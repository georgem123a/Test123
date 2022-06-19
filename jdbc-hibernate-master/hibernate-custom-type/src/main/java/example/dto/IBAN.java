package example.dto;

public class IBAN {

    private static String IBAN_PATTERN = "[a-zA-Z]{2}[0-9]{2}[a-zA-Z0-9]{4}[0-9]{7}([a-zA-Z0-9]?){0,16}";

    public static IBAN of(String value) {
        if (value == null || !value.replaceAll(" ", "").matches(IBAN_PATTERN)) {
            throw new IbanFormatException(value);
        }
        return new IBAN(value);
    }

    private final String value;

    private IBAN(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static class IbanFormatException extends RuntimeException {

        private IbanFormatException(String value) {
            super("The value " + value + " is not in the IBAN format");
        }

    }
}
