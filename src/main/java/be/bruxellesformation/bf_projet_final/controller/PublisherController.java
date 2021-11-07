package be.bruxellesformation.bf_projet_final.controller;

import be.bruxellesformation.bf_projet_final.model.dto.PublisherDTO;
import be.bruxellesformation.bf_projet_final.model.form.Publisher.AddPublisherForm;
import be.bruxellesformation.bf_projet_final.model.form.Publisher.ModifyPublisherForm;
import be.bruxellesformation.bf_projet_final.service.PublisherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

    private final PublisherService service;

    public PublisherController(PublisherService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PublisherDTO>insert(@Valid @RequestBody AddPublisherForm form) {
        return ResponseEntity.ok(service.insert(form));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherDTO>modifyOne(@PathVariable Long id, @Valid @RequestBody ModifyPublisherForm form) {
        return ResponseEntity.ok(service.modifyOne(id, form));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO>getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @PutMapping("/{id}_{b}")
    public ResponseEntity<PublisherDTO>displayPublisher(@PathVariable Long id, @PathVariable boolean b) {
        return ResponseEntity.ok(service.displayPublisher(id, b));
    }


}
