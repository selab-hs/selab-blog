package kr.ac.hs.selab.member.domain;

import kr.ac.hs.selab.auth.dto.AuthPrincipal;
import kr.ac.hs.selab.board.domain.Board;
import kr.ac.hs.selab.common.domain.Date;
import kr.ac.hs.selab.follow.domain.Follow;
import kr.ac.hs.selab.follow.domain.Follows;
import kr.ac.hs.selab.member.domain.vo.*;
import kr.ac.hs.selab.post.domain.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Builder
@AllArgsConstructor
public class Member extends Date {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @Embedded
    private Name name;

    @Embedded
    private Nickname nickname;

    @Embedded
    private PhoneNumber phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_gender")
    private Gender gender;

    @Embedded
    private Birth birth;

    @Enumerated
    private StudentId studentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role", nullable = false)
    private Role role;

    @Column(name = "member_term_service", nullable = false)
    private boolean termService;

    @Column(name = "member_term_privacy", nullable = false)
    private boolean termPrivacy;

    @Column(name = "member_term_location", nullable = false)
    private boolean termLocation;

    @Embedded
    private Follows follows;

    @OneToMany(mappedBy = "postMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Post> posts = new TreeSet<>();

    protected Member() {

    }

    public AuthPrincipal toAuthPrincipal() {
        return AuthPrincipal.builder()
                .id(id)
                .email(email)
                .password(password)
                .role(role)
                .build();
    }

    public boolean follow(Member toMember) {
        Follow follow = new Follow(this, toMember);
        if (isFollowing(follow)) {
            return removeFollow(follow, toMember);
        }
        return addFollow(follow, toMember);
    }

    private boolean isFollowing(Follow follow) {
        return follows.existsFromFollows(follow) && follows.existsToFollows(follow);
    }

    private boolean addFollow(Follow follow, Member toMember) {
        follows.addFromFollows(follow);
        toMember.follows.addToFollows(follow);
        return true;
    }

    private boolean removeFollow(Follow follow, Member toMember) {
        follows.removeFromFollows(follow);
        toMember.follows.removeToFollows(follow);
        return false;
    }

    public void addPost(Post post) {
        posts.add(post);
    }


}
