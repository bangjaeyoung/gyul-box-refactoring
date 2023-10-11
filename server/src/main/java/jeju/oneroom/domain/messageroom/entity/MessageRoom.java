package jeju.oneroom.domain.messageroom.entity;

import jeju.oneroom.global.common.entity.BaseEntity;
import jeju.oneroom.domain.message.entity.Message;
import jeju.oneroom.domain.user.entity.User;
import jeju.oneroom.global.validation.EnumValue;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageRoomId;

    /**
     * 채팅방의 상태. 가능한 값은 'CHECK' 또는 'UNCHECK'
     * 채팅방 최초 생성 시, 채팅방 상태의 기본값이 '읽지 않음'이 되도록 설정
     */
    @Setter
    @Column(nullable = false, columnDefinition = "varchar(255) default 'UNCHECK'")
    @EnumValue(acceptedValues = {"CHECK", "UNCHECK"})
    @Enumerated(EnumType.STRING)
    private MessageRoomStatus messageRoomStatus;

    @Column(nullable = true)
    private String lastMessage;

    @Column(nullable = true)
    private Long lastSenderId;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = true)
    private User sender;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = true)
    private User receiver;

    @OrderBy("messageId")
    @OneToMany(mappedBy = "messageRoom", cascade = CascadeType.ALL)
    private Set<Message> messages = new LinkedHashSet<>();

    // 코드 레벨에서, MessageRoom 엔티티 생성 시에 채팅방 상태의 기본값이 '읽지 않음'이 되도록 설정
    @Builder
    public MessageRoom(Long messageRoomId, User sender, User receiver) {
        this.messageRoomId = messageRoomId;
        this.sender = sender;
        this.receiver = receiver;
        this.messageRoomStatus = MessageRoomStatus.UNCHECK;
    }

    public void setProperties(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.messageRoomStatus = MessageRoomStatus.UNCHECK;
    }

    public void setMessageRoom(Message message) {
        this.lastMessage = message.getContent();
        this.lastSenderId = message.getSender().getId();
        this.messageRoomStatus = MessageRoomStatus.UNCHECK;
    }
}
