package jeju.oneroom.domain.postcomment.mapper;

import jeju.oneroom.domain.postcomment.dto.PostCommentDto;
import jeju.oneroom.domain.postcomment.entity.PostComment;
import jeju.oneroom.domain.user.mapper.UserMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class})
public interface PostCommentMapper {
    PostComment postDtoToPostComment(PostCommentDto.Post postDto);

    @Mapping(target = "writer", source = "user")
    @Mapping(target = "postId", source = "post.id")
    @Mapping(target = "postTitle", source = "post.title")
    PostCommentDto.Response postCommentToResponseDto(PostComment postComment);

    List<PostCommentDto.Response> postCommentsToResponseDtos(List<PostComment> postComments);
}
