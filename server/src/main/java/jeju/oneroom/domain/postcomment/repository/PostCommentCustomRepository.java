package jeju.oneroom.domain.postcomment.repository;

import jeju.oneroom.domain.postcomment.entity.PostComment;
import jeju.oneroom.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostCommentCustomRepository {

    // 특정 유저가 작성한 모든 게시글 댓글 조회
    Page<PostComment> findPostCommentsByUser(User user, Pageable pageable);
}
