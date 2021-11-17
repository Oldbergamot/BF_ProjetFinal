package be.bruxellesformation.bf_projet_final.security.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class LoginDTO {
    @Getter
    @Setter
    private String username;
    @Getter @Setter
    private String password;
}
