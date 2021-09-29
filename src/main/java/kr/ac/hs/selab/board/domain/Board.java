package kr.ac.hs.selab.board.domain;

import kr.ac.hs.selab.board.domain.vo.Content;
import kr.ac.hs.selab.board.domain.vo.Title;
import kr.ac.hs.selab.board.dto.BoardDto;
import kr.ac.hs.selab.common.domain.Date;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.post.domain.Post;
import kr.ac.hs.selab.post.dto.PostDto;
import kr.ac.hs.selab.post.dto.PostMakeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Builder
@AllArgsConstructor
public class Board extends Date {
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Embedded
    private Title title;

    @Embedded
    private Content content;

    @OneToMany(mappedBy = "postBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    public static Board of(BoardDto dto) {
        return Board.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }

    protected Board() {

    }

    public Title getTitle() {
        return title;
    }

    public Content getContent() {
        return content;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void post(PostMakeDto dto, Member member) {
        Post post = new Post(dto.getSubTitle(), dto.getSubContent(), member, this);
        posts.add(post);
    }
}
