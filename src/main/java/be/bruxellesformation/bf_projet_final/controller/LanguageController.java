package be.bruxellesformation.bf_projet_final.controller;

import be.bruxellesformation.bf_projet_final.model.dto.LanguageDTO;
import be.bruxellesformation.bf_projet_final.model.entity.Language;
import be.bruxellesformation.bf_projet_final.model.form.Language.AddLanguageForm;
import be.bruxellesformation.bf_projet_final.model.form.Language.ModifyLanguageForm;
import be.bruxellesformation.bf_projet_final.service.LanguageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/language")
public class LanguageController {

    private final LanguageService service;

    public LanguageController(LanguageService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LanguageDTO>insert(@RequestBody @Valid AddLanguageForm form) {
        return ResponseEntity.ok(service.insert(form));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LanguageDTO>modifyOne(@PathVariable Long id, @Valid @RequestBody ModifyLanguageForm form) {
        return ResponseEntity.ok(service.modifyOne(id, form));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LanguageDTO>getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @PutMapping("/{id}_{b}")
    public ResponseEntity<LanguageDTO>displayLanguage(@PathVariable Long id, @PathVariable boolean b) {
        return ResponseEntity.ok(service.displayLanguage(id, b));
    }
}
