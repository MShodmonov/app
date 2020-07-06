package test.spring.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import test.spring.app.entity.enums.RoleEnumeration;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Role extends AbstractEntity implements GrantedAuthority {

    @Enumerated(EnumType.STRING)
    private RoleEnumeration name;

    @Override
    public String getAuthority() {
        return this.name.name();
    }
}
