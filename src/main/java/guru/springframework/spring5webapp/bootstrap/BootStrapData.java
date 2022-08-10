package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher publisher = new Publisher();
        publisher.setName("Talonmaenkatu");
        publisher.setCity("Turku");
        publisher.setState("P6lvamaa");

        publisherRepository.save(publisher);

        System.out.println("Publisher Count: " + publisherRepository.count());

        Author heidi = new Author("Heidi", "Granholm");
        Book vetMed = new Book("Veterinary Medicine", "123456");
        heidi.getBooks().add(vetMed);
        vetMed.getAuthors().add(heidi);

        vetMed.setPublisher(publisher);
        publisher.getBooks().add(vetMed);

        authorRepository.save(heidi);
        bookRepository.save(vetMed);
        publisherRepository.save(publisher);

        Author alari = new Author("Alari", "Kukk");
        Book softEng = new Book("Software Engineering", "123456");
        alari.getBooks().add(softEng);
        softEng.getAuthors().add(alari);

        softEng.setPublisher(publisher);
        publisher.getBooks().add(softEng);

        authorRepository.save(alari);
        bookRepository.save(softEng);
        publisherRepository.save(publisher);

        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Number of Books: " + publisher.getBooks().size());
    }
}
