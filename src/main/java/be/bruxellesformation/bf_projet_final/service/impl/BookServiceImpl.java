package be.bruxellesformation.bf_projet_final.service.impl;

import be.bruxellesformation.bf_projet_final.exception.model.*;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;
    private final ReviewMapper reviewMapper;
    private final BookRepository bookRepository;
    private final ReviewReposiroty reviewReposiroty;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final LanguageRepository languageRepository;
    private final PublisherRepository publisherRepository;

    public BookServiceImpl(BookMapper bookMapper, ReviewMapper reviewMapper, BookRepository bookRepository, ReviewReposiroty reviewReposiroty, GenreRepository genreRepository, AuthorRepository authorRepository, LanguageRepository languageRepository, PublisherRepository publisherRepository) {
        this.bookMapper = bookMapper;
        this.reviewMapper = reviewMapper;
        this.bookRepository = bookRepository;
        this.reviewReposiroty = reviewReposiroty;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
        this.languageRepository = languageRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public BookDTO insertOne(AddBookForm form) {
        Book book = bookMapper.fromFormToEntity(form);

        Optional<Genre> optGenre = genreRepository.findById(form.getGenre().getId());
        optGenre.ifPresentOrElse(book::setGenre, () -> book.setGenre(genreRepository.save(form.getGenre())));


        Optional<Language> optLang = languageRepository.findById(form.getLanguage().getId());
        optLang.ifPresentOrElse(book::setLanguage, () -> book.setLanguage(languageRepository.save(form.getLanguage())));

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
    @PreAuthorize("hasRole('ROLE_USER')")
    public BookDTO getOne(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        if(!book.isDisplay() && !isRoleAdmin())throw new BookNotFoundException(id);

        return bookMapper.toDto(book);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public BookDTO modifyOne(Long id, ModifyBookForm form) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        if(!book.isDisplay() && !isRoleAdmin())throw new BookNotFoundException(id);

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
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<BookDTO> getOneByName(String name) {
        if(isRoleAdmin()) return bookRepository.findBooksByName(name)
                                        .stream()
                                        .map(bookMapper::toDto)
                                        .collect(Collectors.toList());
        return bookRepository.findBooksByNameAndDisplay(name)
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<BookDTO> getAllByGenre(Long idGenre) {
        if(isRoleAdmin()) return bookRepository.findBooksByGenreId(idGenre)
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());

        if(!genreRepository.getById(idGenre).isDisplay()) throw new GenreNotFoundException(idGenre);

        return bookRepository.findBooksByGenreIdAndDisplay(idGenre)
                        .stream()
                        .map(bookMapper::toDto)
                        .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<BookDTO> getAllByAuthor(Long idAuthor) {

        if(isRoleAdmin()) return bookRepository.findBooksByAuthorsIn(idAuthor)
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());

        if(!authorRepository.getById(idAuthor).isDisplay()) throw new AuthorNotFoundException(idAuthor);
        List<Book> books = bookRepository.findBooksByAuthorsIn(idAuthor);
        return bookRepository.findBooksByGenreIdAndDisplay(idAuthor)
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<BookDTO> getAllByPublisher(Long idPublisher) {

        if(isRoleAdmin()) return bookRepository.
                findBooksByPublisherId(idPublisher)
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());

        if(!publisherRepository.getById(idPublisher).isDisplay()) throw new PublisherNotFoundException(idPublisher);

        return bookRepository.findBooksByPublisherIdAndDisplay(idPublisher)
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());

    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<BookDTO> getAllByLanguage(Long idLanguage) {

        if(isRoleAdmin()) return bookRepository.findBooksByLanguageId(idLanguage)
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());

        if(!languageRepository.getById(idLanguage).isDisplay()) throw new LanguageNotFoundException(idLanguage);

        return bookRepository.findBooksByLanguageIdAndDisplay(idLanguage)
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<BookDTO> getAllByPublishedYear(int year) {
        if(isRoleAdmin())return bookRepository.findAll()
                .stream()
                .filter(b -> b.getPublishedDate()!=null)
                .filter(b -> b.getPublishedDate().getYear() == year)
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
        return bookRepository.findAll()
                .stream()
                .filter(b -> b.getPublishedDate()!=null)
                .filter(b -> b.getPublishedDate().getYear() == year)
                .filter(Book::isDisplay)
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<ReviewDTO> getReviews(Long idBook) {

        Book book = bookRepository.findById(idBook).orElseThrow(()-> new BookNotFoundException(idBook));

        if(isRoleAdmin())return reviewReposiroty.findReviewsByIsAbout(book)
                .stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());

        return reviewReposiroty.findReviewsByIsAboutAndDisplay(book)
                .stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public BookDTO displayBook(Long id, boolean b) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        book.setDisplay(b);
        bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<BookDTO> getAll() {

        if(isRoleAdmin()) return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());

        return bookRepository.findAllByDisplay()
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public Page<BookDTO> getAllWithPagination(int page, int size) {
        if(isRoleAdmin()) {
            Page<Book> result = bookRepository.findAll(PageRequest.of(page, size));
            return result.map(bookMapper::toDto);
        }
        Page<Book> result = bookRepository.findAllByDisplay(PageRequest.of(page, size));
        return result.map(bookMapper::toDto);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
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

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public BookDTO deleteBook(Long id) {
        Book toRemove = bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        bookRepository.delete(toRemove);
        return bookMapper.toDto(toRemove);
    }

    private boolean isRoleAdmin() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(role -> role.equals("ROLE_ADMIN"));
    }
}
