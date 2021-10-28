package be.bruxellesformation.bf_projet_final.model.form.user;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@Validated
public class UserRegisterForm {

    @Length(min = 8)
    private String username;
    @Email
    private String email;
    @Length(min = 8, max = 64)
    private String password;
}
