package com.hsstudy.GuessMyMBTI.api.service.guest;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        Long ownerId = Long.parseLong(request.getParameter("id")); // ownerId를 가져와서 같이 저장하기
        System.out.println("ownerId = " + ownerId);
        try {
            System.out.println("AuthService : guestLogin 실행 -> requestDto = " + requestDto);
            System.out.println("GuestRepository 에 nickname로 유저 있는지 판단하기");

            Guest existGuest = guestRepository.findByNicknameAndId(requestDto.getNickname(), requestDto.getGuestId()).orElse(null);

            System.out.println("existGuest = " + existGuest);

            Account sharedOwner = accountRepository.findById(ownerId).orElse(null);

            if (sharedOwner == null) {
                throw new IllegalArgumentException("Owner가 존재하지 않습니다.");
            }

            // DB에 현재 닉네임과 id가 일치하는 Guest가 없다면 if 수행
            if (existGuest == null) {
                System.out.println("처음 로그인 하는 회원입니다.");
                Guest newGuest = Guest.builder()
                        .nickname(requestDto.getNickname())
                        .authority(Authority.ROLE_GUEST)
                        .owner(sharedOwner)
                        .build();

//                System.out.println("newGuest = " +
//                        newGuest.getId() + " " +
//                        newGuest.getNickname() + " " +
//                        newGuest.getAuthority() + " " +
//                        newGuest.getAnswer() + " " +
//                        newGuest.getResult() + " " +
//                        newGuest.getOwner().getId()
//                );

                guestRepository.save(newGuest);
//
//                System.out.println("Owner 삽입 후 newGuest = " +
//                        newGuest.getId() + " " +
//                        newGuest.getNickname() + " " +
//                        newGuest.getAuthority() + " " +
//                        newGuest.getAnswer() + " " +
//                        newGuest.getResult() + " " +
//                        newGuest.getOwner().getId()
//                );
                System.out.println("save 수행 완료");

//                Map<String, Object> resultMap = new HashMap<>();
//                resultMap.put("owner", sharedOwner);
//                resultMap.put("guest", newGuest);
//
//                ObjectMapper objectMapper = new ObjectMapper();
//                String result = objectMapper.writeValueAsString(resultMap);
                Guest guest = newGuest;
                return ResponseEntity.ok().body(guest);
            } else {
                return ResponseEntity.ok().body(null);
            }
        } catch (Exception e) {
            System.out.println("게스트 로그인 실패");
            e.printStackTrace();
        }

        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Guest> guestResult(GuestDto requestDto) {
        System.out.println("Guest의 requestDto");
        System.out.println("requestDto : " +
                requestDto.getGuestId() + " " +
                requestDto.getNickname() + " " +
                requestDto.getOwner() + " " +
                requestDto.getAnswer() + " " +
                requestDto.getResult() + " " +
                requestDto.getComment() + " " +
                requestDto.getAccuracy() + " " +
                requestDto.getRole());

        // Owner의 id 값을 불러오지 못하는 것 같음
        Guest existGuest = guestRepository.findByNicknameAndId(requestDto.getNickname(), requestDto.getGuestId()).orElse(null);
        System.out.println("existGuest = " + existGuest.getNickname() + " " + existGuest.getId());

        existGuest.setComment(requestDto.getComment());
        existGuest.setResult(requestDto.getResult());
        existGuest.setAccuracy(requestDto.getAccuracy());

        guestRepository.save(existGuest);

        return ResponseEntity.ok().body(existGuest);
    }

    @Override
    public ResponseEntity<Guest> guestInfo(HttpServletRequest request) {
        String nickname = request.getParameter("nickname");
        Guest currentGuest = guestRepository.findByNickname(nickname).orElse(null);
//        Guest currentGuest = guestRepository.findByNicknameAndOwnerId(nickname, ownerId).orElse(null);
        System.out.println(currentGuest.getId());
        return ResponseEntity.ok().body(currentGuest);
    }

}
