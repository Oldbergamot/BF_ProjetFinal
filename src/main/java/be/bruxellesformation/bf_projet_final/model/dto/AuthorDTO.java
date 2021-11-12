package be.bruxellesformation.bf_projet_final.model.dto;

import be.bruxellesformation.bf_projet_final.model.entity.Genre;
import lombok.*;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Getter @Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String biography;
    private List<Genre> genres;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDTO authorDTO = (AuthorDTO) o;
        return Objects.equals(id, authorDTO.id)
                && Objects.equals(firstName.toLowerCase(Locale.ROOT), authorDTO.firstName.toLowerCase(Locale.ROOT))
                && Objects.equals(lastName.toLowerCase(Locale.ROOT), authorDTO.lastName.toLowerCase(Locale.ROOT));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

}
