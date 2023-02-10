package com.hsstudy.GuessMyMBTI.Service.user;

import com.hsstudy.GuessMyMBTI.Entity.User.User;
import com.hsstudy.GuessMyMBTI.Repository.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }
}
