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
@MappedSuperclass // 테이블로 매핑되지 않고, 하위 엔티티에 상속되어 사용되는 임베디드 클래스임을 나타냄
@EntityListeners(AuditingEntityListener.class) // JPA Auditing 기능을 활성화하여 엔티티의 생성 및 수정 시간을 자동으로 관리
public abstract class BaseEntity { // 추상클래스로 정의, 엔티티들의 공통적인 속성인 생성 시간과 수정 시간을 관리하는 기반 엔티티
    // @CreationTimestamp // Hibernate와 같은 JPA(Java Persistence API) 구현체에서 제공하는 애노테이션
    @CreatedDate // 스프링 표준적인걸로 바꿨다. 엔티티 생성 시 자동으로 설정되는 생성 시간 필드
    @Column(updatable = false) // 절대 수정되면 안되므로!
    // insert만 가능. update는 안된다.
    private LocalDateTime createdAt;

    // @UpdateTimestamp // Hibernate와 같은 JPA(Java Persistence API) 구현체에서 제공하는 애노테이션
    @LastModifiedDate // 스프링 표준적인걸로 바꿨다. 수정 시 자동으로 설정되는 시간 필드
    @Column(insertable = false) // 수정 될때만 값이 필요하므로!
    // update만 가능하고 insert는 안된다.
    private LocalDateTime modifiedAt;
}
// 생성 시간과 수정 시간이라는 공통 필드를 BaseEntity에 정의
// JPA Auditing 기능을 활용하여 생성 시간과 수정 시간을 자동으로 업데이트
// 항상 공통을 나올 부분이면 상속을 받아서 하는 것이 좋다.
// 공통 속성화! 애노테이션으로 공통 속성이라는 것을 알려주어야 한다.
