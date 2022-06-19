import example.dao.PersonDao;
import example.dao.PhoneDao;
import example.entity.Person;
import example.entity.Phone;
import example.entity.PhoneType;
import example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class Main {

    public static void main(String[] args){
        PhoneDao phoneDao = new PhoneDao();
        PersonDao personDao = new PersonDao();

        Person person1 = new Person("Henry", "Jack", "henry.jack@dayrep.com", "United States");
        Person person2 = new Person("Nicoletta", "Jean", "nicoletta.jean@dayrep.com", "United States");
        Person person3 = new Person("Toya", "Juarez", "toya.juarez@dayrep.com", "United States");
        Person person4 = new Person("Damian", "Leitch", "damian.leitch@dayrep.com", "United States");

        personDao.createPerson(person1);
        personDao.createPerson(person2);
        personDao.createPerson(person3);
        personDao.createPerson(person4);

        phoneDao.createPhone(new Phone("111-222-333", PhoneType.MOBILE, person1));
        phoneDao.createPhone(new Phone("222-333-444", PhoneType.MOBILE, person2));
        phoneDao.createPhone(new Phone("333-444-555", PhoneType.MOBILE, person3));
        phoneDao.createPhone(new Phone("23-332-222-33", PhoneType.LAND_LINE, person1));
        phoneDao.createPhone(new Phone("32-422-421-64", PhoneType.LAND_LINE, person2));

        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query1 = session.createQuery("from Person", Person.class);
        List<Person> personList1 = query1.list();
        for(Person person : personList1){
            System.out.println(person.toString());
        }
        System.out.println("-----------------------------------");

        Query query2 = session.createNamedQuery("get_person_by_name", Person.class);
        query2 = query2.setParameter("name", "J%");
        List<Person> personList2 = query2.list();
        for(Person person : personList2){
            System.out.println(person.toString());
        }
        System.out.println("-----------------------------------");

        Query query3 = session.createNamedQuery("get_person_by_mobile", Person.class);
        query3 = query3.setParameter("phoneType", PhoneType.MOBILE);
        List<Person> personList3 = query3.list();
        for(Person person : personList3){
            System.out.println(person.toString());
        }
        System.out.println("-----------------------------------");

        Query query4 = session.createNamedQuery("get_person_by_land_line", Person.class);
        query4 = query4.setParameter("phoneType", PhoneType.LAND_LINE);
        List<Person> personList4 = query4.list();
        for(Person person : personList4){
            System.out.println(person.toString());
        }
        System.out.println("-----------------------------------");

        HibernateUtil.shutdown();
    }
}
