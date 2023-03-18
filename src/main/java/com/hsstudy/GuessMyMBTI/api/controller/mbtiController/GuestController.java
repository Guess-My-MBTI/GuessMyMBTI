package com.hsstudy.GuessMyMBTI.api.controller.mbtiController;


import com.hsstudy.GuessMyMBTI.api.domain.account.Account;
import com.hsstudy.GuessMyMBTI.api.domain.guest.Guest;
import com.hsstudy.GuessMyMBTI.api.domain.guest.GuestDto;
import com.hsstudy.GuessMyMBTI.api.service.guest.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class GuestController {
    private final GuestService guestService;

    @GetMapping("/guest-login")
    public ResponseEntity<Account> ownerInfo(HttpServletRequest request) {
        Long ownerId = Long.parseLong(request.getParameter("id"));
        return guestService.ownerInfo(ownerId);
    }

    @PostMapping("/guest-login")
    public ResponseEntity<Guest> guestLogin(@RequestBody GuestDto requestDto, HttpServletRequest request) {
        return guestService.guestLogin(requestDto, request);
    }

    @PostMapping("/guest-result")
    public ResponseEntity<Guest> guestResult(@RequestBody GuestDto requestDto) {
        return guestService.guestResult(requestDto);
    }

    @GetMapping("/guest-info")
    public ResponseEntity<Guest> guestInfo(HttpServletRequest request) {
        return guestService.guestInfo(request);
    }

}
