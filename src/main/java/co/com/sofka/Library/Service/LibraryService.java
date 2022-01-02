package co.com.sofka.Library.Service;

import co.com.sofka.Library.DTOS.BookDto;
import co.com.sofka.Library.Mapper.LibraryMapper;
import co.com.sofka.Library.Model.Book;
import co.com.sofka.Library.Repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    @Autowired
    LibraryRepository libraryRepository;
    LibraryMapper libraryMapper = new LibraryMapper();

    public List<BookDto> getAll(){
        List<Book> books = (List<Book>) libraryRepository.findAll();
        return libraryMapper.fromModelList(books);
    }

    public BookDto getById(String id){
        Book book = libraryRepository.findById(id).orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        return libraryMapper.fromModel(book);
    }

    public BookDto create(BookDto bookDto){
        Book book = libraryMapper.fromDto(bookDto);
        return libraryMapper.fromModel(libraryRepository.save(book));
    }

    public BookDto modify(BookDto bookDto){
        Book book = libraryMapper.fromDto(bookDto);
        libraryRepository.findById(book.getId()).orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        return libraryMapper.fromModel(libraryRepository.save(book));
    }

    public BookDto borrowBook(String id){
        Book book = libraryRepository.findById(id).orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        if (!book.getAvailable()){
            throw new RuntimeException("El libro no está disponible");
        }
        book.setAvailable(false);
        return libraryMapper.fromModel(libraryRepository.save(book));
    }

    public BookDto returnBook(String id){
        Book book = libraryRepository.findById(id).orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        if (book.getAvailable()){
            throw new RuntimeException("El libro ya se encuentra en la biblioteca");
        }
        book.setAvailable(true);
        return libraryMapper.fromModel(libraryRepository.save(book));
    }

    public void delete(String id){
        libraryRepository.deleteById(id);
    }

    public void clear(){
        getAll().forEach(p -> delete(p.getId()));
    }


}
