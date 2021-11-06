package kr.ac.hs.selab.member.utils;

public class MemberValidUtils {
    public static class Regexp {
        public static final String EMAIL = "^[_a-zA-Z0-9-\\+]+(\\.[_a-zA-Z0-9-]+)*@" + "[a-zA-Z0-9-]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z]{2,3})$";
        public static final String PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[가-힣A-Za-z\\d$@$!%*#?&]{8,30}$";
        public static final String NAME = "^[가-힣]{2,10}$";
        public static final String NICKNAME = "^[a-zA-Z가-힣0-9]{2,20}$";
        public static final String BIRTH = "^(19[0-9][0-9]|20\\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$";
        public static final String PHONE_NUMBER = "^010[0-9]{8}$";
        public static final String STUDENT_ID = "^[0-9]{4}58[0-9]{3}$";
    }

    public static class Message {
        public static final String EMAIL = "이메일 형식이 맞지 않습니다.";
        public static final String PASSWORD = "비밀번호는 최소 8자 ~ 최대 30자, 최소 영문 소문자, 대문자, 특수문자를 각각 하나 이상 포함해야 합니다.";
        public static final String NAME = "이름 형식이 맞지 않습니다.";
        public static final String NICKNAME = "닉네임 형식이 맞지 않습니다.";
        public static final String BIRTH = "생년월일은 yyyymmdd 형식에 맞추어 작성해주세요.";
        public static final String PHONE_NUMBER = "전화번호 형식이 맞지 않습니다.";
        public static final String STUDENT_ID = "학번 형식이 맞지 않습니다.";
        public static final String TERM_SERVICE = "이용 약관에 동의해 주세요.";
        public static final String TERM_PRIVACY = "개인 정보 제공에 동의해 주세요.";
        public static final String TERM_LOCATION = "위치 정보 제공에 동의해 주세요.";
    }
}
