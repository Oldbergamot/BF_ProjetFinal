package be.bruxellesformation.bf_projet_final.controller;

import be.bruxellesformation.bf_projet_final.model.dto.BookDTO;
import be.bruxellesformation.bf_projet_final.model.dto.ReviewDTO;
import be.bruxellesformation.bf_projet_final.model.form.book.AddBookForm;
import be.bruxellesformation.bf_projet_final.model.form.book.ModifyBookForm;
import be.bruxellesformation.bf_projet_final.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BookDTO>insertOne(AddBookForm form){
        return ResponseEntity.ok(service.insertOne(form));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO>getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO>modifyOne(@PathVariable Long id, @Valid @RequestBody ModifyBookForm form){
        return ResponseEntity.ok(service.modifyOne(id, form));
    }

    @GetMapping("/{name}")
    public ResponseEntity<BookDTO>getOneByName(@PathVariable String name) {
        return ResponseEntity.ok(service.getOneByName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<BookDTO>>getAllByGenre(@PathVariable Long id){
        return ResponseEntity.ok(service.getAllByGenre(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<BookDTO>>getAllByPublisher(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAllByPublisher(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<BookDTO>>getAllByAuthor(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAllByAuthor(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<BookDTO>>getAllByLanguage(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAllByLanguage(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<BookDTO>>getAllByYear(@PathVariable int year) {
        return ResponseEntity.ok(service.getAllByPublishedYear(year));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ReviewDTO>>getReviews(@PathVariable Long id) {
        return ResponseEntity.ok(service.getReviews(id));
    }

    @PutMapping("/{id}_{b}")
    public ResponseEntity<BookDTO>displayBook(@PathVariable Long id , boolean b){
        return ResponseEntity.ok(service.displayBook(id,b));
    }
}
