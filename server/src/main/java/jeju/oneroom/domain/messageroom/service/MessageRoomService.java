package jeju.oneroom.domain.messageroom.service;

import jeju.oneroom.domain.messageroom.entity.MessageRoom;
import jeju.oneroom.domain.messageroom.repository.MessageRoomRepository;
import jeju.oneroom.global.exception.BusinessLogicException;
import jeju.oneroom.global.exception.ExceptionCode;
import jeju.oneroom.domain.message.entity.Message;
import jeju.oneroom.global.message.redis.RedisSubscriber;
import jeju.oneroom.domain.message.repository.MessageRepository;
import jeju.oneroom.domain.messageroom.dto.MessageRoomDto;
import jeju.oneroom.domain.messageroom.entity.MessageRoomStatus;
import jeju.oneroom.domain.messageroom.mapper.MessageRoomMapper;
import jeju.oneroom.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MessageRoomService {

    private final MessageRoomMapper messageRoomMapper;
    private final MessageRoomRepository messageRoomRepository;
    private final Map<String, ChannelTopic> topics;
    private final RedisMessageListenerContainer redisMessageListener;
    private final RedisSubscriber redisSubscriber;
    private final MessageRepository messageRepository;

    @Transactional
    public MessageRoomDto.SimpleResponse createMessageRoom(MessageRoomDto.Post postDto, User sender, User receiver) {
        if (messageRoomRepository.findMessageRoom(sender.getId(), receiver.getId()) != null) {
            throw new BusinessLogicException(ExceptionCode.EXISTS_MESSAGEROOM);
        }

        MessageRoom messageRoom = messageRoomMapper.messageRoomPostDtoToMessageRoom(postDto);
        messageRoom.setProperties(sender, receiver);

        MessageRoom savedMessageRoom = messageRoomRepository.save(messageRoom);

        sendTopic(savedMessageRoom.getMessageRoomId());

        return messageRoomMapper.messageRoomToMessageRoomSimpleResponseDto(savedMessageRoom);
    }

    public Page<MessageRoomDto.SimpleResponse> findMessageRoomsByUserId(Long userId, int page, int size) {
        return messageRoomRepository.findMessageRoomsBySenderIdOrReceiverId(userId, PageRequest.of(page - 1, size))
                .map(messageRoomMapper::messageRoomToMessageRoomSimpleResponseDto);
    }

    public MessageRoomDto.Response findMessages(User user, Long messageRoomId) {
        MessageRoom messageRoom = findVerifiedMessageRoom(messageRoomId);
        List<Message> messages = new ArrayList<>(messageRoom.getMessages());

        if (!messages.isEmpty()) {
            Message message = messages.get(messages.size() - 1);

            if (user == message.getReceiver()) {
                messageRoom.setMessageRoomStatus(MessageRoomStatus.CHECK);
            }
        }

        MessageRoom savedMessageRoom = messageRoomRepository.save(messageRoom);

        sendTopic(savedMessageRoom.getMessageRoomId());

        return messageRoomMapper.messageRoomToMessageRoomResponseDto(savedMessageRoom);
    }

    @Transactional
    public void deleteMessageRoom(User user, Long messageRoomId) {
        MessageRoom messageRoom = findVerifiedMessageRoom(messageRoomId);
        User sender = messageRoom.getSender();
        User receiver = messageRoom.getReceiver();

        if (user == sender) messageRoom.setSender(null);
        if (user == receiver) messageRoom.setReceiver(null);

        if (messageRoom.getSender() == null && messageRoom.getReceiver() == null) {
            messageRepository.deleteAllById(messageRoom.getMessages()
                    .stream()
                    .map(Message::getMessageId)
                    .collect(Collectors.toList()));

            messageRoomRepository.deleteById(messageRoomId);
        }
    }

    public MessageRoom findVerifiedMessageRoom(Long messageRoomId) {
        return messageRoomRepository.findById(messageRoomId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_MESSAGE_ROOM));
    }

    private void sendTopic(Long messageRoomId) {
        String roomId = "messageRoom" + messageRoomId;
        log.info("topics = {}", topics);

        if (!topics.containsKey(roomId)) {
            ChannelTopic topic = new ChannelTopic(roomId);
            log.info("메시지 토픽 생성");

            redisMessageListener.addMessageListener(redisSubscriber, topic);
            topics.put(roomId, topic);

            log.info("메시지 토픽 전송");
            log.info("topics = {}", topics);
        }
    }
}
