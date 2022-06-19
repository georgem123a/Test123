package fi.sdacademy.sqlinjection;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class LoginController {

    private final DataSource dataSource;

    @PostMapping("/login")
    public List<UserResource> login(@RequestBody LoginResource loginResource) throws SQLException {
        ResultSet rs = dataSource.getConnection().createStatement()
                .executeQuery("SELECT * from users where email = '" + loginResource.getEmail() + "' and password = '" + loginResource.getPassword() + "'");

        List<UserResource> result = new ArrayList<>();
        while(rs.next()) {
            result.add(
                    new UserResource(rs.getInt("id"), rs.getString("email"))
            );
        }
        return result;
    }


}
