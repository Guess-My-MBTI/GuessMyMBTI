package com.hsstudy.GuessMyMBTI.api.service.guest;

import com.hsstudy.GuessMyMBTI.api.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestServiceImpl implements GuestService{

    @Autowired
    private GuestRepository guestRepository;
}
