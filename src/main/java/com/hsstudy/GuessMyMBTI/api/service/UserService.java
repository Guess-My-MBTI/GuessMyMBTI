package com.hsstudy.GuessMyMBTI.api.service;

import com.hsstudy.GuessMyMBTI.api.domain.Role;
import com.hsstudy.GuessMyMBTI.api.domain.User;
import com.hsstudy.GuessMyMBTI.api.domain.UserSignUpDTO;
import com.hsstudy.GuessMyMBTI.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(UserSignUpDTO userSignUpDTO) throws Exception {
        if (userRepository.findByEmail(userSignUpDTO.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        if (userRepository.findByNickname(userSignUpDTO.getNickname()).isPresent()) {
            throw new Exception("이미 존재하는 닉네임입니다.");
        }

        User user = User.builder()
                .email(userSignUpDTO.getEmail())
                .password(userSignUpDTO.getPassword())
                .nickname(userSignUpDTO.getNickname())
                .age(userSignUpDTO.getAge())
                .city(userSignUpDTO.getCity())
                .role(Role.USER)
                .build();

        user.passwordEncode(passwordEncoder);
        userRepository.save(user);
    }
}
