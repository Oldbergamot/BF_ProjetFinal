package be.bruxellesformation.bf_projet_final.controller;

import be.bruxellesformation.bf_projet_final.model.dto.AuthorDTO;
import be.bruxellesformation.bf_projet_final.model.form.author.AddAuthorForm;
import be.bruxellesformation.bf_projet_final.model.form.author.ModifyAuthorForm;
import be.bruxellesformation.bf_projet_final.service.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AuthorDTO>insert(@Valid @RequestBody AddAuthorForm form){
        return ResponseEntity.ok(service.insert(form));
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<AuthorDTO>modifyOne(@PathVariable Long id, @Valid @RequestBody ModifyAuthorForm form) {
        return ResponseEntity.ok(service.modifyOne(id, form));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AuthorDTO>getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @PatchMapping("/display/{id}_{b}")
    public ResponseEntity<AuthorDTO>displayOne(@PathVariable Long id, @PathVariable boolean b){
        return ResponseEntity.ok(service.displayAuthor(id, b));
    }

    @PatchMapping("/update/partial/{id}")
    public ResponseEntity<AuthorDTO>updatePartial(@PathVariable Long id, @Valid@RequestBody Map<String, Object> values){
        return ResponseEntity.ok(service.partialUpdate(id, values));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<AuthorDTO>>getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/get/all/page/{page}_{size}")
    public ResponseEntity<Page<AuthorDTO>> getAllWithPagination( @PathVariable int page, @PathVariable int size){
        return ResponseEntity.ok(service.getAllWithPagination(page, size));
    }

}
