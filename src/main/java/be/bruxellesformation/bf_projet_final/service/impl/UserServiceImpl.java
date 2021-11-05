package be.bruxellesformation.bf_projet_final.service.impl;

import be.bruxellesformation.bf_projet_final.exceptions.BookNotFoundException;
import be.bruxellesformation.bf_projet_final.exceptions.UserNotFoundException;
import be.bruxellesformation.bf_projet_final.mapper.UserMapper;
import be.bruxellesformation.bf_projet_final.model.dto.BookDTO;
import be.bruxellesformation.bf_projet_final.model.dto.UserDTO;
import be.bruxellesformation.bf_projet_final.model.entity.Book;
import be.bruxellesformation.bf_projet_final.model.entity.User;
import be.bruxellesformation.bf_projet_final.model.form.user.UserAddPrefForm;
import be.bruxellesformation.bf_projet_final.model.form.user.UserRegisterForm;
import be.bruxellesformation.bf_projet_final.model.form.user.UserUpdateForm;
import be.bruxellesformation.bf_projet_final.repository.BookRepository;
import be.bruxellesformation.bf_projet_final.repository.UserRepository;
import be.bruxellesformation.bf_projet_final.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final BookRepository bookRepository;

    public UserServiceImpl(UserRepository repository, UserMapper mapper, BookRepository bookRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.bookRepository = bookRepository;
    }

    @Override
    public UserDTO insert(UserRegisterForm form) {
        User u = mapper.fromUserRegisterFormToEntity(form);
        repository.save(u);
        return mapper.toDto(u);
    }

    @Override
    public UserDTO getOne(Long id) {
       User u = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
       return mapper.toDto(u);
    }


    @Override
    public List<UserDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateOne(Long id, UserUpdateForm form) {
        User u = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        u.setEmail(form.getEmail());
        u.setUsername(form.getUsername());
        u.setPassword(form.getPassword());
        repository.save(u);
        return mapper.toDto(u);
    }

    @Override
    public UserDTO deleteOne(Long id) {
        User u = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        u.setDisplay(false);
        return mapper.toDto(u);
    }

    @Override
    public UserDTO updatePref(Long id, UserAddPrefForm form) {
        User u = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        u.setPrefPub(form.getPrefPub());
        u.setPrefLang(form.getPrefLang());
        u.setPrefGenre(form.getPrefGenre());
        u.setPrefAuthor(form.getPrefAuthor());
        repository.save(u);
        return mapper.toDto(u);
    }

    @Override
    public UserDTO updateWishToRead(Long idUser, Long idBook) {
        User u = repository.findById(idUser).orElseThrow(() -> new UserNotFoundException(idUser));
        Book book = bookRepository.findById(idBook).orElseThrow(()-> new BookNotFoundException(idBook));
        List<Book> books = u.getWishToRead();
        books.add(book);
        u.setWishToRead(books);
        repository.save(u);
        return mapper.toDto(u);
    }

    @Override
    public UserDTO updateHasRead(Long idUser, Long idBook) {
        User u = repository.findById(idUser).orElseThrow(() -> new UserNotFoundException(idUser));
        Book book = bookRepository.findById(idBook).orElseThrow(()-> new BookNotFoundException(idBook));
        List<Book> books = u.getHasRead();
        books.add(book);
        u.setHasRead(books);
        repository.save(u);
        return mapper.toDto(u);
    }

    @Override
    public UserDTO removeFromList(Long idUser, Long idBook) {
        return null;
    }

    @Override
    public List<BookDTO> getRecommandation(Long id) {
        return null;
    }
}
