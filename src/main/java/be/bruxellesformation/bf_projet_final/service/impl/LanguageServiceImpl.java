package be.bruxellesformation.bf_projet_final.service.impl;

import be.bruxellesformation.bf_projet_final.exception.model.LanguageNotFoundException;
import be.bruxellesformation.bf_projet_final.mapper.LanguageMapper;
import be.bruxellesformation.bf_projet_final.model.dto.LanguageDTO;
import be.bruxellesformation.bf_projet_final.model.entity.Genre;
import be.bruxellesformation.bf_projet_final.model.entity.Language;
import be.bruxellesformation.bf_projet_final.model.form.Language.AddLanguageForm;
import be.bruxellesformation.bf_projet_final.model.form.Language.ModifyLanguageForm;
import be.bruxellesformation.bf_projet_final.repository.LanguageRepository;
import be.bruxellesformation.bf_projet_final.service.LanguageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository repository;
    private final LanguageMapper mapper;

    public LanguageServiceImpl(LanguageRepository repository, LanguageMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public LanguageDTO insert(AddLanguageForm form) {
        Language l = mapper.fromFormToEntity(form);
        repository.save(l);
        return mapper.toDto(l);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public LanguageDTO modifyOne(Long id, ModifyLanguageForm form) {
        Language l = repository.findById(id).orElseThrow(() -> new LanguageNotFoundException(id));
        l.setName(form.getName());
        repository.save(l);
        return mapper.toDto(l);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public LanguageDTO getOne(Long id) {
        Language l = repository.findById(id).orElseThrow(() -> new LanguageNotFoundException(id));
        if(!l.isDisplay() && !isRoleAdmin()) throw new LanguageNotFoundException(id);

        return mapper.toDto(l);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public LanguageDTO displayLanguage(Long idLanguage, boolean b) {
        Language l = repository.findById(idLanguage).orElseThrow(()-> new LanguageNotFoundException(idLanguage));
        l.setDisplay(b);
        repository.save(l);
        return mapper.toDto(l);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<LanguageDTO> getAll() {
        if(isRoleUser()) return repository.findAll()
                .stream()
                .filter(Language::isDisplay)
                .map(mapper::toDto)
                .collect(Collectors.toList());

        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public Page<LanguageDTO> getAllWithPagination(int page, int size) {
        Page<Language> result;
        if(isRoleUser()) result = repository.findAllByDisplay(PageRequest.of(page, size));
        else result = repository.findAll(PageRequest.of(page, size));
        return result.map(mapper::toDto);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public LanguageDTO deleteLanguage(Long id) {
        Language toRemove = repository.findById(id).orElseThrow(() -> new LanguageNotFoundException(id));
        repository.delete(toRemove);
        return mapper.toDto(toRemove);
    }


    private boolean isRoleAdmin() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(role -> role.equals("ROLE_ADMIN"));
    }

    private boolean isRoleUser() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(role -> role.equals("ROLE_USER"));
    }
}
