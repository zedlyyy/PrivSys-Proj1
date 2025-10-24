package pt.unl.fct.systemsprivacy.lab2client;

import org.springframework.http.ResponseEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import pt.unl.fct.systemsprivacy.lab2client.model.CountRequest;
import pt.unl.fct.systemsprivacy.lab2client.model.CountResponse;
import pt.unl.fct.systemsprivacy.lab2client.service.ApiService;
import java.util.Scanner;

@SpringBootApplication
public class GDPRClient implements CommandLineRunner
{

    private final ApiService apiService;

    public GDPRClient(ApiService apiService) {
        this.apiService = apiService;
    }

    public static void main( String[] args )
    {
        SpringApplication.run(GDPRClient.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner cmd_in = new Scanner(System.in);
        while(true) {
            System.out.println("Choose a query to execute:");
            System.out.println("1. Count");
            System.out.println("2. Average");
            System.out.println("3. Grouped count");
            System.out.println("4. Exit");
            System.out.print(">");
            int i = cmd_in.nextInt();
            cmd_in.nextLine();

            switch(i) {
                case 1:
                    System.out.print("What's the attribute?\n");
                    System.out.print(">");
                    String attribute = cmd_in.nextLine();

                    System.out.print("What's the value?\n");
                    System.out.print(">");
                    String value = cmd_in.nextLine();

                    CountRequest request = new CountRequest(attribute, value);
                    CountResponse response = apiService.postCountResponse(request);
                    System.out.println("Attribute: " + response.getAttribute());
                    System.out.println("Response: " + response.getValue());
                    System.out.println("Sensitivity: " + response.getSens());
                    System.out.println("Budget: " + response.getBudget());
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("Exiting ....");
                    System.exit(0);
                default:
                    System.out.println("Please try again.");
                    break;
            }
        }
    } 
}

