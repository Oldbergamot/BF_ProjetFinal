package be.bruxellesformation.bf_projet_final.controller;

import be.bruxellesformation.bf_projet_final.model.dto.BookDTO;
import be.bruxellesformation.bf_projet_final.model.dto.ReviewDTO;
import be.bruxellesformation.bf_projet_final.model.form.book.AddBookForm;
import be.bruxellesformation.bf_projet_final.model.form.book.ModifyBookForm;
import be.bruxellesformation.bf_projet_final.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<BookDTO>insertOne(@RequestBody AddBookForm form){
        return ResponseEntity.ok(service.insertOne(form));
    }

    @GetMapping("/{book}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<BookDTO>getOne(@PathVariable(value="book") Long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @PutMapping("/{book}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<BookDTO>modifyOne(@PathVariable(value="book") Long id, @Valid @RequestBody ModifyBookForm form){
        return ResponseEntity.ok(service.modifyOne(id, form));
    }

    @GetMapping("/name={name}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<BookDTO>>getOneByName(@PathVariable(value="name") String name) {
        return ResponseEntity.ok(service.getOneByName(name));
    }

    @GetMapping("/genre={genre}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<BookDTO>>getAllByGenre(@PathVariable(value="genre") Long id){
        return ResponseEntity.ok(service.getAllByGenre(id));
    }

    @GetMapping("/publisher={pub}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<BookDTO>>getAllByPublisher(@PathVariable(value="pub") Long id) {
        return ResponseEntity.ok(service.getAllByPublisher(id));
    }

    @GetMapping("/author={author}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<BookDTO>>getAllByAuthor(@PathVariable(value="author") Long id) {
        return ResponseEntity.ok(service.getAllByAuthor(id));
    }

    @GetMapping("/language={lang}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<BookDTO>>getAllByLanguage(@PathVariable(value="lang") Long id) {
        return ResponseEntity.ok(service.getAllByLanguage(id));
    }

    @GetMapping("/year={year}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<BookDTO>>getAllByYear(@PathVariable(value="year") int year) {
        return ResponseEntity.ok(service.getAllByPublishedYear(year));
    }

    @GetMapping("/review={review}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<ReviewDTO>>getReviews(@PathVariable(value="review") Long id) {
        return ResponseEntity.ok(service.getReviews(id));
    }

    @PatchMapping("/{book}/display={display}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<BookDTO>displayBook(@PathVariable(value="book") Long id,@PathVariable(value="display") boolean b){
        return ResponseEntity.ok(service.displayBook(id,b));
    }

    @DeleteMapping("/{book}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<BookDTO>deleteBook(@PathVariable(value="book") Long id){
        return ResponseEntity.ok(service.deleteBook(id));
    }

    @PatchMapping("{book}/update/partial")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<BookDTO>partialUpdate(@PathVariable(value="book") Long id,@Valid @RequestBody Map<String, Object> values){
        return ResponseEntity.ok(service.partialUpdate(id, values));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public  ResponseEntity<List<BookDTO>>getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("all/page={page}&size={size}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity <Page<BookDTO>> getAllWithPagination(@PathVariable(value = "page") int page,@PathVariable (value = "size") int size){
        return  ResponseEntity.ok(service.getAllWithPagination(page,size));
    };
}
