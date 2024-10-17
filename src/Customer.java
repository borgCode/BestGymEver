import java.time.LocalDate;

public class Customer {
    private final String personalNumber;
    private final String name;
    private final LocalDate lastPaidAnnualFee;

    public Customer(String personalNumber, String name, LocalDate lastPaidAnnualFee) {
        this.personalNumber = personalNumber;
        this.name = name;
        this.lastPaidAnnualFee = lastPaidAnnualFee;
    }


    public String getPersonalNumber() {
        return personalNumber;
    }

    public String getName() {
        return name;
    }

    public LocalDate getLastPaidAnnualFee() {
        return lastPaidAnnualFee;
    }
}
