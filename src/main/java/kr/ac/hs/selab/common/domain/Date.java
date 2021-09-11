package kr.ac.hs.selab.common.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Date {
    @CreatedDate
    @Column(name = "create_date")
    protected LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "modified_date")
    protected LocalDateTime modifiedDate;
}
