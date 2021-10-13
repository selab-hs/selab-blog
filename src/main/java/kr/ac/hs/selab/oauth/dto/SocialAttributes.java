package kr.ac.hs.selab.oauth.dto;

import kr.ac.hs.selab.member.domain.vo.SocialType;

public interface SocialAttributes {
    SocialType socialType();

    String name();

    String email();
}
