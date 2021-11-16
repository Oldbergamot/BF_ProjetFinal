package be.bruxellesformation.bf_projet_final.controller;

import be.bruxellesformation.bf_projet_final.model.dto.AuthorDTO;
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
    public ResponseEntity<UserDTO>insert(@Valid @RequestBody UserRegisterForm form) {
        return ResponseEntity.ok(service.insert(form));
    }

    @GetMapping("/{user}")
    public ResponseEntity<UserDTO>getOne(@PathVariable(value = "user") Long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @GetMapping("/all?")
    public ResponseEntity<List<UserDTO>>getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{user}")
    public ResponseEntity<UserDTO>updateOne(@PathVariable(value="user") Long id, @Valid @RequestBody UserUpdateForm fom) {
        return ResponseEntity.ok(service.updateOne(id, fom));
    }

//    @PatchMapping("/delete/{id}")
//    public ResponseEntity<UserDTO>deleteOne(@PathVariable(value="id") Long id){
//        return ResponseEntity.ok(service.deleteOne(id));
//    }

    @PatchMapping("{user}/preference/")
    public ResponseEntity<UserDTO>updatePref(@PathVariable(value="user") Long id,@RequestBody @Valid UserAddPrefForm form) {
        return ResponseEntity.ok(service.updatePref(id, form));
    }

    @PatchMapping("/{user}/wishread")
    public ResponseEntity<UserDTO>addToWishToRead(@PathVariable(value="user") Long idUser,
                                                  @RequestBody() Long book) {
        return ResponseEntity.ok(service.addToWishToRead(idUser, book));
    }

    @PatchMapping("/{user}/hasread")
    public ResponseEntity<UserDTO>addToHasRead(@PathVariable(value="user") Long idUser,
                                               @RequestBody() Long idBook){
        return ResponseEntity.ok(service.addToHasRead(idUser, idBook));
    }

    @PatchMapping("/{user}/wishread/remove")
    public ResponseEntity<UserDTO>removeFromWishToRead(@PathVariable(value="user") Long idUser,
                                                       @RequestBody() Long idBook){
        return ResponseEntity.ok(service.removeFromWishToRead(idUser, idBook));
    }

    @PatchMapping("/{user}/hasread/remove")
    public ResponseEntity<UserDTO>removeFromHasRead(@PathVariable(value="user") Long idUser,
                                                    @RequestBody() Long idBook) {
        return ResponseEntity.ok(service.removeFromHasRead(idUser, idBook));
    }

//    @GetMapping("/{user}/recommandation")
//    public ResponseEntity<List<BookDTO>>getGlobalRecommancdation(@PathVariable(value="user") Long id) {
//        return ResponseEntity.ok(service.getGlobalRecommandation(id));
//    }

    @GetMapping("/{user}/recommandation/genre")
    public ResponseEntity<List<BookDTO>>getRecommandationOnGenre(@PathVariable(value="user") Long id) {
        return ResponseEntity.ok(service.getRecommandationOnGenre(id));
    }

    @GetMapping("/{user}/recommandation/author")
    public ResponseEntity<List<BookDTO>>getRecommandationOnAuthor(@PathVariable(value="user") Long id) {
        return ResponseEntity.ok(service.getRecommandationOnAuthor(id));
    }

    @GetMapping("/{user}/recommandation/publisher")
    public ResponseEntity<List<BookDTO>>getRecommandationOnPublisher(@PathVariable(value="user") Long id) {
        return ResponseEntity.ok(service.getRecommandationOnPublisher(id));
    }

    @GetMapping("/{user}/recommandation/language")
    public ResponseEntity<List<BookDTO>>getRecommandationOnLanguage(@PathVariable(value="user") Long id) {
        return ResponseEntity.ok(service.getRecommandationOnLanguage(id));
    }

//    @GetMapping("/{user}/recommandation?page={page}&size={size}")
    @GetMapping("/{user}/recommandation")
    public ResponseEntity<Page<BookDTO>>getGlobalRecommandationWithPagination(@PathVariable(value="user") Long id,
                                                                              @RequestParam(value="page", defaultValue = "0") int page,
                                                                              @RequestParam(value="size", defaultValue = "10") int size){
        return ResponseEntity.ok(service.getGlobalRecommandationWithPagination(id,page,size));
    }

    @GetMapping("/{user}/recommandation/genre?page={page}&size={size}")
    public ResponseEntity<Page<BookDTO>>getRecommandationOnGenreWithPagination(@PathVariable(value="user") Long id,
                                                                               @PathVariable(value="page") int page,
                                                                               @PathVariable(value="size") int size) {
        return ResponseEntity.ok(service.getRecommandationOnGenreWithPagination(id, page, size));
    }

    @GetMapping("/{user}/recommandation/author?page={page}&size={size}")
    public ResponseEntity<Page<BookDTO>>getRecommandationOnAuthorWithPaginationr(@PathVariable(value="user") Long id,
                                                                                 @PathVariable(value="page") int page,
                                                                                 @PathVariable(value="size") int size) {
        return ResponseEntity.ok(service.getRecommandationOnAuthorWithPaginationr(id,page,size));
    }

    @GetMapping(path = {"/{user}/recommandation/publisher?page={page}&size={size}"})
    public ResponseEntity<Page<BookDTO>> getRecommandationOnPublisherWithPagination(@PathVariable(value="user") Long id,
                                                                                   @PathVariable(value="page") int page,
                                                                                   @PathVariable(value="size") int size) {
        return ResponseEntity.ok(service.getRecommandationOnPublisherWithPagination(id,page,size));
    }

    @GetMapping("/{user}/recommandation/language?page={page}&size={size}")
    public ResponseEntity<Page<BookDTO>>getRecommandationOnLanguageWithPagination(@PathVariable(value="user") Long id,
                                                                                  @PathVariable(value="page") int page,
                                                                                  @PathVariable(value="size") int size) {
        return ResponseEntity.ok(service.getRecommandationOnLanguageWithPagination(id, page,size));
    }

    @PatchMapping("/{user}/update/partial/")
    public ResponseEntity<UserDTO>partialUpdate(@PathVariable(value="user") Long id,
                                                @RequestBody @Valid Map<String, Object> values) {
        return ResponseEntity.ok(service.partialUpdate(id, values));
    }

    @PatchMapping("/{user}/display/display={b}")
    public ResponseEntity<UserDTO>displayOne(@PathVariable(value="user") Long id, @PathVariable boolean b) {
        return ResponseEntity.ok(service.displayOne(id,b));
    }

}
