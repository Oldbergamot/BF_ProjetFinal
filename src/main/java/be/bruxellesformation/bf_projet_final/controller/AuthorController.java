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

    @PutMapping("/{author}")
    public ResponseEntity<AuthorDTO>modifyOne(@PathVariable(value = "author") Long id, @Valid @RequestBody ModifyAuthorForm form) {
        return ResponseEntity.ok(service.modifyOne(id, form));
    }

    @GetMapping("/{author}")
    public ResponseEntity<AuthorDTO>getOne(@PathVariable(value = "author") Long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @PatchMapping("/{author}/display={display}")
    public ResponseEntity<AuthorDTO>displayOne(@PathVariable(value = "author") Long id, @PathVariable(value = "display") boolean b){
        return ResponseEntity.ok(service.displayAuthor(id, b));
    }

    @PatchMapping("/{author}/update/partial")
    public ResponseEntity<AuthorDTO>updatePartial(@PathVariable(value = "author") Long id, @Valid@RequestBody Map<String, Object> values){
        return ResponseEntity.ok(service.partialUpdate(id, values));
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>>getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/all/page={page}&size={size}")
    public ResponseEntity<Page<AuthorDTO>> getAllWithPagination( @PathVariable(value = "page") int page, @PathVariable (value = "size") int size){
        return ResponseEntity.ok(service.getAllWithPagination(page, size));
    }

}
