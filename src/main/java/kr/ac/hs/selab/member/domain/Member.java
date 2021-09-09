package kr.ac.hs.selab.member.domain;

import kr.ac.hs.selab.common.domain.DateEntity;
import kr.ac.hs.selab.member.domain.vo.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends DateEntity {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

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

    @Enumerated
    private ClassNumber classNumber;

}
