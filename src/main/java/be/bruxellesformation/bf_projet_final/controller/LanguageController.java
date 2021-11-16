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

    @PutMapping("/{lang}")
    public ResponseEntity<LanguageDTO>modifyOne(@PathVariable(value = "lang") Long id, @Valid @RequestBody ModifyLanguageForm form) {
        return ResponseEntity.ok(service.modifyOne(id, form));
    }

    @GetMapping("/{lang}")
    public ResponseEntity<LanguageDTO>getOne(@PathVariable(value = "lang") Long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @PutMapping("/{lang}/display={display}")
    public ResponseEntity<LanguageDTO>displayLanguage(@PathVariable(value = "lang") Long id, @PathVariable(value = "display") boolean b) {
        return ResponseEntity.ok(service.displayLanguage(id, b));
    }
}
