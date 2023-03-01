package com.hsstudy.GuessMyMBTI.api.service.owner;


import com.hsstudy.GuessMyMBTI.api.controller.OwnerController;
import com.hsstudy.GuessMyMBTI.api.entity.owner.Owner;
import com.hsstudy.GuessMyMBTI.api.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;

//    @Value("${security.oauth2.client.registration.kakao.clientId}")
//    private String clientId;

    @Override
    @Transactional
    public void save(OwnerController.OwnerRequest ownerRequest) {
        System.out.println("ownerRequest = " + ownerRequest.getOwnerMbti());
        System.out.println("ownerRequest = " + ownerRequest.getAnswer());
        Owner owner = new Owner();
        owner.setOwnerNickname("jong");
        owner.setAnswer(ownerRequest.getAnswer());
        owner.setOwnerMbti(ownerRequest.getOwnerMbti());
        System.out.println("owner = " + owner.getOwnerMbti());
        System.out.println("owner = " + owner.getAnswer());
        ownerRepository.save(owner);
    }
}
