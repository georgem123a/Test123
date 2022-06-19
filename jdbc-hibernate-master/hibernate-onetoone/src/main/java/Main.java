import example.entity.Account;
import example.entity.Employee;
import example.util.HibernateUtil;
import org.hibernate.Session;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Account account = new Account();
        account.setAccountNumber("2343/134/2019");
        account.setDateEmploymentl(LocalDate.now());

        //Add new Employee object
        Employee emp = new Employee();
        emp.setEmail("HenrySHaines@dayrep.com");
        emp.setFirstName("Henry");
        emp.setLastName("Haines");

        //Save Account
        session.save(account);
        //Save Employee
        emp.setAccount(account);
        session.save(emp);

        session.getTransaction().commit();

        HibernateUtil.shutdown();
    }
}
