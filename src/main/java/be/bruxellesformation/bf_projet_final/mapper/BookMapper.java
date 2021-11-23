package be.bruxellesformation.bf_projet_final.mapper;

import be.bruxellesformation.bf_projet_final.model.dto.BookDTO;
import be.bruxellesformation.bf_projet_final.model.entity.Book;
import be.bruxellesformation.bf_projet_final.model.form.book.AddBookForm;
import be.bruxellesformation.bf_projet_final.model.form.book.ModifyBookForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookMapper implements BaseMapper <BookDTO, AddBookForm, Book>{
    private final AuthorMapper authorMapper;

    @Autowired
    public BookMapper(AuthorMapper authorMapper) {
        this.authorMapper = authorMapper;
    }

    public BookDTO toDto(Book entity){
        if(entity==null)return null;
        return BookDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .publishedDate(entity.getPublishedDate())
                .summary(entity.getSummary())
                .authors(this.authorMapper.toSimpleDtos(entity.getAuthors()).collect(Collectors.toList()))
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
                .authors(this.authorMapper.toEntities(dto.getAuthors()).collect(Collectors.toList()))
                .language(dto.getLanguage())
                .genre(dto.getGenre())
                .build();
    }

    public Book fromFormToEntity(AddBookForm form) {
        if(form == null) return null;
        return Book.builder()
                .authors(form.getAuthors())
                .genre(form.getGenre())
                .language((form.getLanguage()))
                .name(form.getName())
                .summary(form.getSummary())
                .publishedDate(form.getPublishedDate())
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
