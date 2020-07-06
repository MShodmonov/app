package test.spring.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount extends AbstractEntity {


    private Long number;

    private Long balance = 0L;

    @OneToMany(mappedBy = "bankAccounts")
    private List<BankCredits> bankCredits;


    public BankAccount(Long number, Long balance, List<BankCredits> bankCredits) {
        this.number = number;
        this.balance = balance;
        this.bankCredits = bankCredits;

    }

    @OneToOne
    @MapsId
    private User users;


}
