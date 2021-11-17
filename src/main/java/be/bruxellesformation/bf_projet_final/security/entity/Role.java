package be.bruxellesformation.bf_projet_final.security.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@ToString
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "security_role")
public class Role implements GrantedAuthority, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private boolean isEnabled = true;

    @Getter @Setter
    private LocalDate createAt;

    @Getter @Setter
    private LocalDate updateAt;

    @Getter @Setter
    private String label;

    @Override
    public String getAuthority() {
        return label;
    }
}
