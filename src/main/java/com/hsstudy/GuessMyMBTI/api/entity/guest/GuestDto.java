package com.hsstudy.GuessMyMBTI.api.entity.guest;

import com.hsstudy.GuessMyMBTI.api.domain.Authority;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GuestDto {
    private Long guestId;
    private String nickname;
    private Authority role;
    private Long ownerId;
    private String mbti;
    private String answer;
    private String result;

}
