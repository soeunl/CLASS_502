package org.choongang.global.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter @Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity { // 추상클래스로 정의
    // @CreationTimestamp // Hibernate와 같은 JPA(Java Persistence API) 구현체에서 제공하는 애노테이션
    @CreatedDate // 스프링 표준적인걸로 바꿨다.
    @Column(updatable = false) // 절대 수정되면 안되므로!
    // insert만 가능. update는 안된다.
    private LocalDateTime createdAt;

    // @UpdateTimestamp // Hibernate와 같은 JPA(Java Persistence API) 구현체에서 제공하는 애노테이션
    @LastModifiedDate // 스프링 표준적인걸로 바꿨다.
    @Column(insertable = false) // 수정 될때만 값이 필요하므로!
    // update만 가능하고 insert는 안된다.
    private LocalDateTime modifiedAt;
}
// 항상 공통을 나올 부분이면 상속을 받아서 하는 것이 좋다.
// 공통 속성화!
// 애노테이션으로 공통 속성이라는 것을 알려주어야 한다.
