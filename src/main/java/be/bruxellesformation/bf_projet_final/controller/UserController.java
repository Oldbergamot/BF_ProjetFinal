package be.bruxellesformation.bf_projet_final.controller;

import be.bruxellesformation.bf_projet_final.model.dto.BookDTO;
import be.bruxellesformation.bf_projet_final.model.dto.UserDTO;
import be.bruxellesformation.bf_projet_final.model.form.user.UserAddPrefForm;
import be.bruxellesformation.bf_projet_final.model.form.user.UserRegisterForm;
import be.bruxellesformation.bf_projet_final.model.form.user.UserUpdateForm;
import be.bruxellesformation.bf_projet_final.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserDTO>insert(UserRegisterForm form) {
        return ResponseEntity.ok(service.insert(form));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO>getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>>getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO>updateOne(@PathVariable Long id, @Valid @RequestBody UserUpdateForm fom) {
        return ResponseEntity.ok(service.updateOne(id, fom));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO>deleteOne(@PathVariable Long id){
        return ResponseEntity.ok(service.deleteOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO>updatePref(@PathVariable Long id,@RequestBody @ Valid UserAddPrefForm form) {
        return ResponseEntity.ok(service.updatePref(id, form));
    }

    @PutMapping("/{idUser}_{idBook}")
    public ResponseEntity<UserDTO>addToWishToRead(@PathVariable Long idUser, @PathVariable Long idBook) {
        return ResponseEntity.ok(service.addToWishToRead(idUser, idBook));
    }

    @PutMapping("/{idUser}_{idBook}")
    public ResponseEntity<UserDTO>addToHasRead(@PathVariable Long idUser, @PathVariable Long idBook){
        return ResponseEntity.ok(service.addToHasRead(idUser, idBook));
    }

    @PutMapping("/{idUser}_{idBook}")
    public ResponseEntity<UserDTO>removeFromWishToRead(@PathVariable Long idUser, @PathVariable Long idBook){
        return ResponseEntity.ok(service.removeFromWishToRead(idUser, idBook));
    }

    @PutMapping("/{idUser}_{idBook}")
    public ResponseEntity<UserDTO>removeFromHasRead(@PathVariable Long idUser, @PathVariable Long idBook) {
        return ResponseEntity.ok(service.removeFromHasRead(idUser, idBook));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<BookDTO>>getGlobalRecommancdation(@PathVariable Long id) {
        return ResponseEntity.ok(service.getGlobalRecommandation(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<BookDTO>>getRecommandationOnGenre(@PathVariable Long id) {
        return ResponseEntity.ok(service.getRecommandationOnGenre(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<BookDTO>>getRecommandationOnAuthor(@PathVariable Long id) {
        return ResponseEntity.ok(service.getRecommandationOnAuthor(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<BookDTO>>getRecommandationOnPublisher(@PathVariable Long id) {
        return ResponseEntity.ok(service.getRecommandationOnPublisher(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<BookDTO>>getRecommandationOnLanguage(@PathVariable Long id) {
        return ResponseEntity.ok(service.getRecommandationOnLanguage(id));
    }




}
