package com.example.cms.utils.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass //JPA Entity 클래스들이 상속할 경우 필드들(createdAt, modifiedAt)도 컬럼으로 인식하도록 합
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseDateTimeEntity {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

}
