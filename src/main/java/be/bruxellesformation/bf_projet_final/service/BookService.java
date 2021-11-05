package be.bruxellesformation.bf_projet_final.service;

import be.bruxellesformation.bf_projet_final.model.dto.BookDTO;
import be.bruxellesformation.bf_projet_final.model.dto.ReviewDTO;
import be.bruxellesformation.bf_projet_final.model.form.book.AddBookForm;
import be.bruxellesformation.bf_projet_final.model.form.book.ModifyBookForm;

import java.util.List;

public interface BookService {

    BookDTO insertOne(AddBookForm form);
    BookDTO getOne(Long id);
    BookDTO modifyOne(Long id, ModifyBookForm form);
    BookDTO getOneByName(String name);
    List<BookDTO> getAllByGenre(Long idGenre);
    List<BookDTO> getAllByAuthor(Long idAuthor);
    List<BookDTO> getAllByPublisher(Long idPublisher);
    List<BookDTO> getAllByLanguage(Long idLanguage);
    List<BookDTO> getAllByPublishedYear(int year);
    List<ReviewDTO> getReviews(Long idBook);
    BookDTO displayBook(Long id, boolean b);
}
