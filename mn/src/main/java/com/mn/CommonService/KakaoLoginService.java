package com.mn.CommonService;

import com.google.gson.Gson;

import com.mn.constant.OAuthType;
import com.mn.entity.Member;
import com.mn.constant.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class KakaoLoginService {

    private final PasswordEncoder passwordEncoder;

    @Value("${kakao.default.password}")
    private String kakaoPassword;

    public String getAccessToken(String code) {
        //HttpHeader 생성 (http 요청 헤더)
        HttpHeaders header = new HttpHeaders();
        header.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //httpBody 생성(필수 설정 값)
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "8a6a9e1acd8713b37c12e941dd600605");
        body.add("redirect_uri", "http://localhost:8000/members/kakao");
        body.add("code", code);

        //HttpHeader와 httpBody가 설정된 HttpEntity객체 생성
        //MultiValueMap : Map의 확장형,
        //                하나의 키와 하나 이상의 값으로 이루어짐, 값을 리스트 형태로 저장(값을 모두 저장)
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, header);

        //RestTemplate : Spring에서 제공하는 객체, 브라우저의 요청없이 http요청을 처리할 수 있음
        RestTemplate restTemplate = new RestTemplate();

        //http 요청을 보내고 그에 대한 응답을 받음
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",   //액세스 토큰 요청 주소
                HttpMethod.POST,    //요청 방식
                requestEntity,  //요청 헤더와 바디
                String.class);  //응답 받을 타입

        //Http 응답 본문(body) 정보를 반환 (json 형태)
        String jsonData = responseEntity.getBody();



        //Json 데이터에서 엑세스 토큰과 관련된 정보만 추출
        //Map<?,?> : 어떤 타입도 모두 가능(컴파일러가 판단한 타입에 대한 정보를 바탕으로 사용)
        Gson gsonObj = new Gson();
        Map<?,?> data = gsonObj.fromJson(jsonData, Map.class);

        return (String) data.get("access_token");
    }

    public Member getMemberInfo(String accessToken) {
        //HttpHeader 생성 (http 요청 헤더)
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization","Bearer " + accessToken);
        header.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpHeader와 httpBody를 하나의 객체에 담기(body는 생략)
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(header);

        RestTemplate restTemplate = new RestTemplate();

        //http 요청을 보내고 그에 대한 응답을 받음
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",   //액세스 토큰 요청 주소
                HttpMethod.POST,    //요청 방식
                requestEntity,  //요청 헤더와 바디
                String.class);  //응답 받을 타입
        String memberInfo = responseEntity.getBody();

        Gson gsonObj = new Gson();
        Map<?,?> data = gsonObj.fromJson(memberInfo, Map.class);

        Double id = (Double) (data.get("id"));
        String nickname = (String) ( (Map<?,?>)(data.get("properties")) ).get("nickname");

        Member member = new Member();
        member.setName(nickname);
        member.setEmail(Double.toString(id));
        member.setPassword(passwordEncoder.encode(kakaoPassword));
        member.setRole(Role.USER);
        member.setOauth(OAuthType.KAKAO);


        return member;
        //카카오 서버가 반환한 사용자 정보
        //return responseEntity.getBody();
    }

}
