package jeju.oneroom.domain.messageroom.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageRoomStatus {

    CHECK("읽음"),
    UNCHECK("읽지 않음");

    private final String text;
}
