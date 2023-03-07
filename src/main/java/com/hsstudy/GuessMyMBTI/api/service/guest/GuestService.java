package com.hsstudy.GuessMyMBTI.api.service.guest;

import com.hsstudy.GuessMyMBTI.api.entity.guest.Guest;
import com.hsstudy.GuessMyMBTI.api.entity.guest.GuestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

public interface GuestService {
    ResponseEntity<Guest> guestLogin(@RequestBody GuestDto requestDto);
}
