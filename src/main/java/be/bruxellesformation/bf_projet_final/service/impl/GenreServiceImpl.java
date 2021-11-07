package be.bruxellesformation.bf_projet_final.service.impl;

import be.bruxellesformation.bf_projet_final.exceptions.GenreNotFoundException;
import be.bruxellesformation.bf_projet_final.mapper.GenreMapper;
import be.bruxellesformation.bf_projet_final.model.dto.GenreDTO;
import be.bruxellesformation.bf_projet_final.model.entity.Genre;
import be.bruxellesformation.bf_projet_final.model.form.genre.AddGenreForm;
import be.bruxellesformation.bf_projet_final.model.form.genre.ModifyGenreForm;
import be.bruxellesformation.bf_projet_final.repository.GenreRepository;
import be.bruxellesformation.bf_projet_final.service.GenreService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;
    private final GenreMapper mapper;

    public GenreServiceImpl(GenreRepository repository, GenreMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public GenreDTO insert(AddGenreForm form) {
        Genre g = mapper.fromAddGenreFormToEntity(form);
        repository.save(g);
        return mapper.toDto(g);
    }

    @Override
    public GenreDTO modifyOne(Long id, ModifyGenreForm form) {
        Genre g = repository.findById(id).orElseThrow(() -> new GenreNotFoundException(id));
        g.setName(form.getName());
        repository.save(g);
        return mapper.toDto(g);
    }

    @Override
    public GenreDTO getOne(Long id) {
        Genre g = repository.findById(id).orElseThrow(() -> new GenreNotFoundException(id));
        return mapper.toDto(g);
    }

    @Override
    public GenreDTO displayGenre(Long idGenre, boolean b) {
        Genre g = repository.findById(idGenre).orElseThrow(() -> new GenreNotFoundException(idGenre));
        g.setDisplay(b);
        repository.save(g);
        return mapper.toDto(g);
    }

    @Override
    public List<GenreDTO> getAll() {
        return null;
    }

    @Override
    public Page<GenreDTO> getAllWithPagination(int page, int size) {
        return null;
    }

    @Override
    public GenreDTO partialUpdate(Long id, Map<String, Object> values) {
        return null;
    }
}
