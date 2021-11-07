package be.bruxellesformation.bf_projet_final.service.impl;

import be.bruxellesformation.bf_projet_final.exceptions.AuthorNotFoundException;
import be.bruxellesformation.bf_projet_final.mapper.AuthorMapper;
import be.bruxellesformation.bf_projet_final.model.dto.AuthorDTO;
import be.bruxellesformation.bf_projet_final.model.entity.Author;
import be.bruxellesformation.bf_projet_final.model.form.author.AddAuthorForm;
import be.bruxellesformation.bf_projet_final.model.form.author.ModifyAuthorForm;
import be.bruxellesformation.bf_projet_final.repository.AuthorRepository;
import be.bruxellesformation.bf_projet_final.service.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorMapper mapper;
    private final AuthorRepository repository;

    public AuthorServiceImpl(AuthorMapper mapper, AuthorRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public AuthorDTO insert(AddAuthorForm form) {
        Author a = mapper.fromAddAuthorFormToEntity(form);
        repository.save(a);
        return mapper.toDto(a);
    }

    @Override
    public AuthorDTO modifyOne(Long id, ModifyAuthorForm form) {
        Author a = repository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        a.setGenres(form.getGenres());
        a.setLastName(form.getLastName());
        a.setFirstName(form.getFirstName());
        a.setBiography(form.getBiography());
        repository.save(a);
        return mapper.toDto(a);
    }

    @Override
    public AuthorDTO getOne(Long id) {
        Author a = repository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        return mapper.toDto(a);
    }

    @Override
    public AuthorDTO displayAuthor(Long idAuthor, boolean b) {
        Author a = repository.findById(idAuthor).orElseThrow(() -> new AuthorNotFoundException(idAuthor));
        a.setDisplay(b);
        repository.save(a);
        return mapper.toDto(a);
    }

    @Override
    public List<AuthorDTO> getAll() {
        return null;
    }

    @Override
    public Page<AuthorDTO> getAllWithPagination(int page, int size) {
        return null;
    }

    @Override
    public AuthorDTO partialUpdate(Long id, Map<String, Object> values) {
        return null;
    }
}
