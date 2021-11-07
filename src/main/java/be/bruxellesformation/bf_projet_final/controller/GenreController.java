package be.bruxellesformation.bf_projet_final.controller;

import be.bruxellesformation.bf_projet_final.model.dto.GenreDTO;
import be.bruxellesformation.bf_projet_final.model.form.genre.AddGenreForm;
import be.bruxellesformation.bf_projet_final.model.form.genre.ModifyGenreForm;
import be.bruxellesformation.bf_projet_final.service.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/genre")
public class GenreController {

    private final GenreService service;

    public GenreController(GenreService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<GenreDTO>insert (@Valid @RequestBody AddGenreForm form) {
        return ResponseEntity.ok(service.insert(form));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO>modifyOne(@PathVariable Long id, @Valid @RequestBody ModifyGenreForm form) {
        return ResponseEntity.ok(service.modifyOne(id,form));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO>getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @PutMapping("/{id}_{b}")
    public ResponseEntity<GenreDTO>displayGenre(@PathVariable Long id, @PathVariable boolean b){
        return ResponseEntity.ok(service.displayGenre(id, b));
    }


}
