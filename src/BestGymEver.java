import javax.swing.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BestGymEver {
    private final Path inputFilePath = Paths.get("medlemmar");
    private String outputFilePath = "träningspass";
    public static void main(String[] args) {
        BestGymEver bestGymEver = new BestGymEver();

        bestGymEver.run();
    }

    private void run() {
        List<Customer> customerList = FileManager.readCustomersFromFile(inputFilePath);
        
        
        while (true) {
            String userInput = JOptionPane.showInputDialog("Skriv in namnet eller personnumret på personen du söker");
            if (userInput == null) {
                JOptionPane.showMessageDialog(null, "Programmet avslutas.");
                break;
            }
            
            Customer customer = CustomerLogic.findCustomer(customerList, userInput.trim());
            if (customer != null) {
                if (CustomerLogic.hasPaidAnnualFee(customer)) {
                    System.out.println("Kunden är en nuvarande medlem");
                    FileManager.registerVisitInFile(outputFilePath, customer);
                } else {
                    System.out.println("Kunden är en före detta kund");
                }
            } else {
                System.out.println("Personen inte finns i filen och har sålunda aldrig varit medlem och är obehörig.");
            }
        }
        


    }
}
