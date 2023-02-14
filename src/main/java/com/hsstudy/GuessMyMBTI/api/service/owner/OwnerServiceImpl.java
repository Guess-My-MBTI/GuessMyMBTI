package com.hsstudy.GuessMyMBTI.api.service.owner;

import com.hsstudy.GuessMyMBTI.api.repository.owner.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;
}
