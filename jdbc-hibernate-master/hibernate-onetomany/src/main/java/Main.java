import example.entity.Book;
import example.entity.Owner;
import example.util.HibernateUtil;
import org.hibernate.Session;

public class Main {

    public static void main (String[] args){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Owner owner = new Owner();
        owner.setName("Henry Haines");
        owner.setEmail("HenrySHaines@dayrep.com");
        owner.setCountry("Canada");

        //Add new Book object
        Book book1 = new Book();
        book1.setTitle("A Dance with Dragons");

        Book book2 = new Book();
        book2.setTitle("A Storm of Swords");

        //Save Owner
        session.save(owner);
        //Save Books
        book1.setOwner(owner);
        book2.setOwner(owner);
        session.save(book1);
        session.save(book2);

        session.getTransaction().commit();

        HibernateUtil.shutdown();
    }
}
