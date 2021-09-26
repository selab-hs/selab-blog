package kr.ac.hs.selab.member.domain.vo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Gender {
    EMPTY("선택안함"),
    MAN("남자"),
    WOMAN("여자");

    private final String gender;

    public String getGender() {
        return gender;
    }
}