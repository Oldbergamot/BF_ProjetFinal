package be.bruxellesformation.bf_projet_final.mapper;

import be.bruxellesformation.bf_projet_final.model.dto.LanguageDTO;
import be.bruxellesformation.bf_projet_final.model.entity.Language;
import be.bruxellesformation.bf_projet_final.model.form.Language.AddLanguageForm;
import be.bruxellesformation.bf_projet_final.model.form.Language.ModifyLanguageForm;
import org.springframework.stereotype.Service;

@Service
public class LanguageMapper implements BaseMapper<LanguageDTO, AddLanguageForm, Language>{

    public LanguageDTO toDto(Language entity) {
        if(entity == null) return null;
        return LanguageDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public Language toEntity(LanguageDTO dto) {
        if(dto==null) return null;
        return Language.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    public Language fromFormToEntity(AddLanguageForm form) {
        if (form == null) return null;
        Language language = new Language();
        language.setName(form.getName());

        return language;
    }

    public Language formModifyLanguageFormToEntity(ModifyLanguageForm form) {
        if (form == null) return null;
        Language language = new Language();
        language.setName(form.getName());

        return language;
    }
}
