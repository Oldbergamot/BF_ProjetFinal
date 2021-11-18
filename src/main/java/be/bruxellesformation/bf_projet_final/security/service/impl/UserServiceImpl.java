package be.bruxellesformation.bf_projet_final.security.service.impl;

import be.bruxellesformation.bf_projet_final.exception.model.*;
import be.bruxellesformation.bf_projet_final.mapper.BookMapper;
import be.bruxellesformation.bf_projet_final.security.mapper.UserMapper;
import be.bruxellesformation.bf_projet_final.model.dto.BookDTO;
import be.bruxellesformation.bf_projet_final.security.dto.UserDTO;
import be.bruxellesformation.bf_projet_final.model.entity.*;
import be.bruxellesformation.bf_projet_final.model.form.user.UserAddPrefForm;
import be.bruxellesformation.bf_projet_final.model.form.user.UserRegisterForm;
import be.bruxellesformation.bf_projet_final.model.form.user.UserUpdateForm;
import be.bruxellesformation.bf_projet_final.repository.BookRepository;
import be.bruxellesformation.bf_projet_final.security.entity.User;
import be.bruxellesformation.bf_projet_final.security.repository.UserRepository;
import be.bruxellesformation.bf_projet_final.security.service.UserService;
import org.hibernate.cfg.NotYetImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, UserMapper mapper, BookRepository bookRepository, BookMapper bookMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = repository;
        this.userMapper = mapper;
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO insert(UserRegisterForm form) {
        User u = userMapper.fromFormToEntity(form);
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        userRepository.save(u);
        return userMapper.toDto(u);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public UserDTO getOne(Long id) {
       User u = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
       if((!u.isAccountNonExpired() || !u.isAccountNonLocked()) && !isRoleAdmin()) throw new AuthorisationException(id);
       return userMapper.toDto(u);
    }


    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public UserDTO updateOne(Long id, UserUpdateForm form) {
        User u = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        if((!u.isAccountNonExpired() || !u.isAccountNonLocked()) && !isRoleAdmin()) throw new AuthorisationException(id);
        u.setEmail(form.getEmail());
        u.setUsername(form.getUsername());
        u.setPassword(form.getPassword());
        userRepository.save(u);
        return userMapper.toDto(u);
    }


//    public UserDTO deleteOne(Long id) {
//        User u = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
////        u.setDisplay(false);
//        u.setAccountExpired(true);
//        u.setAccountLocked(true);
//        userRepository.save(u);
//        return userMapper.toDto(u);
//    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserDTO displayOne(Long id, boolean b) {
        User u = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        u.setAccountExpired(true);
        u.setAccountLocked(true);
        userRepository.save(u);
        return userMapper.toDto(u);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserDTO setAccountExpired(Long id, boolean b) {
        User u = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        u.setAccountExpired(b);
        userRepository.save(u);
        return userMapper.toDto(u);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserDTO setAccountLocked(Long id, boolean b) {
        User u = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        u.setAccountLocked(b);
        userRepository.save(u);
        return userMapper.toDto(u);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
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
    @PreAuthorize("hasRole('ROLE_USER')")
    public UserDTO addToWishToRead(Long idUser, Long idBook) {
        User u = userRepository.findById(idUser).orElseThrow(() -> new UserNotFoundException(idUser));
        if (!u.isAccountNonLocked() || !u.isAccountNonExpired()) throw new ModificationException(idUser);
        Book book = bookRepository.findById(idBook).orElseThrow(()-> new BookNotFoundException(idBook));
        List<Book> books = u.getWishToRead();
        books.add(book);
        u.setWishToRead(books);
        userRepository.save(u);
        return userMapper.toDto(u);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public UserDTO addToHasRead(Long idUser, Long idBook) {
        User u = userRepository.findById(idUser).orElseThrow(() -> new UserNotFoundException(idUser));
        if (!u.isAccountNonLocked() || !u.isAccountNonExpired()) throw new ModificationException(idUser);
        Book book = bookRepository.findById(idBook).orElseThrow(()-> new BookNotFoundException(idBook));
        List<Book> books = u.getHasRead();
        books.add(book);
        u.setHasRead(books);
        userRepository.save(u);
        return userMapper.toDto(u);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public UserDTO removeFromWishToRead(Long idUser, Long idBook) {
        User u = userRepository.findById(idUser).orElseThrow(() -> new UserNotFoundException(idUser));
        if (!isAccountActive(u)) throw new ModificationException(idUser);
        Book b = bookRepository.findById(idBook).orElseThrow(() -> new BookNotFoundException(idBook));

        List<Book> books = u.getWishToRead();
        books.remove(b);
        u.setWishToRead(books);
        userRepository.save(u);

        return userMapper.toDto(u);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public UserDTO removeFromHasRead(Long idUser, Long idBook) {
        User u = userRepository.findById(idUser).orElseThrow(() -> new UserNotFoundException(idUser));
        if (!isAccountActive(u)) throw new ModificationException(idUser);
        Book b = bookRepository.findById(idBook).orElseThrow(() -> new BookNotFoundException(idBook));

        List<Book> books = u.getHasRead();
        books.remove(b);
        u.setHasRead(books);
        userRepository.save(u);

        return userMapper.toDto(u);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<BookDTO> getGlobalRecommandation(Long id) {
        List<BookDTO> books = new ArrayList<>();

        books.addAll(getRecommandationOnAuthor(id));
        books.addAll(getRecommandationOnGenre(id));
        books.addAll(getRecommandationOnLanguage(id));
        books.addAll(getRecommandationOnPublisher(id));

        return books.stream().distinct().collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<BookDTO> getRecommandationOnGenre(Long id) {
        User u = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        if(!isAccountActive(u)) throw new AuthorisationException(id);
        List<Long> genresId = u.getPrefGenre()
                .stream()
                .map(Genre::getId)
                .collect(Collectors.toList());
        if(genresId.size()==0) throw new PreferenceNotCompletedException();
        List<Book> results = bookRepository.findBooksByGenreIdIn(genresId);
        return bookMapper.fromListEntityToDto(results);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<BookDTO> getRecommandationOnAuthor(Long id) {
        User u = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        if(!isAccountActive(u)) throw new AuthorisationException(id);
        List<Long> authorsId = u.getPrefAuthor()
                .stream()
                .map(Author::getId)
                .collect(Collectors.toList());
        if(authorsId.size() == 0 ) throw new PreferenceNotCompletedException();
        List<Book> results = bookRepository.findBooksByAuthorsIdIn(authorsId);
        return bookMapper.fromListEntityToDto(results);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<BookDTO> getRecommandationOnPublisher(Long id) {
        User u = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        if(!isAccountActive(u)) throw new AuthorisationException(id);
        List<Long> publisherId = u.getPrefPub()
                .stream()
                .map(Publisher::getId)
                .collect(Collectors.toList());
        if(publisherId.size()==0) throw new PreferenceNotCompletedException();
        List<Book> results = bookRepository.findBooksByPublisherIdIn(publisherId);
        return bookMapper.fromListEntityToDto(results);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<BookDTO> getRecommandationOnLanguage(Long id) {
        User u = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        if(!isAccountActive(u)) throw new AuthorisationException(id);
        List<Long> languagesId = u.getPrefGenre()
                .stream()
                .map(Genre::getId)
                .collect(Collectors.toList());
        if(languagesId.size()==0) throw new PreferenceNotCompletedException();
        List<Book> results = bookRepository.findBooksByLanguageIdIn(languagesId);
        return bookMapper.fromListEntityToDto(results);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public Page<UserDTO> getAllWithPagination(int page, int size) {
        Page<User>results = userRepository.findAll(PageRequest.of(page, size));
        return results.map(userMapper::toDto);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public Page<BookDTO> getGlobalRecommandationWithPagination(Long id,int page, int size) {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        if(!isAccountActive(user)) throw new AuthorisationException(id);
        List<Long> genresId = user.getPrefGenre()
                .stream()
                .map(Genre::getId)
                .collect(Collectors.toList());
        List<Long> publishersId = user.getPrefPub()
                .stream()
                .map(Publisher::getId)
                .collect(Collectors.toList());
        List<Long> languagesId = user.getPrefLang()
                .stream()
                .map(Language::getId)
                .collect(Collectors.toList());
        logger.info("Page: "+ page+ ", size: "+ size);
        Page<Book>results = bookRepository.findAllByGenreIdInOrLanguageIdInOrPublisherIdIn(genresId,languagesId,publishersId, PageRequest.of(page, size));

        return results.map(bookMapper::toDto);
    }


    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public Page<BookDTO> getRecommandationOnGenreWithPagination(Long id, int page, int size) {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        if(!isAccountActive(user)) throw new AuthorisationException(id);
        List<Long> genresId = user.getPrefGenre()
                .stream()
                .map(Genre::getId)
                .collect(Collectors.toList());
        if(genresId.size() == 0 ) throw new PreferenceNotCompletedException();
        Page<Book> results = bookRepository.findAllByGenreIdIn(genresId,PageRequest.of(page, size) );
        return results.map(bookMapper::toDto);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public Page<BookDTO> getRecommandationOnAuthorWithPaginationr(Long id,int page, int size) {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        if(!isAccountActive(user)) throw new AuthorisationException(id);
        List<Long> authorsId = user.getPrefAuthor()
                .stream()
                .map(Author::getId)
                .collect(Collectors.toList());
        if(authorsId.size() == 0 ) throw new PreferenceNotCompletedException();
        Page<Book> books = bookRepository.findBooksByAuthorsIn(authorsId, PageRequest.of(page, size));
        return books.map(bookMapper::toDto);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public Page<BookDTO> getRecommandationOnPublisherWithPagination(Long id,int page, int size) {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));

        List<Long> publishersId = user.getPrefGenre()
                .stream()
                .map(Genre::getId)
                .collect(Collectors.toList());
        if(publishersId.size() == 0 ) throw new PreferenceNotCompletedException();
        Page<Book> results = bookRepository.findAllByPublisherIdIn(publishersId,PageRequest.of(page, size));
        return results.map(bookMapper::toDto);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public Page<BookDTO> getRecommandationOnLanguageWithPagination(Long id,int page, int size) {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        List<Long> languagesId = user.getPrefLang()
                .stream()
                .map(Language::getId)
                .collect(Collectors.toList());
        if(languagesId.size() == 0 ) throw new PreferenceNotCompletedException();
        Page<Book> results = bookRepository.findAllByLanguageIdIn(languagesId, PageRequest.of(page, size));
        return results.map(bookMapper::toDto);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public UserDTO partialUpdate(Long id, Map<String, Object> values) {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));

        for(String s : values.keySet()){
            switch (s) {
                case "username":
                    String username = (String) values.get(s);
                    if (username == null || username.isBlank())
                        throw new IllegalArgumentException("Invalid value for username");
                    user.setUsername(username);
                    break;
                case "email":
                    String email = (String) values.get(s);
                    if (email == null || email.isBlank()) throw new IllegalArgumentException("Invalid value for email");
                    user.setEmail(email);
                    break;
                case "password":
                    String password = (String) values.get(s);
                    if (password == null || password.isBlank())
                        throw new IllegalArgumentException("Invalid value for password");
                    user.setPassword(passwordEncoder.encode(password));
                    break;
                case "wishToRead":
                    List<Book> wishToRead = (List<Book>) values.get(s);
                    if (wishToRead.size() == 0) throw new IllegalArgumentException("Invalid value for Wish to Read");
                    user.setWishToRead(wishToRead);
                    break;
                case "hasRead":
                    List<Book> hasRead = (List<Book>) values.get(s);
                    if (hasRead.size() == 0) throw new IllegalArgumentException("Invalid value for has read");
                    user.setWishToRead(hasRead);
                    break;
                case "prefLang":
                    List<Language>prefLang = (List<Language>) values.get(s);
                    if(prefLang.size() == 0 ) throw new IllegalArgumentException("Invalid value for pref lang");
                    user.setPrefLang(prefLang);
                    break;
                case "prefPub" :
                    List<Publisher>prefPub = (List<Publisher>) values.get(s);
                    if(prefPub.size() == 0) throw new IllegalArgumentException("Invalid value for pref pub");
                    user.setPrefPub(prefPub);
                    break;
                case "prefAuthor":
                    List<Author>prefAuthor = (List<Author>) values.get(s);
                    if(prefAuthor.size()==0) throw new IllegalArgumentException("Invalid value for pref Author");
                    user.setPrefAuthor(prefAuthor);
                    break;
            }
        }
        userRepository.save(user);

        return userMapper.toDto(user);
    }

    private boolean isRoleAdmin() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(role -> role.equals("ROLE_ADMIN"));
    }

    private boolean isAccountActive(User u) {
        if(!u.isAccountNonExpired() || !u.isAccountNonLocked()) throw new ModificationException(u.getId());
        return true;
    }

}
