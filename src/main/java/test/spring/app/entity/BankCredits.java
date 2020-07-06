package test.spring.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BankCredits extends AbstractEntity{


    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    private User user;

    private String creditName;


    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    private BankAccount bankAccounts;

}
