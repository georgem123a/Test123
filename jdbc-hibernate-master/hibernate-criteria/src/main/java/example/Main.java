package example;

import example.users.Gender;
import example.users.NewUserDao;
import example.users.OldUserDao;
import example.users.User;
import example.users.UserDao;

import static example.db.InitialData.createData;
import static example.db.SessionManager.shutdown;

public class Main {

    public static void main(String[] args) {
        try {
            createData();

            System.out.println("---------------------------- Old way ---------------------");
            execute(new OldUserDao());

            System.out.println("---------------------------- New way ---------------------");
            execute(new NewUserDao());

        } finally {
            shutdown();
        }
    }

    private static void execute(UserDao dao) {
        System.out.println("\r\nAll users:");
        for (User user : dao.listAllUsers()) {
            System.out.println(user);
        }

        System.out.println("User with email kamil@gmail.com: " + dao.findByEmail("kamil@gmail.com"));

        System.out.println("\r\nUsers from Finland:");
        for (User user : dao.findByCountry("FI")) {
            System.out.println(user);
        }

        System.out.println("\r\nFemales from Finland:");
        for (User user : dao.findByGenderAndCountry(Gender.FEMALE,"FI")) {
            System.out.println(user);
        }
    }
}
