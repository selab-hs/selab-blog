package kr.ac.hs.selab.member.dto;

import kr.ac.hs.selab.member.domain.vo.Gender;
import lombok.Data;
import lombok.NonNull;

@Data
public class MemberSocialSignUpDto {
    @NonNull
    private String nickname;

    @NonNull
    private Gender gender;

    @NonNull
    private String birth;

    @NonNull
    private String phoneNumber;

    @NonNull
    private String studentId;

    @NonNull
    private boolean termService;

    @NonNull
    private boolean termPrivacy;

    @NonNull
    private boolean termLocation;
}
