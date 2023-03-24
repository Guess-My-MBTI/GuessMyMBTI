package com.hsstudy.GuessMyMBTI.api.domain.guest;

import com.hsstudy.GuessMyMBTI.api.domain.account.Account;
import com.hsstudy.GuessMyMBTI.api.domain.account.Authority;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GuestDto {
    private Long guestId;
    private String nickname;
    private Authority role;
    private Account owner;
    private String answer;
    private String comment;
    private String accuracy;
    private String result;

}
