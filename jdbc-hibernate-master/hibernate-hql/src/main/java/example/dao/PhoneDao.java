package example.dao;

import example.entity.Phone;
import example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PhoneDao {

    public void createPhone(Phone phone) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            // start a transaction
            transaction = session.beginTransaction();
            // save the phone object
            session.save(phone);
            // commit transaction
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }
}
