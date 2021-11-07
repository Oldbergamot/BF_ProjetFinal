package be.bruxellesformation.bf_projet_final.service;

import be.bruxellesformation.bf_projet_final.model.dto.AuthorDTO;
import be.bruxellesformation.bf_projet_final.model.dto.LanguageDTO;
import be.bruxellesformation.bf_projet_final.model.entity.Language;
import be.bruxellesformation.bf_projet_final.model.form.Language.AddLanguageForm;
import be.bruxellesformation.bf_projet_final.model.form.Language.ModifyLanguageForm;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface LanguageService {

    LanguageDTO insert(AddLanguageForm form);
    LanguageDTO modifyOne(Long id, ModifyLanguageForm form);
    LanguageDTO getOne(Long id);
    LanguageDTO displayLanguage(Long idLanguage, boolean b);

    List<LanguageDTO> getAll();
    Page<LanguageDTO> getAllWithPagination(int page, int size);
    LanguageDTO partialUpdate(Long id, Map<String, Object> values);

}
