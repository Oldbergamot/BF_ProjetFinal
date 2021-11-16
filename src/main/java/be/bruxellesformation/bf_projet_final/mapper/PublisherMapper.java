package be.bruxellesformation.bf_projet_final.mapper;

import be.bruxellesformation.bf_projet_final.model.dto.PublisherDTO;
import be.bruxellesformation.bf_projet_final.model.entity.Publisher;
import be.bruxellesformation.bf_projet_final.model.form.Publisher.AddPublisherForm;
import be.bruxellesformation.bf_projet_final.model.form.Publisher.ModifyPublisherForm;
import org.springframework.stereotype.Service;

@Service
public class PublisherMapper implements BaseMapper <PublisherDTO,AddPublisherForm,Publisher>{

    public Publisher toEntity(PublisherDTO dto) {
        if (dto == null) return null;
        return Publisher.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    public PublisherDTO toDto(Publisher entity){
        if(entity == null) return null;
        return PublisherDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public Publisher fromFormToEntity(AddPublisherForm form) {
        if (form == null) return null;
        Publisher publisher = new Publisher();
        publisher.setName(form.getName());
        return publisher;
    }

    public Publisher formModifyPublisherFormToEntity(ModifyPublisherForm form) {
        if (form == null) return null;
        Publisher publisher = new Publisher();
        publisher.setName(form.getName());
        return publisher;
    }

}
