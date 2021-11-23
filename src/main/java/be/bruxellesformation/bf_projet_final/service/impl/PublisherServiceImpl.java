package be.bruxellesformation.bf_projet_final.service.impl;

import be.bruxellesformation.bf_projet_final.exception.model.AuthorNotFoundException;
import be.bruxellesformation.bf_projet_final.exception.model.AuthorisationException;
import be.bruxellesformation.bf_projet_final.exception.model.PublisherNotFoundException;
import be.bruxellesformation.bf_projet_final.mapper.PublisherMapper;
import be.bruxellesformation.bf_projet_final.model.dto.PublisherDTO;
import be.bruxellesformation.bf_projet_final.model.entity.Genre;
import be.bruxellesformation.bf_projet_final.model.entity.Publisher;
import be.bruxellesformation.bf_projet_final.model.form.Publisher.AddPublisherForm;
import be.bruxellesformation.bf_projet_final.model.form.Publisher.ModifyPublisherForm;
import be.bruxellesformation.bf_projet_final.repository.PublisherRepository;
import be.bruxellesformation.bf_projet_final.service.PublisherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherMapper mapper;
    private final PublisherRepository repository;

    public PublisherServiceImpl(PublisherMapper mapper, PublisherRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public PublisherDTO insert(AddPublisherForm form) {
        Publisher p = mapper.fromFormToEntity(form);
        repository.save(p);
        return mapper.toDto(p);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public PublisherDTO modifyOne(Long id, ModifyPublisherForm form) {
        Publisher p = repository.findById(id).orElseThrow(() -> new PublisherNotFoundException(id));
        if(!p.isDisplay()&&!isRoleAdmin()) throw new AuthorisationException(id);
        p.setName(form.getName());
        repository.save(p);
        return mapper.toDto(p);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public PublisherDTO getOne(Long id) {
        Publisher p = repository.findById(id).orElseThrow(() -> new PublisherNotFoundException(id));
        if(!p.isDisplay()&&!isRoleAdmin()) throw new AuthorisationException(id);
        return mapper.toDto(p);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PublisherDTO displayPublisher(Long idPublisher, boolean b) {
        Publisher p = repository.findById(idPublisher).orElseThrow(() -> new PublisherNotFoundException(idPublisher));
        p.setDisplay(b);
        repository.save(p);
        return mapper.toDto(p);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<PublisherDTO> getAll() {
        if(isRoleUser()) return repository.findAll()
                .stream()
                .filter(Publisher::isDisplay)
                .map(mapper::toDto)
                .collect(Collectors.toList());

        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public Page<PublisherDTO> getAllWithPagination(int page, int size) {
        Page<Publisher> result;
        if(isRoleUser()) result = repository.findAllByDisplay(PageRequest.of(page, size));
        else result = repository.findAll(PageRequest.of(page, size));

        return result.map(mapper::toDto);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PublisherDTO deletePublisher(Long id) {
        Publisher toRemove = repository.findById(id).orElseThrow(() -> new PublisherNotFoundException(id));
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
