package jeju.oneroom.domain.postcomment.repository;

import jeju.oneroom.domain.postcomment.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Long>, PostCommentCustomRepository {
}
