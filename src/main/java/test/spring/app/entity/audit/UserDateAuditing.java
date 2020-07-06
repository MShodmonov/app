package test.spring.app.entity.audit;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import test.spring.app.entity.User;


import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class UserDateAuditing extends DateAuditing{

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(updatable = false)
    private User createdBy;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    private User updatedBy;
}
