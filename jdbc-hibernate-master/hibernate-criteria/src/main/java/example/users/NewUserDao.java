package example.users;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static example.db.SessionManager.runInTransaction;

public class NewUserDao implements UserDao {

    @Override
    public List<User> listAllUsers() {
        return runInTransaction(session -> {
            CriteriaQuery<User> query = session.getCriteriaBuilder().createQuery(User.class);

            return session.createQuery(query.select(query.from(User.class))).list();
        });
    }

    @Override
    public User findByEmail(String email) {
        return runInTransaction(session -> {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> query = cb.createQuery(User.class);
            Root<User> root = query.from(User.class);

            return session.createQuery(
                    query.select(root)
                        .where(cb.equal(root.get("email"), email))
            ).uniqueResult();
        });
    }

    @Override
    public List<User> findByCountry(String countryCode) {
        return runInTransaction(session -> {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> query = cb.createQuery(User.class);
            Root<User> root = query.from(User.class);

            return session.createQuery(
                    query.select(root)
                            .where(cb.equal(root.join("country").get("code"), countryCode))
            ).list();
        });
    }

    @Override
    public List<User> findByGenderAndCountry(Gender gender, String countryCode) {
        return runInTransaction(session -> {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> query = cb.createQuery(User.class);
            Root<User> root = query.from(User.class);

            return session.createQuery(
                    query.select(root)
                            .where(
                                    cb.and(
                                            cb.equal(root.join("country").get("code"), countryCode),
                                            cb.equal(root.get("gender"), gender)
                                    )
                            )
            ).list();
        });
    }
}
