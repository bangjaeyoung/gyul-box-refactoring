package jeju.oneroom.domain.post.entity;

import jeju.oneroom.domain.postcomment.entity.PostComment;
import jeju.oneroom.domain.postlike.entity.PostLike;
import jeju.oneroom.global.common.entity.BaseEntity;
import jeju.oneroom.domain.houseinfo.entity.HouseInfo;
import jeju.oneroom.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;
    private String content;

    @Setter
    private int views;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "houseInfo_id")
    private HouseInfo houseInfo;

    // 게시글의 댓글 수
    @Basic(fetch = FetchType.LAZY)
    @Formula("(select count(*) from post_comment pc where pc.post_id = post_id)")
    private long commentsCount;

    // 게시글의 좋아요 수
    @Basic(fetch = FetchType.LAZY)
    @Formula("(select count(*) from post_like pl where pl.post_id = post_id)")
    private int likes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 게시글 삭제 시, 연관된 모든 게시글 댓글 삭제
    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<PostComment> postComments = new ArrayList<>();

    // 게시글 삭제 시, 연관된 모든 게시글 좋아요 삭제
    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<PostLike> postLikes = new ArrayList<>();

    @Builder
    public Post(String title, String content, int views, HouseInfo houseInfo, User user, List<PostComment> postComments) {
        this.title = title;
        this.content = content;
        this.views = views;
        this.houseInfo = houseInfo;
        this.user = user;
        this.postComments = postComments;
    }

    public void setProperties(HouseInfo houseInfo, User user) {
        this.houseInfo = houseInfo;
        this.user = user;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 조회 수 1 증가
    public void increaseViews() {
        this.views++;
    }

    // 동일 유저 검증
    public boolean isAuthor(User user) {
        return this.user.equals(user);
    }
}
