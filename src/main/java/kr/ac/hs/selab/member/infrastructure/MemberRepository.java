package kr.ac.hs.selab.member.infrastructure;

import kr.ac.hs.selab.member.domain.Member;
import kr.ac.hs.selab.member.domain.vo.Email;
import kr.ac.hs.selab.member.domain.vo.Nickname;
import kr.ac.hs.selab.member.domain.vo.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(Email email);

    boolean existsByNickname(Nickname nickname);

    boolean existsByPhoneNumber(PhoneNumber phoneNumber);
}
