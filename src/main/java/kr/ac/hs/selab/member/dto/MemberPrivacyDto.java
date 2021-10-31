package kr.ac.hs.selab.member.dto;

import kr.ac.hs.selab.member.domain.vo.Gender;
import kr.ac.hs.selab.member.utils.MemberValidUtils;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Pattern;

@Data
public class MemberPrivacyDto {
    @NonNull
    @Pattern(regexp = MemberValidUtils.Regexp.NICKNAME, message = MemberValidUtils.Message.NICKNAME)
    private String nickname;

    @NonNull
    private Gender gender;

    @NonNull
    @Pattern(regexp = MemberValidUtils.Regexp.BIRTH, message = MemberValidUtils.Message.BIRTH)
    private String birth;

    @NonNull
    @Pattern(regexp = MemberValidUtils.Regexp.PHONE_NUMBER, message = MemberValidUtils.Message.PHONE_NUMBER)
    private String phoneNumber;

    @NonNull
    @Pattern(regexp = MemberValidUtils.Regexp.STUDENT_ID, message = MemberValidUtils.Message.STUDENT_ID)
    private String studentId;

    protected MemberPrivacyDto() {
    }
}
