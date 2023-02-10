package com.hsstudy.GuessMyMBTI.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OWNER_REFRESH_TOKEN")
public class OwnerRefreshToken {
    @JsonIgnore
    @Id
    @Column(name = "REFRESH_TOKEN_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refreshTokenSeq;

    @Column(name = "OWNER_ID")
    @NotNull
    private Long ownerId;

    @Column(name = "REFRESH_TOKEN", length = 256)
    @NotNull
    @Size(max = 256)
    private String refreshToken;

}
