package be.bruxellesformation.bf_projet_final.service;


import be.bruxellesformation.bf_projet_final.model.dto.BookDTO;
import be.bruxellesformation.bf_projet_final.model.dto.UserDTO;
import be.bruxellesformation.bf_projet_final.model.form.user.UserAddPrefForm;
import be.bruxellesformation.bf_projet_final.model.form.user.UserRegisterForm;
import be.bruxellesformation.bf_projet_final.model.form.user.UserUpdateForm;

import java.util.List;

public interface UserService{

    UserDTO insert(UserRegisterForm form);
    UserDTO getOne(Long id);
    List<UserDTO> getAll();
    UserDTO updateOne(Long id, UserUpdateForm form);
    UserDTO deleteOne(Long id);
    UserDTO updatePref(Long id, UserAddPrefForm form);
    UserDTO updateWishToRead(Long idUser, Long idBook);
    UserDTO updateHasRead(Long idUser, Long idBook);
    UserDTO removeFromList(Long idUser, Long idBook);
    List<BookDTO> getRecommandation(Long id);
}
