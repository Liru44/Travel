package com.travel.travel.Controller;

import com.travel.travel.Component.JwtTokenProvider;
import com.travel.travel.DTO.MemberDTO;
import com.travel.travel.Service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    private final AuthenticationManager authenticationManager;
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    public MemberController(AuthenticationManager authenticationManager, MemberService memberService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.memberService = memberService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerMember(@RequestBody MemberDTO memberDTO) {
        try {
            memberService.registerMember(memberDTO);
            return ResponseEntity.ok("회원가입 성공");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String id, @RequestParam String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(id, password));

            String token = jwtTokenProvider.generateToken(authentication.getName());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("아이디나 비밀번호가 틀립니다. ");
        }
    }
}
