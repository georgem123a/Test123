package example.db;

import example.users.Country;
import example.users.Gender;
import example.users.User;

import static example.db.SessionManager.runInTransaction;

public class InitialData {

    public static void createData() {
        runInTransaction(session -> {
            Country finland = new Country("Finland", "FI");
            Country poland = new Country("Poland", "PL");
            session.save(finland);
            session.save(poland);

            session.save(
                    User.builder()
                            .email("kamil@gmail.com")
                            .password("secretPassword")
                            .country(finland)
                            .gender(Gender.MALE)
                            .build()
            );

            session.save(
                    User.builder()
                            .email("john@gmail.com")
                            .password("secretPassword")
                            .country(finland)
                            .gender(Gender.MALE)
                            .build()
            );

            session.save(
                    User.builder()
                            .email("alice@gmail.com")
                            .password("secretPassword")
                            .country(finland)
                            .gender(Gender.FEMALE)
                            .build()
            );

            session.save(
                    User.builder()
                            .email("foo@gmail.com")
                            .password("secretPassword")
                            .country(poland)
                            .gender(Gender.MALE)
                            .build()
            );

        });
    }
}
