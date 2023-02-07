package com.hsstudy.GuessMyMBTI.Service;

import com.hsstudy.GuessMyMBTI.Repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class GuestServiceImpl implements GuestService{

    @Autowired
    private GuestRepository guestRepository;
}
