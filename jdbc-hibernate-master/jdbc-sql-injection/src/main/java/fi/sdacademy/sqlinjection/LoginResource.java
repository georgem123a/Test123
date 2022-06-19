package fi.sdacademy.sqlinjection;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResource {

    private String email;
    private String password;
}
