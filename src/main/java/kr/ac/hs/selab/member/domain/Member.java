package kr.ac.hs.selab.member.domain;

import kr.ac.hs.selab.auth.dto.CustomUserDetails;
import kr.ac.hs.selab.common.domain.Date;
import kr.ac.hs.selab.member.domain.vo.Gender;
import kr.ac.hs.selab.member.domain.vo.Role;
import kr.ac.hs.selab.member.domain.vo.SocialType;
import kr.ac.hs.selab.member.dto.MemberPrivacyRequest;
import kr.ac.hs.selab.member.dto.MemberSocialSignupRequest;
import kr.ac.hs.selab.oauth.dto.CustomOAuth2User;
import kr.ac.hs.selab.oauth.dto.SocialAttributes;
import kr.ac.hs.selab.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.*;

@AllArgsConstructor
@Builder
@Entity
public class Member extends Date {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_email", nullable = false)
    private String email;

    @Column(name = "member_password")
    private String password;

    @Column(name = "member_name")
    private String name;

    @Column(name = "member_nickname")
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_gender")
    private Gender gender;

    @Column(name = "member_birth")
    private String birth;

    @Column(name = "member_phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role", nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_social_type", nullable = false)
    private SocialType socialType;

    @Column(name = "member_student_id")
    private String studentId;

    @Column(name = "member_term_service", nullable = false)
    private boolean termService;

    @Column(name = "member_term_privacy", nullable = false)
    private boolean termPrivacy;

    @Column(name = "member_term_location", nullable = false)
    private boolean termLocation;

<<<<<<< HEAD
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();


=======
    @OneToMany(mappedBy = "postMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Post> posts = new TreeSet<>();
>>>>>>> f69e7fcba78cb7ad30684ba782035d8c768a5200
    protected Member() {
    }

    public CustomUserDetails toCustomUserDetails() {
        return CustomUserDetails.builder()
                .id(id)
                .email(email)
                .password(password)
                .socialType(socialType)
                .role(role)
                .build();
    }

    public CustomOAuth2User toCustomOAuth2User() {
        return CustomOAuth2User.builder()
                .id(id)
                .email(email)
                .role(role)
                .socialType(socialType)
                .build();
    }

    public boolean isSocial() {
        return socialType != SocialType.BASIC;
    }

    public void updateSocial(MemberSocialSignupRequest request) {
        this.gender = request.getGender();
        this.nickname = request.getNickname();
        this.phoneNumber = request.getPhoneNumber();
        this.birth = request.getBirth();
        this.studentId = request.getStudentId();
        this.termLocation = request.isTermLocation();
        this.termPrivacy = request.isTermPrivacy();
        this.termService = request.isTermService();
    }

    public void updatePrivacy(MemberPrivacyRequest request) {
        this.gender = request.getGender();
        this.nickname = request.getNickname();
        this.phoneNumber = request.getPhoneNumber();
        this.birth = request.getBirth();
        this.studentId = request.getStudentId();
    }

    public boolean checkPrivacyEmpty() {
        return gender == Gender.EMPTY ||
                Objects.isNull(nickname) ||
                Objects.isNull(phoneNumber) ||
                Objects.isNull(birth) ||
                Objects.isNull(studentId);
    }

    public void addPost(Post post) {
        posts.add(post);
    }
}
