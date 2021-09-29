package kr.ac.hs.selab.post.domain;

import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.common.domain.Date;
import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.post.domain.vo.SubTitle;
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
    Long id;

    @Embedded
    private SubTitle subTitle;

    @Column(name = "post_sub_content")
    private String subContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_Id")
    private Member postMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_Id")
    private Board postBoard;

    protected Post() {
    }

    public SubTitle getSubTitle() {
        return subTitle;
    }

    public Post(SubTitle subTitle, String subContent, Member postMember, Board postBoard) {
        this.subTitle = subTitle;
        this.subContent = subContent;
        this.postMember = postMember;
        this.postBoard = postBoard;
    }
}
