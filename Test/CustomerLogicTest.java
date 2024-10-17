import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class CustomerLogicTest {

    Customer customerOverdue = new Customer("7703021234", "Alhambra Aromes", LocalDate.now().minusYears(1).minusDays(1));
    Customer customerPaid = new Customer("8204021234", "Bear Belle", LocalDate.now().minusYears(1).plusDays(1));
    Customer customerPaidAYearAgo = new Customer("8512021234", "Chamade Coriola", LocalDate.now().minusYears(1));
    @Test
    void hasPaidAnnualFeeTest() {

        Assertions.assertFalse(CustomerLogic.hasPaidAnnualFee(customerOverdue));
        Assertions.assertTrue(CustomerLogic.hasPaidAnnualFee(customerPaid));
        Assertions.assertTrue(CustomerLogic.hasPaidAnnualFee(customerPaidAYearAgo));
    }


    @Test
    void findCustomerTest() {

        List<Customer> customers = Arrays.asList(customerOverdue, customerPaid, customerPaidAYearAgo);

        assertEquals(customerPaid, CustomerLogic.findCustomer(customers, customerPaid.getName()));
        assertEquals(customerPaid, CustomerLogic.findCustomer(customers, customerPaid.getPersonalNumber()));
        assertEquals(customerOverdue, CustomerLogic.findCustomer(customers, customerOverdue.getName()));
        assertEquals(customerOverdue, CustomerLogic.findCustomer(customers, customerOverdue.getPersonalNumber()));

        //Skapa en person som inte finns i listan (filen)
        Customer customerNotInList = new Customer("2313290123", "John Doe", LocalDate.now());

        //Kolla om personen finns i listan (filen)
        assertNull(CustomerLogic.findCustomer(customers, customerNotInList.getName()));
        assertNull(CustomerLogic.findCustomer(customers, customerNotInList.getPersonalNumber()));
    }
}