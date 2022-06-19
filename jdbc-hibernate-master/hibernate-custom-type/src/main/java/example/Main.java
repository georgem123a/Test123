package example;

import example.db.Account;
import example.db.Currency;
import example.dto.IBAN;

import static example.SessionManager.runInTransaction;
import static example.SessionManager.shutdown;

public class Main {

    public static void main(String[] args) {
        try {
            runInTransaction(session -> {
                var accountId = session.save(
                  Account.builder()
                    .amountOfMoney(1000000f)
                    .currency(Currency.EUR)
                    .iban(IBAN.of("FR76 3000 6000 0112 3456 7890 189"))
                    .build()
                );

                session.flush();
                session.clear();

                var account = session.find(Account.class, accountId);
                System.out.println(account);
            });
        } finally {
            shutdown();
        }
    }
}
