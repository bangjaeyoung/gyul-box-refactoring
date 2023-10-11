package jeju.oneroom.domain.post.mapper;

import jeju.oneroom.domain.houseinfo.mapper.HouseInfoMapper;
import jeju.oneroom.domain.postcomment.mapper.PostCommentMapper;
import jeju.oneroom.domain.post.dto.PostDto;
import jeju.oneroom.domain.post.entity.Post;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {PostCommentMapper.class, HouseInfoMapper.class})
public interface PostMapper {
    Post postDtoToPost(PostDto.Post postDto);

    @Mapping(target = "writer", source = "user")
    PostDto.Response postToResponseDto(Post post);

    @Mapping(target = "nickname", expression = "java(post.getUser().getNickname())")
    PostDto.SimpleResponseDto postToSimpleResponseDto(Post post);
}
