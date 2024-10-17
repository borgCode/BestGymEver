import java.time.LocalDate;
import java.util.List;

public class CustomerLogic {
    public static Customer findCustomer(List<Customer> customerList, String customerToFind) {
        for (Customer customer : customerList) {
            if (customerToFind.equals(customer.getPersonalNumber()) || customerToFind.equalsIgnoreCase(customer.getName())) {
                return customer;
            }
        }
        return null;
    }

    public static boolean hasPaidAnnualFee(Customer customer) {
        return LocalDate.now().isBefore(customer.getLastPaidAnnualFee().plusYears(1).plusDays(1));
    }
}
