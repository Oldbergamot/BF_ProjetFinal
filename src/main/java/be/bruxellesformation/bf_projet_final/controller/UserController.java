package be.bruxellesformation.bf_projet_final.controller;

import be.bruxellesformation.bf_projet_final.model.dto.BookDTO;
import be.bruxellesformation.bf_projet_final.model.dto.UserDTO;
import be.bruxellesformation.bf_projet_final.model.form.user.UserAddPrefForm;
import be.bruxellesformation.bf_projet_final.model.form.user.UserRegisterForm;
import be.bruxellesformation.bf_projet_final.model.form.user.UserUpdateForm;
import be.bruxellesformation.bf_projet_final.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDTO>getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>>getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO>updateOne(@PathVariable(value="id") Long id, @Valid @RequestBody UserUpdateForm fom) {
        return ResponseEntity.ok(service.updateOne(id, fom));
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<UserDTO>deleteOne(@PathVariable(value="id") Long id){
        return ResponseEntity.ok(service.deleteOne(id));
    }

    @PutMapping("/update/pref/{id}")
    public ResponseEntity<UserDTO>updatePref(@PathVariable(value="id") Long id,@RequestBody @Valid UserAddPrefForm form) {
        return ResponseEntity.ok(service.updatePref(id, form));
    }

    @PutMapping("/add/wishread/{idUser}_{idBook}")
    public ResponseEntity<UserDTO>addToWishToRead(@PathVariable(value="idUser") Long idUser,
                                                  @PathVariable(value="idBook") Long idBook) {
        return ResponseEntity.ok(service.addToWishToRead(idUser, idBook));
    }

    @PutMapping("/add/hasread/{idUser}_{idBook}")
    public ResponseEntity<UserDTO>addToHasRead(@PathVariable(value="idUser") Long idUser,
                                               @PathVariable(value="idBook") Long idBook){
        return ResponseEntity.ok(service.addToHasRead(idUser, idBook));
    }

    @PutMapping("/remove/wishread/{idUser}_{idBook}")
    public ResponseEntity<UserDTO>removeFromWishToRead(@PathVariable(value="idUser") Long idUser,
                                                       @PathVariable(value="idBook") Long idBook){
        return ResponseEntity.ok(service.removeFromWishToRead(idUser, idBook));
    }

    @PutMapping("/remove/hasread/{idUser}_{idBook}")
    public ResponseEntity<UserDTO>removeFromHasRead(@PathVariable(value="idUser") Long idUser,
                                                    @PathVariable(value="idBook") Long idBook) {
        return ResponseEntity.ok(service.removeFromHasRead(idUser, idBook));
    }

    @GetMapping("/recommandation/all/{id}")
    public ResponseEntity<List<BookDTO>>getGlobalRecommancdation(@PathVariable(value="id") Long id) {
        return ResponseEntity.ok(service.getGlobalRecommandation(id));
    }

    @GetMapping("/recommandation/genre/all/{id}")
    public ResponseEntity<List<BookDTO>>getRecommandationOnGenre(@PathVariable(value="id") Long id) {
        return ResponseEntity.ok(service.getRecommandationOnGenre(id));
    }

    @GetMapping("/recommandation/author/all/{id}")
    public ResponseEntity<List<BookDTO>>getRecommandationOnAuthor(@PathVariable(value="id") Long id) {
        return ResponseEntity.ok(service.getRecommandationOnAuthor(id));
    }

    @GetMapping("/recommandation/pub/all/{id}")
    public ResponseEntity<List<BookDTO>>getRecommandationOnPublisher(@PathVariable(value="id") Long id) {
        return ResponseEntity.ok(service.getRecommandationOnPublisher(id));
    }

    @GetMapping("/recommandation/lang/all/{id}")
    public ResponseEntity<List<BookDTO>>getRecommandationOnLanguage(@PathVariable(value="id") Long id) {
        return ResponseEntity.ok(service.getRecommandationOnLanguage(id));
    }

    @GetMapping("/get/recommandation/all/page/{id}_{page}_{size}")
    public ResponseEntity<Page<BookDTO>>getGlobalRecommandationWithPagination(@PathVariable(value="id") Long id,
                                                                              @PathVariable(value="page") int page,
                                                                              @PathVariable(value="size") int size){
        return ResponseEntity.ok(service.getGlobalRecommandationWithPagination(id,page,size));
    }

    @GetMapping("/get/recommandation/genre/page/{id}_{page}_{size}")
    public ResponseEntity<Page<BookDTO>>getRecommandationOnGenreWithPagination(@PathVariable(value="id") Long id,
                                                                               @PathVariable(value="page") int page,
                                                                               @PathVariable(value="size") int size) {
        return ResponseEntity.ok(service.getRecommandationOnGenreWithPagination(id, page, size));
    }

    @GetMapping("/get/recommandation/author/page/{id}_{page}_{size}")
    public ResponseEntity<Page<BookDTO>>getRecommandationOnAuthorWithPaginationr(@PathVariable(value="id") Long id,
                                                                                 @PathVariable(value="page") int page,
                                                                                 @PathVariable(value="size") int size) {
        return ResponseEntity.ok(service.getRecommandationOnAuthorWithPaginationr(id,page,size));
    }

    @GetMapping("/get/recommandation/publisher/page/{id}_{page}_{size}")
    public ResponseEntity<Page<BookDTO>>getRecommandationOnPublisherWithPagination(@PathVariable(value="id") Long id,
                                                                                   @PathVariable(value="page") int page,
                                                                                   @PathVariable(value="size") int size) {
        return ResponseEntity.ok(service.getRecommandationOnPublisherWithPagination(id,page,size));
    }

    @GetMapping("/get/recommandation/language/page/{id}_{page}_{size}")
    public ResponseEntity<Page<BookDTO>>getRecommandationOnLanguageWithPagination(@PathVariable(value="id") Long id,
                                                                                  @PathVariable(value="page") int page,
                                                                                  @PathVariable(value="size") int size) {
        return ResponseEntity.ok(service.getRecommandationOnLanguageWithPagination(id, page,size));
    }

    @PutMapping("/update/partial/{id}")
    public ResponseEntity<UserDTO>partialUpdate(@PathVariable(value="id") Long id,
                                                @RequestBody @Valid Map<String, Object> values) {
        return ResponseEntity.ok(service.partialUpdate(id, values));
    }

}
