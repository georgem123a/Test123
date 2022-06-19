package example.users;

import org.hibernate.criterion.Restrictions;

import java.util.List;

import static example.db.SessionManager.runInTransaction;

public class OldUserDao implements UserDao {

    @Override
    public List<User> listAllUsers() {
        return runInTransaction(session -> {
            return session.createCriteria(User.class).list();
        });
    }

    @Override
    public User findByEmail(String email) {
        return runInTransaction(session -> {
            return (User) session.createCriteria(User.class)
                    .add(Restrictions.eq("email", email))
                    .uniqueResult();
        });
    }

    @Override
    public List<User> findByCountry(String countryCode) {
        return runInTransaction(session -> {
            return session.createCriteria(User.class)
                    .createAlias("country", "c")
                    .add(Restrictions.eq("c.code", countryCode))
                    .list();
        });
    }

    @Override
    public List<User> findByGenderAndCountry(Gender gender, String countryCode) {
        return runInTransaction(session -> {
            return session.createCriteria(User.class)
                    .createAlias("country", "c")
                    .add(Restrictions.conjunction(
                            Restrictions.eq("c.code", countryCode),
                            Restrictions.eq("gender", gender)
                    ))
                    .list();
        });
    }
}
