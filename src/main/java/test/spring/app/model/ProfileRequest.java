package test.spring.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequest {

    @NotNull
    private String password;

    @NotNull
    private String username;

    @NotNull
    @Size(max = 32, min = 5, message = "Username must be between 5 and 32 characters")
    private String name;

    @Size(max = 255, min = 3, message = "Full name must be between 3 and 255 characters")
    private String surname;

    @NotBlank(message = "bank account number id cannot be null")
    private Long bankAccount;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private String birthDate;

    private Long balance;

    private List<String> credits;
}
