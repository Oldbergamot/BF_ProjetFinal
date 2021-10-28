package be.bruxellesformation.bf_projet_final.model.dto;

import be.bruxellesformation.bf_projet_final.model.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String biography;
    private List<Genre> genres;

}
