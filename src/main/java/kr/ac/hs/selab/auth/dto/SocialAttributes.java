package kr.ac.hs.selab.auth.dto;

import kr.ac.hs.selab.member.domain.vo.Email;
import kr.ac.hs.selab.member.domain.vo.Name;
import kr.ac.hs.selab.member.domain.vo.SocialType;

public interface SocialAttributes {
    SocialType socialType();

    String userKey();

    Name name();

    Email email();
}
