package jeju.oneroom.domain.message.controller;

import jeju.oneroom.domain.message.dto.MessageDto;
import jeju.oneroom.domain.message.service.MessageService;
import jeju.oneroom.domain.messageroom.entity.MessageRoom;
import jeju.oneroom.domain.messageroom.service.MessageRoomService;
import jeju.oneroom.domain.user.entity.User;
import jeju.oneroom.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessageController {

    private final UserService userService;
    private final MessageService messageService;
    private final MessageRoomService messageRoomService;

    @PostMapping
    public ResponseEntity<MessageDto.Response> postMessage(@Valid @RequestBody MessageDto.Post postDto) {
        MessageRoom messageRoom = messageRoomService.findVerifiedMessageRoom(postDto.getMessageRoomId());
        User sender = userService.verifyExistsUser(postDto.getSenderId());  // email
        User receiver = userService.verifyExistsUser(postDto.getReceiverId());

        MessageDto.Response response = messageService.createMessage(postDto, messageRoom, sender, receiver);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @MessageMapping("/chats/{roomId}")
    public void handleChatMessage(@DestinationVariable Long roomId,
                                  @Valid @RequestBody MessageDto.Post postDto) {
        MessageRoom messageRoom = messageRoomService.findVerifiedMessageRoom(postDto.getMessageRoomId());
        User sender = userService.verifyExistsUser(postDto.getSenderId());  // email
        User receiver = userService.verifyExistsUser(postDto.getReceiverId());

        messageService.createMessage(postDto, messageRoom, sender, receiver);
    }
}
