package example.db;

import example.users.Country;
import example.users.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Function;

public class SessionManager {

    private static SessionFactory sessionFactory;

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.URL, "jdbc:h2:mem:test;MODE=MYSQL");
                settings.put(Environment.USER, "SA");
                settings.put(Environment.PASS, "");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "false");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Country.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                sessionFactory.openSession();
            } catch (Exception e) {
                throw new RuntimeException("Failed to open session", e);
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

    public static void runInTransaction(Consumer<Session> consumer) {
        runInTransaction(session -> {
            consumer.accept(session);
            return null;
        });
    }

    public static <T> T runInTransaction(Function<Session, T> consumer) {
        Transaction transaction = null;
        try {
            Session session = getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            T result = consumer.apply(session);

            transaction.commit();
            return result;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(ex);
        }
    }
}
