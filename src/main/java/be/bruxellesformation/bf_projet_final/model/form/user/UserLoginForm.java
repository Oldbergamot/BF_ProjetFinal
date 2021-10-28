package be.bruxellesformation.bf_projet_final.model.form.user;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class UserLoginForm {
    @Length(min = 8)
    @NotBlank
    private String username;
    @Length(min = 8, max = 64)
    @NotBlank
    private String password;

}
