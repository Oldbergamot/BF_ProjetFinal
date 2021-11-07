package be.bruxellesformation.bf_projet_final.service;


import be.bruxellesformation.bf_projet_final.model.dto.AuthorDTO;
import be.bruxellesformation.bf_projet_final.model.dto.BookDTO;
import be.bruxellesformation.bf_projet_final.model.dto.UserDTO;
import be.bruxellesformation.bf_projet_final.model.form.user.UserAddPrefForm;
import be.bruxellesformation.bf_projet_final.model.form.user.UserRegisterForm;
import be.bruxellesformation.bf_projet_final.model.form.user.UserUpdateForm;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface UserService{

    UserDTO insert(UserRegisterForm form);
    UserDTO getOne(Long id);
    List<UserDTO> getAll();
    UserDTO updateOne(Long id, UserUpdateForm form);
    UserDTO deleteOne(Long id);
    UserDTO updatePref(Long id, UserAddPrefForm form);
    UserDTO addToWishToRead(Long idUser, Long idBook);
    UserDTO addToHasRead(Long idUser, Long idBook);
    UserDTO removeFromWishToRead(Long idUser, Long idBook);
    UserDTO removeFromHasRead(Long idUser, Long idBook);
    List<BookDTO> getGlobalRecommandation(Long id);
    List<BookDTO> getRecommandationOnGenre(Long id);
    List<BookDTO> getRecommandationOnAuthor(Long id);
    List<BookDTO> getRecommandationOnPublisher(Long id);
    List<BookDTO> getRecommandationOnLanguage(Long id);

    Page<UserDTO> getAllWithPagination(int page, int size);
    UserDTO partialUpdate(Long id, Map<String, Object> values);
}
