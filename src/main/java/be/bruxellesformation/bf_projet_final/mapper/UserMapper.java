package be.bruxellesformation.bf_projet_final.mapper;

import be.bruxellesformation.bf_projet_final.model.dto.UserDTO;
import be.bruxellesformation.bf_projet_final.model.entity.User;
import be.bruxellesformation.bf_projet_final.model.form.user.UserAddPrefForm;
import be.bruxellesformation.bf_projet_final.model.form.user.UserRegisterForm;
import org.springframework.stereotype.Service;

@Service
public class UserMapper{

    public UserDTO toDto(User entity){
        if (entity == null) return null;
        return UserDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .wishToRead(entity.getWishToRead())
                .hasRead(entity.getHasRead())
                .prefLang(entity.getPrefLang())
                .prefPub(entity.getPrefPub())
                .prefAuthor(entity.getPrefAuthor())
                .prefGenre(entity.getPrefGenre())
                .build();
    }

    public User toEntity(UserDTO dto) {
        if (dto == null) return null;

        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .wishToRead(dto.getWishToRead())
                .hasRead(dto.getHasRead())
                .prefAuthor(dto.getPrefAuthor())
                .prefGenre(dto.getPrefGenre())
                .prefLang(dto.getPrefLang())
                .prefPub(dto.getPrefPub())
                .build();
    }

    public User fromUserRegisterFormToEntity(UserRegisterForm form) {
        if (form == null) return null;
        User user = new User();
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        return user;
    }

    public User fromUserAddPrefFormToEntity(UserAddPrefForm form) {
        if(form == null) return null;
        User user = new User();
        user.setPrefAuthor(form.getPrefAuthor());
        user.setPrefGenre(form.getPrefGenre());
        user.setPrefPub(form.getPrefPub());
        user.setPrefLang(form.getPrefLang());

        return user;
    }
}
