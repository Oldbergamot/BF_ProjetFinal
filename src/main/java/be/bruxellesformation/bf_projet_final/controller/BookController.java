package be.bruxellesformation.bf_projet_final.controller;

import be.bruxellesformation.bf_projet_final.model.dto.BookDTO;
import be.bruxellesformation.bf_projet_final.model.dto.ReviewDTO;
import be.bruxellesformation.bf_projet_final.model.form.book.AddBookForm;
import be.bruxellesformation.bf_projet_final.model.form.book.ModifyBookForm;
import be.bruxellesformation.bf_projet_final.service.BookService;
import org.springframework.data.domain.Page;
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

    @GetMapping("/{book}")
    public ResponseEntity<BookDTO>getOne(@PathVariable(value="book") Long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @PutMapping("/{book}")
    public ResponseEntity<BookDTO>modifyOne(@PathVariable(value="book") Long id, @Valid @RequestBody ModifyBookForm form){
        return ResponseEntity.ok(service.modifyOne(id, form));
    }

    @GetMapping("/name={name}")
    public ResponseEntity<List<BookDTO>>getOneByName(@PathVariable(value="name") String name) {
        return ResponseEntity.ok(service.getOneByName(name));
    }

    @GetMapping("/genre={genre}")
    public ResponseEntity<List<BookDTO>>getAllByGenre(@PathVariable(value="genre") Long id){
        return ResponseEntity.ok(service.getAllByGenre(id));
    }

    @GetMapping("/publisher={pub}")
    public ResponseEntity<List<BookDTO>>getAllByPublisher(@PathVariable(value="pub") Long id) {
        return ResponseEntity.ok(service.getAllByPublisher(id));
    }

    @GetMapping("/author={author}")
    public ResponseEntity<List<BookDTO>>getAllByAuthor(@PathVariable(value="author") Long id) {
        return ResponseEntity.ok(service.getAllByAuthor(id));
    }

    @GetMapping("/language={lang}")
    public ResponseEntity<List<BookDTO>>getAllByLanguage(@PathVariable(value="lang") Long id) {
        return ResponseEntity.ok(service.getAllByLanguage(id));
    }

    @GetMapping("/year={year}")
    public ResponseEntity<List<BookDTO>>getAllByYear(@PathVariable(value="year") int year) {
        return ResponseEntity.ok(service.getAllByPublishedYear(year));
    }

    @GetMapping("/review={review}")
    public ResponseEntity<List<ReviewDTO>>getReviews(@PathVariable(value="review") Long id) {
        return ResponseEntity.ok(service.getReviews(id));
    }

    @PatchMapping("/{book}/display={display}")
    public ResponseEntity<BookDTO>displayBook(@PathVariable(value="book") Long id,@PathVariable(value="display") boolean b){
        return ResponseEntity.ok(service.displayBook(id,b));
    }

    @PatchMapping("{book}/update/partial")
    public ResponseEntity<BookDTO>partialUpdate(@PathVariable(value="book") Long id,@Valid @RequestBody Map<String, Object> values){
        return ResponseEntity.ok(service.partialUpdate(id, values));
    }

    @GetMapping("/all")
    public  ResponseEntity<List<BookDTO>>getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("all/page={page}&size={size}")
    public ResponseEntity <Page<BookDTO>> getAllWithPagination(@PathVariable(value = "page") int page,@PathVariable (value = "size") int size){
        return  ResponseEntity.ok(service.getAllWithPagination(page,size));
    };
}
