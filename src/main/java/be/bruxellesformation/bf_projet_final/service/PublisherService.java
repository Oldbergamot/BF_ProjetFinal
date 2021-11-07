package be.bruxellesformation.bf_projet_final.service;

import be.bruxellesformation.bf_projet_final.model.dto.AuthorDTO;
import be.bruxellesformation.bf_projet_final.model.dto.PublisherDTO;
import be.bruxellesformation.bf_projet_final.model.form.Publisher.AddPublisherForm;
import be.bruxellesformation.bf_projet_final.model.form.Publisher.ModifyPublisherForm;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface PublisherService {

    PublisherDTO insert (AddPublisherForm form);
    PublisherDTO modifyOne(Long id, ModifyPublisherForm form);
    PublisherDTO getOne(Long id);
    PublisherDTO displayPublisher(Long idPublisher, boolean b);

    List<PublisherDTO> getAll();
    Page<PublisherDTO> getAllWithPagination(int page, int size);
    PublisherDTO partialUpdate(Long id, Map<String, Object> values);
}
