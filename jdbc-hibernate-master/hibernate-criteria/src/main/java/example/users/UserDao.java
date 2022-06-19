package example.users;

import java.util.List;

public interface UserDao {

    List<User> listAllUsers();

    User findByEmail(String email);

    List<User> findByCountry(String countryCode);

    List<User> findByGenderAndCountry(Gender gender, String countryCode);
}
