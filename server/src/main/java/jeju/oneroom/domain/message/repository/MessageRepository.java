package jeju.oneroom.domain.message.repository;

import jeju.oneroom.domain.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
