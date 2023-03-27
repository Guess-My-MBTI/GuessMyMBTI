package com.hsstudy.GuessMyMBTI.api.domain.account;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class UserDetailsImpl implements UserDetails {

    private Account account;

    public UserDetailsImpl(Account account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return account.getAuthority().toString();
            }
        });
        return collection;
    }

    // 비밀번호를 안써가지고.. 그렇게 됐슈
    @Override
    public String getPassword() {
        return null;
    }

    // todo : 이메일을 필수 요건으로 사용했는데 바꿔야 할 필요가 있음 -> 해결 완
    @Override
    public String getUsername() {
        return account.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}