package be.bruxellesformation.bf_projet_final.controller;

import be.bruxellesformation.bf_projet_final.model.dto.PublisherDTO;
import be.bruxellesformation.bf_projet_final.model.form.Publisher.AddPublisherForm;
import be.bruxellesformation.bf_projet_final.model.form.Publisher.ModifyPublisherForm;
import be.bruxellesformation.bf_projet_final.service.PublisherService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<PublisherDTO>insert(@Valid @RequestBody AddPublisherForm form) {
        return ResponseEntity.ok(service.insert(form));
    }

    @PutMapping("/{pub}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<PublisherDTO>modifyOne(@PathVariable(value = "pub") Long id, @Valid @RequestBody ModifyPublisherForm form) {
        return ResponseEntity.ok(service.modifyOne(id, form));
    }

    @GetMapping("/{pub}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<PublisherDTO>getOne(@PathVariable(value = "pub") Long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @PutMapping("/{pub}/display={display}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<PublisherDTO>displayPublisher(@PathVariable(value = "pub") Long id, @PathVariable(value = "display") boolean b) {
        return ResponseEntity.ok(service.displayPublisher(id, b));
    }

    @DeleteMapping("/{pub}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PublisherDTO>deletePublisher(@PathVariable(value = "pub") Long id) {
        return ResponseEntity.ok(service.deletePublisher(id));
    }


}
