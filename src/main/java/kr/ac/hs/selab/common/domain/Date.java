package kr.ac.hs.selab.common.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Date {
    @CreatedDate
    @Column(name = "create_date")
    protected LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "modified_date")
    protected LocalDateTime modifiedDate;

    protected Date() {
    }
}
