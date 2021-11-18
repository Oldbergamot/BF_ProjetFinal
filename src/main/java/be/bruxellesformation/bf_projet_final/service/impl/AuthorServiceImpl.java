package be.bruxellesformation.bf_projet_final.service.impl;

import be.bruxellesformation.bf_projet_final.exception.model.AuthorNotFoundException;
import be.bruxellesformation.bf_projet_final.exception.model.AuthorisationException;
import be.bruxellesformation.bf_projet_final.mapper.AuthorMapper;
import be.bruxellesformation.bf_projet_final.model.dto.AuthorDTO;
import be.bruxellesformation.bf_projet_final.model.entity.Author;
import be.bruxellesformation.bf_projet_final.model.entity.Genre;
import be.bruxellesformation.bf_projet_final.model.form.author.AddAuthorForm;
import be.bruxellesformation.bf_projet_final.model.form.author.ModifyAuthorForm;
import be.bruxellesformation.bf_projet_final.repository.AuthorRepository;
import be.bruxellesformation.bf_projet_final.service.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorMapper mapper;
    private final AuthorRepository repository;

    public AuthorServiceImpl(AuthorMapper mapper, AuthorRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public AuthorDTO insert(AddAuthorForm form) {
        Author a = mapper.fromFormToEntity(form);
        repository.save(a);
        return mapper.toDto(a);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public AuthorDTO modifyOne(Long id, ModifyAuthorForm form) {
        Author a = repository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));

        if(isRoleUser() && !a.isDisplay()) throw new AuthorNotFoundException(id);

        a.setGenres(form.getGenres());
        a.setLastName(form.getLastName());
        a.setFirstName(form.getFirstName());
        a.setBiography(form.getBiography());
        repository.save(a);

        return mapper.toDto(a);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public AuthorDTO getOne(Long id) {
        Author a = repository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));

        if(!a.isDisplay() && !isRoleAdmin())throw new AuthorisationException(id);

        return mapper.toDto(a);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public AuthorDTO displayAuthor(Long idAuthor, boolean b) {
        Author a = repository.findById(idAuthor).orElseThrow(() -> new AuthorNotFoundException(idAuthor));
        a.setDisplay(b);
        repository.save(a);
        return mapper.toDto(a);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<AuthorDTO> getAll() {
    if(isRoleUser()) return repository.findAll()
                                .stream()
                                .filter(Author::isDisplay)
                                .map(mapper::toDto)
                                .collect(Collectors.toList());

        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public Page<AuthorDTO> getAllWithPagination(int page, int size) {
        Page<Author> result;
        if(isRoleUser()) result = repository.findAllByDisplay(PageRequest.of(page, size));
        else result = repository.findAll(PageRequest.of(page, size));

        return result.map(mapper::toDto);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public AuthorDTO partialUpdate(Long id, Map<String, Object> values) {
        Author author = repository.findById(id).orElseThrow(()->new AuthorNotFoundException(id));

        if(!author.isDisplay() && !isRoleAdmin()) throw new AuthorisationException(id);

        for(String s : values.keySet()){
            switch (s) {
                case "lastName":
                    String lastName = (String) values.get(s);
                    if(lastName == null || lastName.isBlank()){
                        throw new IllegalArgumentException("Invalid value for lastName : "+lastName);
                    }
                    author.setLastName(lastName);
                    break;
                case "firstName" :
                    String firstName = (String) values.get(s);
                    if(firstName == null || firstName.isBlank()) {
                        throw new IllegalArgumentException("Invalid value for firstname : "+firstName);
                    }
                    author.setFirstName(firstName);
                    break;
                case "genres" :
                    List<Genre> genres = (List<Genre>) values.get(s);
                    if(genres == null) throw new IllegalArgumentException("Invalid value for genres");
                    author.setGenres(genres);
                    break;
                case "biography" :
                    String biography = (String) values.get(s);
                    if(biography == null || biography.isBlank()) throw  new IllegalArgumentException("Invalid value for biography");
                    author.setBiography(biography);
                    break;
            }
        }
        author = repository.save(author);
        return mapper.toDto(author);

    }

    private boolean isRoleUser() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(role -> role.equals("ROLE_USER"));
    }
    private boolean isRoleAdmin() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(role -> role.equals("ROLE_ADMIN"));
    }
}
