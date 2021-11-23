package be.bruxellesformation.bf_projet_final.service.impl;

import be.bruxellesformation.bf_projet_final.exception.model.AuthorisationException;
import be.bruxellesformation.bf_projet_final.exception.model.GenreNotFoundException;
import be.bruxellesformation.bf_projet_final.mapper.GenreMapper;
import be.bruxellesformation.bf_projet_final.model.dto.GenreDTO;
import be.bruxellesformation.bf_projet_final.model.entity.Author;
import be.bruxellesformation.bf_projet_final.model.entity.Genre;
import be.bruxellesformation.bf_projet_final.model.form.genre.AddGenreForm;
import be.bruxellesformation.bf_projet_final.model.form.genre.ModifyGenreForm;
import be.bruxellesformation.bf_projet_final.repository.GenreRepository;
import be.bruxellesformation.bf_projet_final.service.GenreService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;
    private final GenreMapper mapper;

    public GenreServiceImpl(GenreRepository repository, GenreMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public GenreDTO insert(AddGenreForm form) {
        Genre g = mapper.fromFormToEntity(form);
        repository.save(g);
        return mapper.toDto(g);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public GenreDTO modifyOne(Long id, ModifyGenreForm form) {
        Genre g = repository.findById(id).orElseThrow(() -> new GenreNotFoundException(id));
        if(!g.isDisplay() && !isRoleAdmin()) throw new AuthorisationException(id);
        g.setName(form.getName());
        repository.save(g);
        return mapper.toDto(g);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public GenreDTO getOne(Long id) {
        Genre g = repository.findById(id).orElseThrow(() -> new GenreNotFoundException(id));
        if(!g.isDisplay() && !isRoleAdmin()) throw new AuthorisationException(id);
        return mapper.toDto(g);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public GenreDTO displayGenre(Long idGenre, boolean b) {
        Genre g = repository.findById(idGenre).orElseThrow(() -> new GenreNotFoundException(idGenre));
        g.setDisplay(b);
        repository.save(g);
        return mapper.toDto(g);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<GenreDTO> getAll() {
        if(isRoleUser()) return repository.findAll()
                .stream()
                .filter(Genre::isDisplay)
                .map(mapper::toDto)
                .collect(Collectors.toList());

        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public Page<GenreDTO> getAllWithPagination(int page, int size) {
        Page<Genre> result;
        if(isRoleUser()) result = repository.findAllByDisplay(PageRequest.of(page, size));
        else result = repository.findAll(PageRequest.of(page, size));

        return result.map(mapper::toDto);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public GenreDTO deleteGenre(Long id) {
        Genre toRemove = repository.findById(id).orElseThrow(() -> new GenreNotFoundException(id));
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
