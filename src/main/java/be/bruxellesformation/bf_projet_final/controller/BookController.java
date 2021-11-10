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
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BookDTO>insertOne(@RequestBody AddBookForm form){
        return ResponseEntity.ok(service.insertOne(form));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BookDTO>getOne(@PathVariable(value="id") Long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<BookDTO>modifyOne(@PathVariable(value="id") Long id, @Valid @RequestBody ModifyBookForm form){
        return ResponseEntity.ok(service.modifyOne(id, form));
    }

    @GetMapping("/get/name/{name}")
    public ResponseEntity<List<BookDTO>>getOneByName(@PathVariable(value="name") String name) {
        return ResponseEntity.ok(service.getOneByName(name));
    }

    @GetMapping("/get/genre/{id}")
    public ResponseEntity<List<BookDTO>>getAllByGenre(@PathVariable(value="id") Long id){
        return ResponseEntity.ok(service.getAllByGenre(id));
    }

    @GetMapping("/get/pub/{id}")
    public ResponseEntity<List<BookDTO>>getAllByPublisher(@PathVariable(value="id") Long id) {
        return ResponseEntity.ok(service.getAllByPublisher(id));
    }

    @GetMapping("/get/author/{id}")
    public ResponseEntity<List<BookDTO>>getAllByAuthor(@PathVariable(value="id") Long id) {
        return ResponseEntity.ok(service.getAllByAuthor(id));
    }

    @GetMapping("/get/lang/{id}")
    public ResponseEntity<List<BookDTO>>getAllByLanguage(@PathVariable(value="id") Long id) {
        return ResponseEntity.ok(service.getAllByLanguage(id));
    }

    @GetMapping("/get/year/{year}")
    public ResponseEntity<List<BookDTO>>getAllByYear(@PathVariable(value="year") int year) {
        return ResponseEntity.ok(service.getAllByPublishedYear(year));
    }

    @GetMapping("/get/reviews/{id}")
    public ResponseEntity<List<ReviewDTO>>getReviews(@PathVariable(value="id") Long id) {
        return ResponseEntity.ok(service.getReviews(id));
    }

    @PutMapping("/display/{id}_{b}")
    public ResponseEntity<BookDTO>displayBook(@PathVariable(value="id") Long id,@PathVariable(value="b") boolean b){
        return ResponseEntity.ok(service.displayBook(id,b));
    }

    @PutMapping("/update/partial/{id}")
    public ResponseEntity<BookDTO>partialUpdate(@PathVariable(value="id") Long id,@Valid @RequestBody Map<String, Object> values){
        return ResponseEntity.ok(service.partialUpdate(id, values));
    }

}
