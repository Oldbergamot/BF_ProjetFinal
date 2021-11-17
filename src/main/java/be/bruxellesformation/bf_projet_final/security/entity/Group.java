package be.bruxellesformation.bf_projet_final.security.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@EqualsAndHashCode
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "security_group")
public class Group implements GrantedAuthority, Serializable {

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

    @ManyToMany(targetEntity = Role.class, cascade = {CascadeType.REFRESH})
    @Singular
    @Getter @Setter
    private Set<Role> roles;

    @Override
    public String getAuthority() {
        return label;
    }
}
