package be.bruxellesformation.bf_projet_final.service;

import be.bruxellesformation.bf_projet_final.model.dto.LanguageDTO;
import be.bruxellesformation.bf_projet_final.model.form.Language.AddLanguageForm;
import be.bruxellesformation.bf_projet_final.model.form.Language.ModifyLanguageForm;

public interface LanguageService {

    LanguageDTO insert(AddLanguageForm form);
    LanguageDTO modifyOne(Long id, ModifyLanguageForm form);
    LanguageDTO getOne(Long id);
    LanguageDTO displayLanguage(Long idLanguage, boolean b);

}
