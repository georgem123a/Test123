import example.entity.Author;
import example.entity.Book;
import example.util.HibernateUtil;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Author author1 = new Author();
        author1.setName("Ashish");
        author1.setSurname("Singh Bhatia");

        Author author2 = new Author();
        author2.setName("Bostjan");
        author2.setSurname("Kaluza");

        Author author3 = new Author();
        author3.setName("Richard M.");
        author3.setSurname("Reese");

        //Add new Book object
        Book book1 = new Book();
        book1.setTitle("Machine Learning in Java");

        Book book2 = new Book();
        book2.setTitle("Natural Language Processing with Java");

        //Save Authors
        session.save(author1);
        session.save(author2);
        //Save Books
        Set<Author> authorSet1 = new HashSet<>();
        authorSet1.add(author1);
        authorSet1.add(author2);

        Set<Author> authorSet2 = new HashSet<>();
        authorSet2.add(author3);
        authorSet2.add(author1);

        book1.setAuthors(authorSet1);
        book2.setAuthors(authorSet2);
        session.save(book1);
        session.save(book2);

        session.getTransaction().commit();

        HibernateUtil.shutdown();
    }
}
