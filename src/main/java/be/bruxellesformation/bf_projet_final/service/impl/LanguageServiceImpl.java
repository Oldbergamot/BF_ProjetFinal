package be.bruxellesformation.bf_projet_final.service.impl;

import be.bruxellesformation.bf_projet_final.exception.model.LanguageNotFoundException;
import be.bruxellesformation.bf_projet_final.mapper.LanguageMapper;
import be.bruxellesformation.bf_projet_final.model.dto.LanguageDTO;
import be.bruxellesformation.bf_projet_final.model.entity.Language;
import be.bruxellesformation.bf_projet_final.model.form.Language.AddLanguageForm;
import be.bruxellesformation.bf_projet_final.model.form.Language.ModifyLanguageForm;
import be.bruxellesformation.bf_projet_final.repository.LanguageRepository;
import be.bruxellesformation.bf_projet_final.service.LanguageService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository repository;
    private final LanguageMapper mapper;

    public LanguageServiceImpl(LanguageRepository repository, LanguageMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public LanguageDTO insert(AddLanguageForm form) {
        Language l = mapper.formAddLanguageFormToEntity(form);
        repository.save(l);
        return mapper.toDto(l);
    }

    @Override
    public LanguageDTO modifyOne(Long id, ModifyLanguageForm form) {
        Language l = repository.findById(id).orElseThrow(() -> new LanguageNotFoundException(id));
        l.setName(form.getName());
        repository.save(l);
        return mapper.toDto(l);
    }

    @Override
    public LanguageDTO getOne(Long id) {
        Language l = repository.findById(id).orElseThrow(() -> new LanguageNotFoundException(id));
        return mapper.toDto(l);
    }

    @Override
    public LanguageDTO displayLanguage(Long idLanguage, boolean b) {
        Language l = repository.findById(idLanguage).orElseThrow(()-> new LanguageNotFoundException(idLanguage));
        l.setDisplay(b);
        repository.save(l);
        return mapper.toDto(l);
    }

    @Override
    public List<LanguageDTO> getAll() {
        return null;
    }

    @Override
    public Page<LanguageDTO> getAllWithPagination(int page, int size) {
        return null;
    }

    @Override
    public LanguageDTO partialUpdate(Long id, Map<String, Object> values) {
        return null;
    }
}
