package com.hsstudy.GuessMyMBTI.api.domain.account.dto.kakao;

import lombok.*;

//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
@Data
public class KakaoTokenDto {

    /**
    카카오에서 보내는 access token 을 매핑하는 클래스다.
    직접 요청을 던져보고 파라미터를 따왔다.
    언젠가 깃허브에 코드가 올라가서 대박나서 보고 따라할 누군가에게 전한다...
    인가코드 받아와서 HTTP Request 완성한 후 서버에 띄워보고 정확한 필드명을 구해서 넣도록 할 것...
     */

    private String access_token;
    private String token_type;
    private String refresh_token;
    private String id_token;
    private int expires_in;
    private int refresh_token_expires_in;
    private String scope;

}