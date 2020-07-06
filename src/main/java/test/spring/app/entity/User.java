package test.spring.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractEntity implements UserDetails {


    private String username;

    private String password;

    private String name;
    private String surname;
    private Date birthday;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "users")
    //@JoinColumn(name = "bank_account_id",referencedColumnName = "id")
    private BankAccount bankAccount;



    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialNonExpired = true;
    private boolean isEnabled = true;


    public User(String username,String password,String name,String surname,Date birthday, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.roles = roles;
    }
    public User(String username,String password,String name,String surname,Date birthday, List<Role> roles,BankAccount bankAccount) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.roles = roles;
        this.bankAccount=bankAccount;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }



    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }


}
