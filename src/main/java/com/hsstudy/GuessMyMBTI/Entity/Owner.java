package com.hsstudy.GuessMyMBTI.Entity;

import lombok.*;

import javax.persistence.*;

// DB테이블과 매핑

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
