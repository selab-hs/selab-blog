package kr.ac.hs.selab.member.dto;

import kr.ac.hs.selab.member.domain.vo.Birth;
import kr.ac.hs.selab.member.domain.vo.Gender;
import kr.ac.hs.selab.member.domain.vo.Nickname;
import kr.ac.hs.selab.member.domain.vo.PhoneNumber;
import lombok.Data;
import lombok.NonNull;

@Data
public class SocialMemberSignDto {
    @NonNull
    private Nickname nickname;

    @NonNull
    private Gender gender;

    @NonNull
    private Birth birth;

    @NonNull
    private PhoneNumber phoneNumber;

    @NonNull
    private boolean termLocation;
}
