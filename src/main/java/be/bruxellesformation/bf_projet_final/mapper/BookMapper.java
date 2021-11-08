package be.bruxellesformation.bf_projet_final.mapper;

import be.bruxellesformation.bf_projet_final.model.dto.BookDTO;
import be.bruxellesformation.bf_projet_final.model.entity.Book;
import be.bruxellesformation.bf_projet_final.model.form.book.AddBookForm;
import be.bruxellesformation.bf_projet_final.model.form.book.ModifyBookForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookMapper {

    public BookDTO toDto(Book entity){
        if(entity==null);
        return BookDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .publishedDate(entity.getPublishedDate())
                .summary(entity.getSummary())
                .authors(entity.getAuthors())
                .language(entity.getLanguage())
                .genre(entity.getGenre())
                .build();
    }

    public Book toEntity(BookDTO dto) {
        if(dto == null) return null;
        return Book.builder()
                .id(dto.getId())
                .name(dto.getName())
                .publishedDate(dto.getPublishedDate())
                .summary(dto.getSummary())
                .authors(dto.getAuthors())
                .language(dto.getLanguage())
                .genre(dto.getGenre())
                .build();
    }

    public Book fromAddBookFormToEntity(AddBookForm form) {
        if(form == null) return null;
        Book book = new Book();
        book.setAuthors(form.getAuthors());
        book.setGenre(form.getGenre());
        book.setLanguage(form.getLanguage());
        book.setName(form.getName());
        book.setPublishedDate(form.getPublishedDate());
        book.setSummary(form.getSummary());
        return book;
    }

    public Book fromModifyBookFormToEntity(ModifyBookForm form) {
        if(form == null) return null;
        return Book.builder()
                .name(form.getName())
                .publishedDate(form.getPublishedDate())
                .summary(form.getSummary())
                .authors(form.getAuthors())
                .language(form.getLanguage())
                .genre(form.getGenre())
                .build();
    }

    public List<BookDTO> fromListEntityToDto(List<Book> list) {
        List<BookDTO> results = new ArrayList<>();
        for(Book b : list) {
            results.add(toDto(b));
        }
        return results;
    }

    public List<Book> fromListDTOToEntity(List<BookDTO> list) {
        List<Book> books = new ArrayList<>();
        for(BookDTO dto : list){
            books.add(toEntity(dto));
        }
        return books;
    }

}
