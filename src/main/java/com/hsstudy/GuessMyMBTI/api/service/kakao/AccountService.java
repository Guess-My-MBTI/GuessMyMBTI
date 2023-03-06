package com.hsstudy.GuessMyMBTI.api.service.kakao;

import com.hsstudy.GuessMyMBTI.api.domain.Account;
import com.hsstudy.GuessMyMBTI.api.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor

public class AccountService {
    private final AccountRepository accountRepository;

    /* 사용자 정보 가져오는 메소드
    요청 헤더에 Authorization 항목으로 토큰이 오면,
    인증된 사용자에 대해 정보를 가져와 Account 타입으로 반환 */
    public Account getAccountInfo(HttpServletRequest request) {

        String authenticAccount = (String) request.getAttribute("authenticAccount"); // 이메일 리턴
        System.out.println("JWT 토큰 인증이 완료된 계정입니다 : " + authenticAccount);

        Account account = accountRepository.findByEmail(authenticAccount)
                .orElseThrow();
        System.out.println("여기는 AccountService : " + account);
        return account;
    }


}