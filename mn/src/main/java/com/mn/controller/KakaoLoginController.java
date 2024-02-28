package com.mn.controller;


import com.mn.CommonService.KakaoLoginService;
import com.mn.entity.Member;
import com.mn.seoha.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class KakaoLoginController {

    @Autowired
    private KakaoLoginService kakaoLoginService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MemberService memberService;

    @Value("${kakao.default.password}")
    private String kakaoPassword;

    @GetMapping("/members/kakao")
    public  String kakaoCallback(String code){
        //인증 서버로부터 받은 code를 이용하여 엑세스 토큰을 얻어옴
        String accessToken = kakaoLoginService.getAccessToken(code);

        Member kakaoMember = kakaoLoginService.getMemberInfo(accessToken);

        try {
           // Member findMember = memberService.saveMember(kakaoMember);
        } catch (IllegalStateException e) {
            memberService.loadUserByUsername(kakaoMember.getEmail());
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(kakaoMember.getEmail(), kakaoPassword);

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";
    }
}
