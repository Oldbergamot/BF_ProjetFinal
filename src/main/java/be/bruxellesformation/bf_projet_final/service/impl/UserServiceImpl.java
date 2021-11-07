package be.bruxellesformation.bf_projet_final.service.impl;

import be.bruxellesformation.bf_projet_final.exceptions.BookNotFoundException;
import be.bruxellesformation.bf_projet_final.exceptions.UserNotFoundException;
import be.bruxellesformation.bf_projet_final.mapper.BookMapper;
import be.bruxellesformation.bf_projet_final.mapper.UserMapper;
import be.bruxellesformation.bf_projet_final.model.dto.BookDTO;
import be.bruxellesformation.bf_projet_final.model.dto.UserDTO;
import be.bruxellesformation.bf_projet_final.model.entity.*;
import be.bruxellesformation.bf_projet_final.model.form.user.UserAddPrefForm;
import be.bruxellesformation.bf_projet_final.model.form.user.UserRegisterForm;
import be.bruxellesformation.bf_projet_final.model.form.user.UserUpdateForm;
import be.bruxellesformation.bf_projet_final.repository.BookRepository;
import be.bruxellesformation.bf_projet_final.repository.UserRepository;
import be.bruxellesformation.bf_projet_final.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public UserServiceImpl(UserRepository repository, UserMapper mapper, BookRepository bookRepository, BookMapper bookMapper) {
        this.userRepository = repository;
        this.userMapper = mapper;
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public UserDTO insert(UserRegisterForm form) {
        User u = userMapper.fromUserRegisterFormToEntity(form);
        userRepository.save(u);
        return userMapper.toDto(u);
    }

    @Override
    public UserDTO getOne(Long id) {
       User u = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
       return userMapper.toDto(u);
    }


    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateOne(Long id, UserUpdateForm form) {
        User u = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        u.setEmail(form.getEmail());
        u.setUsername(form.getUsername());
        u.setPassword(form.getPassword());
        userRepository.save(u);
        return userMapper.toDto(u);
    }

    @Override
    public UserDTO deleteOne(Long id) {
        User u = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        u.setDisplay(false);
        return userMapper.toDto(u);
    }

    @Override
    public UserDTO updatePref(Long id, UserAddPrefForm form) {
        User u = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        u.setPrefPub(form.getPrefPub());
        u.setPrefLang(form.getPrefLang());
        u.setPrefGenre(form.getPrefGenre());
        u.setPrefAuthor(form.getPrefAuthor());
        userRepository.save(u);
        return userMapper.toDto(u);
    }

    @Override
    public UserDTO addToWishToRead(Long idUser, Long idBook) {
        User u = userRepository.findById(idUser).orElseThrow(() -> new UserNotFoundException(idUser));
        Book book = bookRepository.findById(idBook).orElseThrow(()-> new BookNotFoundException(idBook));
        List<Book> books = u.getWishToRead();
        books.add(book);
        u.setWishToRead(books);
        userRepository.save(u);
        return userMapper.toDto(u);
    }

    @Override
    public UserDTO addToHasRead(Long idUser, Long idBook) {
        User u = userRepository.findById(idUser).orElseThrow(() -> new UserNotFoundException(idUser));
        Book book = bookRepository.findById(idBook).orElseThrow(()-> new BookNotFoundException(idBook));
        List<Book> books = u.getHasRead();
        books.add(book);
        u.setHasRead(books);
        userRepository.save(u);
        return userMapper.toDto(u);
    }

    @Override
    public UserDTO removeFromWishToRead(Long idUser, Long idBook) {
        User u = userRepository.findById(idUser).orElseThrow(() -> new UserNotFoundException(idUser));
        Book b = bookRepository.findById(idBook).orElseThrow(() -> new BookNotFoundException(idBook));

        List<Book> books = u.getWishToRead();
        books.remove(b);
        u.setWishToRead(books);
        userRepository.save(u);

        return userMapper.toDto(u);
    }

    @Override
    public UserDTO removeFromHasRead(Long idUser, Long idBook) {
        User u = userRepository.findById(idUser).orElseThrow(() -> new UserNotFoundException(idUser));
        Book b = bookRepository.findById(idBook).orElseThrow(() -> new BookNotFoundException(idBook));

        List<Book> books = u.getHasRead();
        books.remove(b);
        u.setHasRead(books);
        userRepository.save(u);

        return userMapper.toDto(u);
    }

    @Override
    public List<BookDTO> getGlobalRecommandation(Long id) {
        List<BookDTO> books = new ArrayList<>();

        books.addAll(getRecommandationOnAuthor(id));
        books.addAll(getRecommandationOnGenre(id));
        books.addAll(getRecommandationOnLanguage(id));
        books.addAll(getRecommandationOnPublisher(id));

        return books;
    }

    @Override
    public List<BookDTO> getRecommandationOnGenre(Long id) {
        User u = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        List<Genre> genres = u.getPrefGenre();
        if(genres.size() == 0) return null;
        List<Book> books = new ArrayList<>();
        // getting books
        for (Genre g : genres) {
            List<Book> temp = bookRepository.findByGenre(g);
            if(temp != null) books.addAll(temp);
        }
        //maybe look fur duplicate?
        return bookMapper.fromListEntityToDto(books);
    }

    @Override
    public List<BookDTO> getRecommandationOnAuthor(Long id) {
//        User u = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
//        List<Author> authors = u.getPrefAuthor();
//        if (authors.size() == 0) return null;
//        List<Book> books = new ArrayList<>();
//        //getting books
//        for(Author a : authors) {
//            List<Book> temp = bookRepository.findByAuthor(a);
//            if(temp.size()==0) books.addAll(temp);
//        }
//        return bookMapper.fromListEntityToDto(temp);
        return null;
    }

    @Override
    public List<BookDTO> getRecommandationOnPublisher(Long id) {
        User u = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
        List<Publisher> publishers = u.getPrefPub();
        if(publishers.size() == 0) return null;
        List<Book> books = new ArrayList<>();
        //getting books
        for (Publisher p : publishers){
            List<Book> temp = bookRepository.findByPublisher(p);
            if (temp.size()!=0)books.addAll(temp);
        }
        return bookMapper.fromListEntityToDto(books);
    }

    @Override
    public List<BookDTO> getRecommandationOnLanguage(Long id) {
        User u = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
        List<Language> languages = u.getPrefLang();
        if(languages.size()==0) return null;
        List<Book>books = new ArrayList<>();
        //getting books
        for (Language l : languages) {
            List<Book>temp = bookRepository.findByLanguage(l);
            if(temp.size() != 0) books.addAll(temp);
        }
        return bookMapper.fromListEntityToDto(books);
    }

    @Override
    public Page<UserDTO> getAllWithPagination(int page, int size) {
        return null;
    }

    @Override
    public UserDTO partialUpdate(Long id, Map<String, Object> values) {
        return null;
    }
}
