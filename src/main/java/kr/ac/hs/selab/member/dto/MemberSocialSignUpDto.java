package kr.ac.hs.selab.member.dto;

import kr.ac.hs.selab.member.domain.vo.*;
import lombok.Data;
import lombok.NonNull;

@Data
public class MemberSocialSignUpDto {
    @NonNull
    private Nickname nickname;

    @NonNull
    private Gender gender;

    @NonNull
    private Birth birth;

    @NonNull
    private PhoneNumber phoneNumber;

    @NonNull
    private StudentId studentId;

    @NonNull
    private boolean termService;

    @NonNull
    private boolean termPrivacy;

    @NonNull
    private boolean termLocation;
}
