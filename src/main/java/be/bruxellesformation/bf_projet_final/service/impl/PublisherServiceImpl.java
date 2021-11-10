package be.bruxellesformation.bf_projet_final.service.impl;

import be.bruxellesformation.bf_projet_final.exception.model.PublisherNotFoundException;
import be.bruxellesformation.bf_projet_final.mapper.PublisherMapper;
import be.bruxellesformation.bf_projet_final.model.dto.PublisherDTO;
import be.bruxellesformation.bf_projet_final.model.entity.Publisher;
import be.bruxellesformation.bf_projet_final.model.form.Publisher.AddPublisherForm;
import be.bruxellesformation.bf_projet_final.model.form.Publisher.ModifyPublisherForm;
import be.bruxellesformation.bf_projet_final.repository.PublisherRepository;
import be.bruxellesformation.bf_projet_final.service.PublisherService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherMapper mapper;
    private final PublisherRepository repository;

    public PublisherServiceImpl(PublisherMapper mapper, PublisherRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public PublisherDTO insert(AddPublisherForm form) {
        Publisher p = mapper.formAdPublisherFormToEntity(form);
        repository.save(p);
        return mapper.toDto(p);
    }

    @Override
    public PublisherDTO modifyOne(Long id, ModifyPublisherForm form) {
        Publisher p = repository.findById(id).orElseThrow(() -> new PublisherNotFoundException(id));
        p.setName(form.getName());
        repository.save(p);
        return mapper.toDto(p);
    }

    @Override
    public PublisherDTO getOne(Long id) {
        Publisher p = repository.findById(id).orElseThrow(() -> new PublisherNotFoundException(id));
        return mapper.toDto(p);
    }

    @Override
    public PublisherDTO displayPublisher(Long idPublisher, boolean b) {
        Publisher p = repository.findById(idPublisher).orElseThrow(() -> new PublisherNotFoundException(idPublisher));
        p.setDisplay(b);
        repository.save(p);
        return mapper.toDto(p);
    }

    @Override
    public List<PublisherDTO> getAll() {
        return null;
    }

    @Override
    public Page<PublisherDTO> getAllWithPagination(int page, int size) {
        return null;
    }

    @Override
    public PublisherDTO partialUpdate(Long id, Map<String, Object> values) {
        return null;
    }
}
