package example;

import example.inheritance.mappedsuperclass.Country;
import example.inheritance.mappedsuperclass.User;
import example.inheritance.singletable.HotelBooking;
import example.inheritance.singletable.RestaurantBooking;

import static example.db.SessionManager.runInTransaction;
import static example.db.SessionManager.shutdown;

public class Main {

    public static void main(String[] args) {
        try {
            mappedSuperclassExample();
            singleTable();
        } finally {
            shutdown();
        }
    }

    private static void singleTable() {
        runInTransaction(session -> {
            var restaurantBooking = new RestaurantBooking();
            restaurantBooking.setNumberOfPeople(1);
            restaurantBooking.setSpecialRequests("I want a bucked of flowers on my bed");
            session.save(restaurantBooking);

            var hotelBooking = new HotelBooking();
            hotelBooking.setRoomNumber(3);
            hotelBooking.setSpecialRequests("Throw the ring into her drink");
            session.save(hotelBooking);

            var result = session.createSQLQuery("SELECT * from booking").list();
            for (var row : result) {
                for (var column : (Object[]) row) {
                    System.out.print(column + "\t");
                }
                System.out.println("\r\n");
            }
        });
    }

    private static void mappedSuperclassExample() {
        runInTransaction(session -> {
            var userId = session.save(new User("John"));
            var countryId = session.save(new Country("Finland"));

            session.flush();
            session.clear();

            var user = session.find(User.class, userId);
            var country = session.find(Country.class, countryId);

            System.out.println(user);
            System.out.println(country);
        });
    }
}
