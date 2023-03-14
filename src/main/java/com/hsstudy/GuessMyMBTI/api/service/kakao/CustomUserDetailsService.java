package com.hsstudy.GuessMyMBTI.api.service.kakao;

import com.hsstudy.GuessMyMBTI.api.domain.account.Account;
import com.hsstudy.GuessMyMBTI.api.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

/*

 * UserDetailsService 인터페이스를 구현한 클래스입니다.
 * loadUserByUsername 메소드를 오버라이드 하는데 여기서 넘겨받은 UserDetails 와 Authentication 의 패스워드를 비교하고 검증하는 로직을 처리합니다.
 * 물론 DB 에서 username 을 기반으로 값을 가져오기 때문에 아이디 존재 여부도 자동으로 검증 됩니다.
 * loadUserByUsername 메소드를 어디서 호출하는지 내부를 타고 들어가봅니다.

 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional // transactional 처리로 작업단위 지정
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountRepository.findByEmail(email)
                //Repo 를 Optional 로 지정해줘야 map 메서드를 사용할 수 있음
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(email + " 이메일을 DB 에서 찾을 수 없습니다."));

    }

    // DB 계정 정보가 존재하면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Account account) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(account.getAuthority().toString());

        // 여기서 리턴하는 User 은 스프링 시큐리티 내 UserDetails 의 구현체임...!! 헷갈리지xx
        // UserDetails 는 인터페이스라는거~!
        return new User(
                account.getEmail(),
                "",
                Collections.singleton(grantedAuthority)
        );
    }

}