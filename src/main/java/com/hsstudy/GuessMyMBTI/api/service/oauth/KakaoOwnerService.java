//package com.hsstudy.GuessMyMBTI.api.service.oauth;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.hsstudy.GuessMyMBTI.api.entity.owner.Owner;
//import com.hsstudy.GuessMyMBTI.api.repository.owner.OwnerRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import javax.transaction.Transactional;
//import java.util.UUID;
//
//@RequiredArgsConstructor
//@Service
//public class KakaoOwnerService {
//    private final PasswordEncoder passwordEncoder;
//    private final OwnerRepository ownerRepository;
//
//    @Value(("${spring.security.oauth2.client.registration.kakao.clientId}"))
//    private String clientId;
//
//    @Transactional
//    public SignupSocialDto kakaoLogin(String code) throws JsonProcessingException {
//        // 1. "인가 코드"로 "액세스 토큰" 요청
//        String accessToken = getAccessToken(code, "http://localhost:3000/login/oauth2/callback/kakao");
//
//        // 2. 필요시에 회원가입
//        Owner kakaoOwner = registerKakaoOwnerIfNeeded(accessToken);
//
//        // 3. 로그인 JWT 토큰 발행
//        String token = jwtTokenCreate(kakaoOwner);
//
//        return SignupSocialDto.builder()
//                .token(token)
//                .userId(kakaoOwner.getId())
//                .build();
//    }
//
//    private String getAccessToken(String code, String redirect_uri) throws JsonProcessingException {
//        // HTTP Header 생성
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//
//        // HTTP Body 생성
//        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
//        body.add("grant_type", "authorization_code");
//        body.add("client_id", clientId);
//        body.add("redirect_uri", redirect_uri);
//        body.add("code", code);
//
//        // HTTP 요청 보내기
//        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);
//        RestTemplate rt = new RestTemplate();
//        ResponseEntity<String> response = rt.exchange(
//                "https://kauth.kakao.com/oauth/token",
//                HttpMethod.POST,
//                kakaoTokenRequest,
//                String.class
//        );
//
//        // HTTP 응답 (JSON) -> 액세스 토큰 파싱
//        String responseBody = response.getBody();
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.readTree(responseBody);
//        return jsonNode.get("access_token").asText();
//    }
//
//    // 젤 처음 로그인 시, 회원 가입 안되어 있으면 회원 가입 시켜주기
//    private Owner registerKakaoOwnerIfNeeded(String accessToken) throws JsonProcessingException {
//        JsonNode jsonNode = getKakaoUserInfo(accessToken);
//
//        // DB 에 중복된 Kakao Id 가 있는지 확인
//        String kakaoId = String.valueOf(jsonNode.get("id").asLong());
//        Owner kakaoOwner = ownerRepository.findByUsername(kakaoId).orElse(null);
//
//        // 회원가입
//        if (kakaoOwner == null) {
//            String kakaoNick = jsonNode.get("properties").get("nickname").asText();
//
//            // password: random UUID
//            String password = UUID.randomUUID().toString();
//            String encodedPassword = passwordEncoder.encode(password);
//
//            kakaoOwner = new Owner(kakaoId, kakaoNick, encodedPassword);
//            ownerRepository.save(kakaoOwner);
//        }
//
//        return kakaoOwner;
//    }
//
//    // 카카오에서 동의 항목 가져오기
//    private JsonNode getKakaoUserInfo(String accessToken) throws JsonProcessingException {
//        // HTTP Header 생성
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Bearer " + accessToken);
//        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//
//        // HTTP 요청 보내기
//        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
//        RestTemplate rt = new RestTemplate();
//        ResponseEntity<String> response = rt.exchange(
//                "https://kapi.kakao.com/v2/user/me",
//                HttpMethod.POST,
//                kakaoUserInfoRequest,
//                String.class
//        );
//
//        String responseBody = response.getBody();
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.readTree(responseBody);
//    }
//
//    // JWT 토큰 생성
//    private String jwtTokenCreate(Owner kakaoOwner) {
//        String TOKEN_TYPE = "BEARER";
//
//        UserDetails userDetails = new UserDetailsImpl(kakaoOwner);
//        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        UserDetailsImpl userDetails1 = ((UserDetailsImpl) authentication.getPrincipal());
//        final String token = JwtTokenUtils.generateJwtToken(userDetails1);
//        return TOKEN_TYPE + " " + token;
//    }
//}
