package kr.ac.hs.selab.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorMessage {
    NOT_SUPPORTED_SOCIAL_LOGIN("지원하지 않는 소셜 로그인입니다."),
    NON_EXISTENT_USER("해당 사용자가 존재하지 않습니다."),
    NON_EXISTENT_USER_PRIVACY("회원 개인 정보가 존재하지 않습니다."),
    EXISTENT_USER("이미 존재하는 사용자입니다."),
    EXISTENT_NICKNAME("이미 존재하는 닉네임입니다."),
    EXISTENT_PHONE_NUMBER("이미 존재하는 전화번호입니다."),
    OVERABUNDANCE_BOARD_COUNT("게시판 생성 개수가 초과되었습니다."),
    DUPLICATION_BOARD_TITLE("중복된 게시판 명칭입니다."),
    IS_NOT_EXITS_BOARD("존재하지 않는 게시판입니다."),
    IS_NOT_EXITS_POST("존재하지 않는 게시물입니다.");

    private final String message;

    public String getMessage() {
        return message;
    }
}
