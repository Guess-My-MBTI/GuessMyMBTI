package com.hsstudy.GuessMyMBTI.api.service.guest;

import com.hsstudy.GuessMyMBTI.api.domain.account.Account;
import com.hsstudy.GuessMyMBTI.api.domain.account.Authority;
import com.hsstudy.GuessMyMBTI.api.domain.guest.Guest;
import com.hsstudy.GuessMyMBTI.api.domain.guest.GuestDto;
import com.hsstudy.GuessMyMBTI.api.repository.AccountRepository;
import com.hsstudy.GuessMyMBTI.api.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private AccountRepository accountRepository;


    @Override
    public ResponseEntity<Account> ownerInfo(Long ownerId) {
        // todo : try catch로 예외처리 -> url Owner가 없는 경우
        Account urlOwner = accountRepository.findById(ownerId).orElse(null);

        return ResponseEntity.ok().body(urlOwner);
    }

    @Override
    public ResponseEntity<Guest> guestLogin(@RequestBody GuestDto requestDto, HttpServletRequest request) {
        try {
            Long ownerId = Long.parseLong(request.getParameter("id")); // ownerId를 가져와서 같이 저장하기
            Account sharedOwner = accountRepository.findById(ownerId).orElse(null);
            Guest guest = Guest.builder()
                    .nickname(requestDto.getNickname())
                    .owner(sharedOwner)
                    .authority(Authority.ROLE_GUEST)
                    .build();
            guestRepository.save(guest);
            return ResponseEntity.ok().body(guest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Guest> guestResult(GuestDto requestDto) {
        // Owner의 id 값을 불러오지 못하는 것 같음
        Guest existGuest = guestRepository.findByNicknameAndId(requestDto.getNickname(), requestDto.getGuestId()).orElse(null);

        existGuest.setComment(requestDto.getComment());
        existGuest.setResult(requestDto.getResult());
        existGuest.setAccuracy(requestDto.getAccuracy());

        guestRepository.save(existGuest);

        return ResponseEntity.ok().body(existGuest);
    }


    // todo : 얘는 필요 없을 것 같음
    @Override
    public ResponseEntity<Guest> guestInfo(HttpServletRequest request) {
        String nickname = request.getParameter("nickname");
        Long guestId = Long.parseLong(request.getParameter("guestId"));
        Guest currentGuest = guestRepository.findByNicknameAndId(nickname, guestId).orElse(null);
        return ResponseEntity.ok().body(currentGuest);
    }

}
