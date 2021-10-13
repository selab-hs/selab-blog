package kr.ac.hs.selab.post.domain;

import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.common.domain.Date;
import kr.ac.hs.selab.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
public class Post extends Date {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_title", unique = true)
    private String title;

    @Lob
    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_Id")
    private Member postMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_Id")
    private Board postBoard;

    protected Post() {
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

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
