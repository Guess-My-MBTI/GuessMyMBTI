package com.hsstudy.GuessMyMBTI.api.service.guest;

import com.hsstudy.GuessMyMBTI.api.domain.account.Authority;
import com.hsstudy.GuessMyMBTI.api.entity.guest.Guest;
import com.hsstudy.GuessMyMBTI.api.entity.guest.GuestDto;
import com.hsstudy.GuessMyMBTI.api.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestRepository guestRepository;

    @Override
    public ResponseEntity<Guest> guestLogin(@RequestBody GuestDto requestDto) {
        // 닉네임을 꺼내서 그 값이 repo에 있다면 저장 그냥 실행
        // 없다면 저장한 후 실행
        try {
            System.out.println("AuthService : guestLogin 실행 -> requestDto = " + requestDto);
            System.out.println("GuestRepository 에 nickname로 유저 있는지 판단하기");

            Guest existGuest = guestRepository.findByNicknameAndId(requestDto.getNickname(), requestDto.getGuestId()).orElse(null);
            System.out.println("existGuest = " + existGuest);

            // DB에 현재 닉네임과 id가 일치하는 Guest가 없다면 if 수행
            if (existGuest == null) {
                System.out.println("처음 로그인 하는 회원입니다.");
                Guest newGuest = Guest.builder()
                        .nickname(requestDto.getNickname())
                        .authority(Authority.ROLE_GUEST)
                        .build();

                System.out.println("newGuest = " +
                        newGuest.getId() + " " +
                        newGuest.getNickname() + " " +
                        newGuest.getAuthority() + " " +
                        newGuest.getAnswer() + " " +
                        newGuest.getResult() + " " +
                        newGuest.getId()
                );
                guestRepository.save(newGuest);
                existGuest = newGuest;
                return ResponseEntity.ok().body(existGuest);
            }
            return ResponseEntity.ok().body(existGuest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 위의 try catch문을 안 탄 경우
        return ResponseEntity.ok().body(null);
    }

    @Override
    public ResponseEntity<Guest> guestResult(GuestDto requestDto) {
        Guest existGuest = guestRepository.findByNicknameAndId(requestDto.getNickname(), requestDto.getGuestId()).orElse(null);
        System.out.println("existGuest = " + existGuest.getNickname() + " " + existGuest.getId());
        System.out.println("Guest의 requestDto");
        System.out.println("requestDto : " +
                requestDto.getGuestId() + " " +
                requestDto.getNickname() + " " +
                requestDto.getOwnerId() + " " +
                requestDto.getAnswer() + " " +
                requestDto.getResult() + " " +
                requestDto.getComment() + " " +
                requestDto.getAccuracy() + " " +
                requestDto.getRole());

        existGuest.setComment(requestDto.getComment());
        existGuest.setResult(requestDto.getResult());

        guestRepository.save(existGuest);

        return ResponseEntity.ok().body(existGuest);
    }

    @Override
    public ResponseEntity<Guest> guestInfo(GuestDto requestDto) {
        Guest currentGuest = guestRepository.findByNickname(requestDto.getNickname()).orElse(null);
        System.out.println(currentGuest.getId());
        return ResponseEntity.ok().body(currentGuest);
    }

}
