package kr.ac.hs.selab.oauth.dto;

import kr.ac.hs.selab.member.domain.vo.Email;
import kr.ac.hs.selab.member.domain.vo.Name;
import kr.ac.hs.selab.member.domain.vo.SocialType;

public interface SocialAttributes {
    SocialType socialType();

    Name name();

    Email email();
}
