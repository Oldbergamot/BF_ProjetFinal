package be.bruxellesformation.bf_projet_final.security.controller;

import be.bruxellesformation.bf_projet_final.model.dto.BookDTO;
import be.bruxellesformation.bf_projet_final.security.dto.UserDTO;
import be.bruxellesformation.bf_projet_final.model.form.user.UserAddPrefForm;
import be.bruxellesformation.bf_projet_final.model.form.user.UserRegisterForm;
import be.bruxellesformation.bf_projet_final.model.form.user.UserUpdateForm;
import be.bruxellesformation.bf_projet_final.security.entity.User;
import be.bruxellesformation.bf_projet_final.security.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserDTO>getOne(@PathVariable(value = "user") Long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserDTO>>getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{user}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserDTO>updateOne(@PathVariable(value="user") Long id, @Valid @RequestBody UserUpdateForm fom) {
        return ResponseEntity.ok(service.updateOne(id, fom));
    }

    @PatchMapping("{user}/preference/")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserDTO>updatePref(@PathVariable(value="user") Long id,@RequestBody @Valid UserAddPrefForm form) {
        return ResponseEntity.ok(service.updatePref(id, form));
    }

    @PatchMapping("/{user}/wishread")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserDTO>addToWishToRead(@PathVariable(value="user") Long idUser,
                                                  @RequestBody() Long book) {
        return ResponseEntity.ok(service.addToWishToRead(idUser, book));
    }

    @PatchMapping("/{user}/hasread")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserDTO>addToHasRead(@PathVariable(value="user") Long idUser,
                                               @RequestBody() Long idBook){
        return ResponseEntity.ok(service.addToHasRead(idUser, idBook));
    }

    @PatchMapping("/{user}/wishread/remove")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserDTO>removeFromWishToRead(@PathVariable(value="user") Long idUser,
                                                       @RequestBody() Long idBook){
        return ResponseEntity.ok(service.removeFromWishToRead(idUser, idBook));
    }

    @PatchMapping("/{user}/hasread/remove")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserDTO>removeFromHasRead(@PathVariable(value="user") Long idUser,
                                                    @RequestBody() Long idBook) {
        return ResponseEntity.ok(service.removeFromHasRead(idUser, idBook));
    }

//    @GetMapping("/{user}/recommandation")
//    public ResponseEntity<List<BookDTO>>getGlobalRecommancdation(@PathVariable(value="user") Long id) {
//        return ResponseEntity.ok(service.getGlobalRecommandation(id));
//    }

    @GetMapping("/{user}/recommandation/genre")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<BookDTO>>getRecommandationOnGenre(@PathVariable(value="user") Long id) {
        return ResponseEntity.ok(service.getRecommandationOnGenre(id));
    }

    @GetMapping("/{user}/recommandation/author")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<BookDTO>>getRecommandationOnAuthor(@PathVariable(value="user") Long id) {
        return ResponseEntity.ok(service.getRecommandationOnAuthor(id));
    }

    @GetMapping("/{user}/recommandation/publisher")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<BookDTO>>getRecommandationOnPublisher(@PathVariable(value="user") Long id) {
        return ResponseEntity.ok(service.getRecommandationOnPublisher(id));
    }

    @GetMapping("/{user}/recommandation/language")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<BookDTO>>getRecommandationOnLanguage(@PathVariable(value="user") Long id) {
        return ResponseEntity.ok(service.getRecommandationOnLanguage(id));
    }

    @GetMapping("/{user}/recommandation")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Page<BookDTO>>getGlobalRecommandationWithPagination(@PathVariable(value="user") Long id,
                                                                              @RequestParam(value="page", defaultValue = "0") int page,
                                                                              @RequestParam(value="size", defaultValue = "10") int size){
        return ResponseEntity.ok(service.getGlobalRecommandationWithPagination(id,page,size));
    }

    @GetMapping("/{user}/recommandation/genre?page={page}&size={size}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Page<BookDTO>>getRecommandationOnGenreWithPagination(@PathVariable(value="user") Long id,
                                                                               @PathVariable(value="page") int page,
                                                                               @PathVariable(value="size") int size) {
        return ResponseEntity.ok(service.getRecommandationOnGenreWithPagination(id, page, size));
    }

    @GetMapping("/{user}/recommandation/author?page={page}&size={size}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Page<BookDTO>>getRecommandationOnAuthorWithPaginationr(@PathVariable(value="user") Long id,
                                                                                 @PathVariable(value="page") int page,
                                                                                 @PathVariable(value="size") int size) {
        return ResponseEntity.ok(service.getRecommandationOnAuthorWithPaginationr(id,page,size));
    }

    @GetMapping(path = {"/{user}/recommandation/publisher?page={page}&size={size}"})
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Page<BookDTO>> getRecommandationOnPublisherWithPagination(@PathVariable(value="user") Long id,
                                                                                   @PathVariable(value="page") int page,
                                                                                   @PathVariable(value="size") int size) {
        return ResponseEntity.ok(service.getRecommandationOnPublisherWithPagination(id,page,size));
    }

    @GetMapping("/{user}/recommandation/language?page={page}&size={size}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Page<BookDTO>>getRecommandationOnLanguageWithPagination(@PathVariable(value="user") Long id,
                                                                                  @PathVariable(value="page") int page,
                                                                                  @PathVariable(value="size") int size) {
        return ResponseEntity.ok(service.getRecommandationOnLanguageWithPagination(id, page,size));
    }

    @PatchMapping("/{user}/update/partial/")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserDTO>partialUpdate(@PathVariable(value="user") Long id,
                                                @RequestBody @Valid Map<String, Object> values) {
        return ResponseEntity.ok(service.partialUpdate(id, values));
    }

    @PatchMapping("/{user}/display/display={b}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<UserDTO>displayOne(@PathVariable(value="user") Long id, @PathVariable boolean b) {
        return ResponseEntity.ok(service.displayOne(id,b));
    }

    @DeleteMapping("/{user}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDTO>deleteUser(@PathVariable(value="user") Long id) {
        return ResponseEntity.ok(service.deleteUser(id));
    }

    @PatchMapping("/{user}/locked={b}")
    @PreAuthorize("hasRole('ROLDE_ADMIN')")
    public ResponseEntity<UserDTO>setAccountLocked(@PathVariable(value = "user") Long id, @PathVariable(value = "b") boolean b) {
        return ResponseEntity.ok(service.setAccountLocked(id, b));
    }

    @PatchMapping("/{user}/expired={b}")
    @PreAuthorize("hasRole('ROLDE_ADMIN')")
    public ResponseEntity<UserDTO>setAccountExpired(@PathVariable(value = "user") Long id, @PathVariable(value = "b") boolean b) {
        return ResponseEntity.ok(service.setAccountExpired(id, b));
    }


}
