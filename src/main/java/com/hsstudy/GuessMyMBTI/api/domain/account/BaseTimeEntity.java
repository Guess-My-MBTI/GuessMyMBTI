package com.hsstudy.GuessMyMBTI.api.domain.account;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity 를 상속할 경우 필드들(createDate, modifiedDate)도 컬럼으로 인식하도록 합니다.
@EntityListeners(AuditingEntityListener.class) // BaseTimeEntity 클래스 Auditing 기능을 포함시킵니다.
public abstract class BaseTimeEntity {

    @CreatedDate // Entity 가 생성되어 저장될때 시간이 자동 저장됩니다.
    private LocalDateTime createdDate;

    @LastModifiedDate // 조회한 Entity 의 값을 변경할 떄 시간이 자동 저장됩니다.
    private LocalDateTime modifiedDate;

    /* 그리고 Posts 클래스가 BaseTimeEntity 를 상속받도록 변경합니다. */
}