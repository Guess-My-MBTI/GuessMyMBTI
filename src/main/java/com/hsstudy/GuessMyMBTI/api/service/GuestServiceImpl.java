package com.hsstudy.GuessMyMBTI.api.service;

import com.hsstudy.GuessMyMBTI.api.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class GuestServiceImpl implements GuestService{

    @Autowired
    private GuestRepository guestRepository;
}
