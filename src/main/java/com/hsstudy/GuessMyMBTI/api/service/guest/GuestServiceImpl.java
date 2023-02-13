package com.hsstudy.GuessMyMBTI.api.service.guest;

import com.hsstudy.GuessMyMBTI.api.repository.guest.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class GuestServiceImpl implements GuestService{

    @Autowired
    private GuestRepository guestRepository;
}
