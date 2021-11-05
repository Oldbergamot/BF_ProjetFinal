package be.bruxellesformation.bf_projet_final.service;

import be.bruxellesformation.bf_projet_final.model.dto.PublisherDTO;
import be.bruxellesformation.bf_projet_final.model.form.Publisher.AddPublisherForm;
import be.bruxellesformation.bf_projet_final.model.form.Publisher.ModifyPublisherForm;

public interface PublisherService {

    PublisherDTO insert (AddPublisherForm form);
    PublisherDTO modifyOne(Long id, ModifyPublisherForm form);
    PublisherDTO getOne(Long id);
    PublisherDTO displayPublisher(Long idPublisher, boolean b);
}
