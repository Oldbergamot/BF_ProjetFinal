package be.bruxellesformation.bf_projet_final.service.impl;

import be.bruxellesformation.bf_projet_final.model.dto.BookDTO;
import be.bruxellesformation.bf_projet_final.model.dto.ReviewDTO;
import be.bruxellesformation.bf_projet_final.model.form.book.AddBookForm;
import be.bruxellesformation.bf_projet_final.model.form.book.ModifyBookForm;
import be.bruxellesformation.bf_projet_final.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {
    @Override
    public BookDTO insertOne(AddBookForm form) {
        return null;
    }

    @Override
    public BookDTO getOne(Long id) {
        return null;
    }

    @Override
    public BookDTO modifyOne(Long id, ModifyBookForm form) {
        return null;
    }

    @Override
    public BookDTO getOneByName(String name) {
        return null;
    }

    @Override
    public List<BookDTO> getAllByGenre(Long idGenre) {
        return null;
    }

    @Override
    public List<BookDTO> getAllByAuthor(Long idAuthor) {
        return null;
    }

    @Override
    public List<BookDTO> getAllByPublisher(Long idPublisher) {
        return null;
    }

    @Override
    public List<BookDTO> getAllByLanguage(Long idLanguage) {
        return null;
    }

    @Override
    public List<BookDTO> getAllByPublishedYear(int year) {
        return null;
    }

    @Override
    public List<ReviewDTO> getReviews(Long idBook) {
        return null;
    }

    @Override
    public BookDTO displayBook(Long id, boolean b) {
        return null;
    }

    @Override
    public List<BookDTO> getAll() {
        return null;
    }

    @Override
    public Page<BookDTO> getAllWithPagination(int page, int size) {
        return null;
    }

    @Override
    public BookDTO partialUpdate(Long id, Map<String, Object> values) {
        return null;
    }
}
