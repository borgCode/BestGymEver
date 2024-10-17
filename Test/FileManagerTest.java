import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    Path inputFilePath = Paths.get("medlemmar");
    String outputFilePath = "träningspassTest";
    @Test
    void readPeopleFromFileTest() {
        List<Customer> people = FileManager.readCustomersFromFile(inputFilePath);
        assertEquals(14, people.size());
        assertNotEquals(12, people.size());

    }

    @Test
    void readExactDataFromFileTest() {
        List<Customer> customers = FileManager.readCustomersFromFile(inputFilePath);

        assertEquals("7703021234", customers.get(0).getPersonalNumber());
        assertEquals("Alhambra Aromes", customers.get(0).getName());
        assertEquals(LocalDate.of(2024, 7, 1), customers.get(0).getLastPaidAnnualFee());

        assertNotEquals("8204021234", customers.get(0).getPersonalNumber());
        assertNotEquals("Bear Belle", customers.get(0).getName());
        assertNotEquals(LocalDate.of(2019, 12, 2), customers.get(0).getLastPaidAnnualFee());

        assertEquals("8204021234", customers.get(1).getPersonalNumber());
        assertEquals("Bear Belle", customers.get(1).getName());
        assertEquals(LocalDate.of(2019, 12, 2), customers.get(1).getLastPaidAnnualFee());

    }

    @Test
    void registerVisitInFileTest() {

        //Ta bort gamla testfilen
        new File(outputFilePath).delete();

        Customer customerOverdue = new Customer("7703021234", "Alhambra Aromes", LocalDate.now().minusYears(1).minusDays(1));
        Customer customerPaid = new Customer("8204021234", "Bear Belle", LocalDate.now().minusYears(1).plusDays(1));
        Customer customerPaidAYearAgo = new Customer("8512021234", "Chamade Coriola", LocalDate.now().minusYears(1));

        List<Customer> customers = Arrays.asList(customerOverdue, customerPaid, customerPaidAYearAgo);

        for (Customer customer : customers) {
            if (CustomerLogic.hasPaidAnnualFee(customer)) {
                FileManager.registerVisitInFile(outputFilePath, customer);
            }
        }

        assertEquals(2, countLinesInFile(outputFilePath));
        

        try(BufferedReader bufferedReader = new BufferedReader(
                new FileReader(outputFilePath))) {
            String s = bufferedReader.readLine();
            assertTrue(s.startsWith("8204021234, Bear Belle"));
            s = bufferedReader.readLine();
            assertTrue(s.startsWith("8512021234, Chamade Coriola"));
        } catch (FileNotFoundException e) {
            System.out.println("Filen kunde inte hittas");
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Det gick inte att läsa från filen");
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Något gick fel");
            e.printStackTrace();
            System.exit(0);
        }

    }

    public final int countLinesInFile(String fileToCount) {
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(
                new FileReader(fileToCount));) {
            while (reader.readLine() != null) lines++;
        }
        catch (FileNotFoundException e) {
            System.out.println("Filen kunde inte hittas");
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Det gick inte att läsa från filen");
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Något gick fel");
            e.printStackTrace();
            System.exit(0);
        }
        return lines;
    }
}