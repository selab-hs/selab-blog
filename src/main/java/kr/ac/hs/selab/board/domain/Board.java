package kr.ac.hs.selab.board.domain;

import kr.ac.hs.selab.common.domain.Date;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
public class Board extends Date {
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "board_title", unique = true)
    private String title;

    @Lob
    @Column(name = "board_content")
    private String content;

    @OneToMany(mappedBy = "postBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    protected Board() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void addPost(Post post) {
        posts.add(post);
    }
}
