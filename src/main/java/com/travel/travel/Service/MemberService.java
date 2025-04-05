package com.travel.travel.Service;

import com.travel.travel.DTO.MemberDTO;
import com.travel.travel.Entity.MemberEntity;
import com.travel.travel.Repository.MemberRepository;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Retryable(
            value = { RuntimeException.class }, // 재시도할 예외 타입
            maxAttempts = 3,  // 최대 3번 재시도
            backoff = @Backoff(delay = 1000, multiplier = 2) // 2초 대기 후 재시도
    )
    public void registerMember(MemberDTO memberDTO) {
        String encodePassword = passwordEncoder.encode(memberDTO.getPassword());

        MemberEntity member = new MemberEntity();
        member.setId(memberDTO.getId());
        member.setPassword(encodePassword);
        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());
        member.setPhone(memberDTO.getPhone());
        member.setRole(memberDTO.getRole());

        memberRepository.save(member);
    }

    public MemberEntity getUserById(String id) {
        return memberRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found : " + id));
    }
}
