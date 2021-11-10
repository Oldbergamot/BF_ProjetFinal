package be.bruxellesformation.bf_projet_final.service.impl;

import be.bruxellesformation.bf_projet_final.exception.model.BookNotFoundException;
import be.bruxellesformation.bf_projet_final.mapper.BookMapper;
import be.bruxellesformation.bf_projet_final.mapper.ReviewMapper;
import be.bruxellesformation.bf_projet_final.model.dto.BookDTO;
import be.bruxellesformation.bf_projet_final.model.dto.ReviewDTO;
import be.bruxellesformation.bf_projet_final.model.entity.*;
import be.bruxellesformation.bf_projet_final.model.form.book.AddBookForm;
import be.bruxellesformation.bf_projet_final.model.form.book.ModifyBookForm;
import be.bruxellesformation.bf_projet_final.repository.*;
import be.bruxellesformation.bf_projet_final.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;
    private final ReviewMapper reviewMapper;
    private final BookRepository bookRepository;
    private final ReviewReposiroty reviewReposiroty;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final LanguageRepository languageRepository;

    public BookServiceImpl(BookMapper bookMapper, ReviewMapper reviewMapper, BookRepository bookRepository, ReviewReposiroty reviewReposiroty, GenreRepository genreRepository, AuthorRepository authorRepository, LanguageRepository languageRepository) {
        this.bookMapper = bookMapper;
        this.reviewMapper = reviewMapper;
        this.bookRepository = bookRepository;
        this.reviewReposiroty = reviewReposiroty;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
        this.languageRepository = languageRepository;
    }

    @Override
    public BookDTO insertOne(AddBookForm form) {
        Book book = bookMapper.fromAddBookFormToEntity(form);
//        book.setGgenreRepository.save(book.getGenre());

        Optional<Genre> optGenre = genreRepository.findById(form.getGenre().getId());
        optGenre.ifPresentOrElse(book::setGenre, () -> book.setGenre(genreRepository.save(form.getGenre())));

//        languageRepository.save(book.getLanguage());

        Optional<Language> optLang = languageRepository.findById(form.getLanguage().getId());
        optLang.ifPresentOrElse(book::setLanguage, () -> book.setLanguage(languageRepository.save(form.getLanguage())));

//        authorRepository.saveAll(book.getAuthors());
        List <Author> authorList = new ArrayList<>();

        for (int i = 0 ; i < form.getAuthors().size() ; i++) {
            Optional<Author> optAuth = authorRepository.findById(form.getAuthors().get(i).getId());
            if(optAuth.isPresent()) {
                authorList.add(form.getAuthors().get(i));
            }else {
                authorRepository.save(form.getAuthors().get(i));
            }
        }
        book.setAuthors(authorList);

        bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    @Override
    public BookDTO getOne(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        return bookMapper.toDto(book);
    }

    @Override
    public BookDTO modifyOne(Long id, ModifyBookForm form) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setSummary(form.getSummary());
        book.setName(form.getName());
        book.setPublishedDate(form.getPublishedDate());
        book.setLanguage(form.getLanguage());
        book.setAuthors(form.getAuthors());
        book.setGenre(form.getGenre());
        bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    @Override
    public List<BookDTO> getOneByName(String name) {
        List<Book> books = bookRepository.findBooksByName(name);
        return bookMapper.fromListEntityToDto(books);
    }

    @Override
    public List<BookDTO> getAllByGenre(Long idGenre) {
        List<Book> books = bookRepository.findBooksByGenreId(idGenre);
        return bookMapper.fromListEntityToDto(books);
    }

    @Override
    public List<BookDTO> getAllByAuthor(Long idAuthor) {
        List<Book> books = bookRepository.findBooksByAuthorsIn(idAuthor);

        return null;
    }

    @Override
    public List<BookDTO> getAllByPublisher(Long idPublisher) {
        List<Book>books = bookRepository.findBooksByPublisherId(idPublisher);
        return bookMapper.fromListEntityToDto(books);
    }

    @Override
    public List<BookDTO> getAllByLanguage(Long idLanguage) {
        List<Book>results = bookRepository.findBooksByLanguageId(idLanguage);
        return bookMapper.fromListEntityToDto(results);
    }

    @Override
    public List<BookDTO> getAllByPublishedYear(int year) {
        List<Book> books = bookRepository.findBooksByPublishedDateContains(year);
        return bookMapper.fromListEntityToDto(books);
    }

    @Override
    public List<ReviewDTO> getReviews(Long idBook) {
        Book book = bookRepository.findById(idBook).orElseThrow(()-> new BookNotFoundException(idBook));
        List<Review> reviews = reviewReposiroty.findReviewsByIsAbout(book);
        return reviewMapper.fromListEntityToDto(reviews);
    }

    @Override
    public BookDTO displayBook(Long id, boolean b) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        book.setDisplay(b);
        bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    @Override
    public List<BookDTO> getAll() {
        List<Book> books = bookRepository.findAll();
        return bookMapper.fromListEntityToDto(books);
    }

    @Override
    public Page<BookDTO> getAllWithPagination(int page, int size) {
        Page<Book> result = bookRepository.findAll(PageRequest.of(page, size));
        return result.map(bookMapper::toDto);
    }

    @Override
    public BookDTO partialUpdate(Long id, Map<String, Object> values) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        for(String s : values.keySet()){
            switch (s) {
                case "name" :
                    String name = (String) values.get(s);
                    if(name == null || name.isBlank()) throw new IllegalArgumentException("Invalid value for name");
                    book.setName(name);
                    break;
                    /*
                    could add condition for the date, FE no future date
                     */
                case "publishedDate":
                    LocalDate publishedDate = (LocalDate) values.get(s);
                    if(publishedDate == null) throw new IllegalArgumentException("Invalid value for date");
                    book.setPublishedDate(publishedDate);
                    break;
                case "authors" :
                    List<Author>authors = (List<Author>) values.get(s);
                    if(authors == null) throw new IllegalArgumentException("Invalid value for authors");
                    book.setAuthors(authors);
                    break;
                case "publisher" :
                    Publisher publisher = (Publisher) values.get(s);
                    if(publisher == null) throw new IllegalArgumentException("Invalid value for publisher");
                    book.setPublisher(publisher);
                    break;
                case "language":
                    Language language = (Language) values.get(s);
                    if(language == null) throw new IllegalArgumentException("Invalid value for language");
                    book.setLanguage(language);
                    break;
                case "genre" :
                    Genre genre = (Genre) values.get(s);
                    if(genre == null) throw new IllegalArgumentException("Invalid value for genre");
                    book.setGenre(genre);
                    break;
            }
        }
        bookRepository.save(book);
        return bookMapper.toDto(book);
    }
}
