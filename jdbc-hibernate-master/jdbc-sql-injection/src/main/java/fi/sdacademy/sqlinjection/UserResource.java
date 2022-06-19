package fi.sdacademy.sqlinjection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserResource {

    private final int id;
    private final String email;
}
