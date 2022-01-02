package co.com.sofka.Library.Mapper;

import co.com.sofka.Library.DTOS.BookDto;
import co.com.sofka.Library.Model.Book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LibraryMapper {

    public Book fromDto(BookDto bookDTO){
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setName(bookDTO.getName());
        book.setBookType(bookDTO.getBookType());
        book.setAvailable(bookDTO.getAvailable());
        return book;
    }

    public BookDto fromModel(Book book){
        BookDto bookDTO = new BookDto();
        bookDTO.setId((book.getId()));
        bookDTO.setName(book.getName());
        bookDTO.setBookType(book.getBookType());
        bookDTO.setAvailable(book.getAvailable());
        return bookDTO;
    }

    public List<BookDto> fromModelList(List<Book> bookList){
        if(bookList.isEmpty()){
            return null;
        }
        List<BookDto> list = new ArrayList<>(bookList.size());

        for (Book book : bookList) {
            list.add(fromModel(book));
        }
        return list;
    }
}
