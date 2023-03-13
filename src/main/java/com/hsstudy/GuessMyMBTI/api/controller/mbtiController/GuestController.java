package com.hsstudy.GuessMyMBTI.api.controller.mbtiController;


import com.hsstudy.GuessMyMBTI.api.entity.guest.Guest;
import com.hsstudy.GuessMyMBTI.api.entity.guest.GuestDto;
import com.hsstudy.GuessMyMBTI.api.service.guest.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GuestController {
    private final GuestService guestService;

    @PostMapping("/guest-login")
    public ResponseEntity<Guest> guestLogin(@RequestBody GuestDto requestDto) {
        System.out.println("requestDto = " + requestDto);
        String nickname = requestDto.getNickname();
        System.out.println("Guest Login parameter-> nickname = " + nickname);
        return guestService.guestLogin(requestDto);
    }

    // todo : guest에서 정보(send 누를 때)를 보내면 저장하기 @PostMapping("/guest-send")
    @PostMapping("/guest-result")
    public ResponseEntity<Guest> guestResult(@RequestBody GuestDto requestDto) {
        System.out.println("requestDto = " + requestDto);
        String nickname = requestDto.getNickname();
        System.out.println("Guest Login parameter-> nickname = " + nickname);
        return guestService.guestResult(requestDto);
    }

    @GetMapping("/guest-info")
    public ResponseEntity<Guest> guestInfo(@RequestBody GuestDto requestDto) {
        return guestService.guestInfo(requestDto);
    }

}
