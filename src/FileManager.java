import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    public static List<Customer> readCustomersFromFile(Path filePath) {
        
        List<Customer> customers = new ArrayList<>();
        try (Scanner scanner = new Scanner(filePath)) {
            String firstLine;
            String secondLine;
            while (scanner.hasNext()) {
                firstLine = scanner.nextLine();
                if (scanner.hasNext()) {
                    secondLine = scanner.nextLine();
                    String[] parts = firstLine.split(",");
                    customers.add(new Customer(parts[0].trim(), parts[1].trim(), LocalDate.parse(secondLine.trim())));
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Filen kunde inte hittas");
        } catch (IOException e) {
            System.out.println("Det gick inte att läsa från filen");
        } catch (Exception e) {
            System.out.println("Något gick fel");
        }
        return customers;

    }

    public static void registerVisitInFile(String outputFilePath, Customer customer) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(outputFilePath, true)
        )) {
            bufferedWriter.append(customer.getPersonalNumber() + ", " + customer.getName() + ", " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            bufferedWriter.newLine();
        } catch (FileNotFoundException e) {
            System.out.println("Filen kunde inte hittas");
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Det gick inte att skriva till fil");
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Något gick fel");
            e.printStackTrace();
            System.exit(0);
        }
    }
}
