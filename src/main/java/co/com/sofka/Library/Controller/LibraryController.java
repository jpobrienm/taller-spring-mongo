package co.com.sofka.Library.Controller;

import co.com.sofka.Library.DTOS.BookDto;
import co.com.sofka.Library.Service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class LibraryController {
    @Autowired
    LibraryService libraryService;

    @GetMapping()
    public ResponseEntity<List<BookDto>> findAll(){
        return new ResponseEntity<>(libraryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable("id") String id){
        return new ResponseEntity<>(libraryService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<BookDto> create(@RequestBody BookDto bookDto){
        return new ResponseEntity<>(libraryService.create(bookDto), HttpStatus.CREATED);
    }

    @PutMapping("/modify")
    public ResponseEntity<BookDto> update(@RequestBody BookDto bookDto){
        if(bookDto.getId() != null){
            return new ResponseEntity<>(libraryService.modify(bookDto), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/modify/borrow/{id}")
    public ResponseEntity<BookDto> borrowBook(@PathVariable("id") String id){
        if (libraryService.getById(id) != null) {
            return new ResponseEntity<>(libraryService.borrowBook(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/modify/return/{id}")
    public ResponseEntity<BookDto> returnBook(@PathVariable("id") String id){
        if (libraryService.getById(id) != null) {
            return new ResponseEntity<>(libraryService.returnBook(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") String id){
        try{
            libraryService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
