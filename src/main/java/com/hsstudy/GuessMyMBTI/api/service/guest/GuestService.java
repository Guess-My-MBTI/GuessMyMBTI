package com.hsstudy.GuessMyMBTI.api.service.guest;

import com.hsstudy.GuessMyMBTI.api.domain.guest.Guest;
import com.hsstudy.GuessMyMBTI.api.domain.guest.GuestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

public interface GuestService {
    ResponseEntity<Guest> guestLogin(@RequestBody GuestDto requestDto, HttpServletRequest request);
    ResponseEntity<Guest> guestResult(@RequestBody GuestDto requestDto);
    ResponseEntity<Guest> guestInfo(HttpServletRequest request);
}
