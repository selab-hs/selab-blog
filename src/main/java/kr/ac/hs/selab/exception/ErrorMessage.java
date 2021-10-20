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
    INVALID_CLASS_NUMBER("학번 형식이 맞지 않습니다."),
    DUPLICATION_MEMBER("중복된 계정입니다."),
    INVALID_SOCIAL_LOGIN("지원하지 않는 소셜 로그인 입니다."),
    NON_EXISTENT_USER("해당 사용자를 찾을 수 없습니다."),
    EXISTENT_USER("이미 존재하는 사용자입니다."),
    EXISTENT_NICKNAME("이미 존재하는 닉네임입니다."),
    EXISTENT_PHONE_NUMBER("이미 존재하는 전화번호입니다.");

    private final String message;

    public String getMessage() {
        return message;
    }
}
