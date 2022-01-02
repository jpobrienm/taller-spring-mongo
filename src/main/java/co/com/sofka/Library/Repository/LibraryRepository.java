package co.com.sofka.Library.Repository;

import co.com.sofka.Library.Model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LibraryRepository extends MongoRepository<Book, String> {
}
