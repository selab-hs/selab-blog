package kr.ac.hs.selab.post.converter;

import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.post.domain.Post;
import kr.ac.hs.selab.post.dto.PostDetailDto;
import kr.ac.hs.selab.post.dto.PostDto;
import org.springframework.stereotype.Component;

@Component
public class PostConverter {
    public Post toPost(PostDto dto, Member member, Board board) {
        return Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .postMember(member)
                .postBoard(board)
                .build();
    }

    public PostDetailDto toPostDetailDto(Post post) {
        return PostDetailDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }
}
