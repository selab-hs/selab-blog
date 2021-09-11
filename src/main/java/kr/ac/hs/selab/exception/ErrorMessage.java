package kr.ac.hs.selab.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorMessage {
    INVALID_NAME("이름 형식이 맞지 않습니다."),
    INVALID_EMAIL("이메일 형식이 맞지 않습니다."),
    INVALID_NICKNAME("닉네임 형식이 맞지 않습니다."),
    INVALID_PHONE_NUMBER("전화번호 형식이 맞지 않습니다."),
    INVALID_PASSWORD("비밀번호는 최소 8자 ~ 최대 30자, 최소 영문 소문자, 대문자, 특수문자를 각각 하나 이상 포함해야 합니다."),
    INVALID_BIRTH("생년월일은 yyyymmdd 형식에 맞추어 작성해주세요."),
    INVALID_CLASS_NUMBER("학번 형식이 맞지 않습니다.");

    private final String message;

    public String getMessage() {
        return message;
    }
}
