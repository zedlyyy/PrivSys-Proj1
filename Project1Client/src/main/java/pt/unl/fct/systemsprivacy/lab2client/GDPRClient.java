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
    private final String ALL_AGE_DATAPOINTS = "0";

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
                    System.out.println("Sensitivity: " + response.getCountSens());
                    System.out.println("Budget: " + response.getBudget());
                    break;
                case 2:
                    System.out.print("Average of the ages:\n");
                    request = new CountRequest("age", ALL_AGE_DATAPOINTS);
                    response = apiService.postAverageResponse(request);
                    System.out.println("Attribute: " + response.getAttribute());
                    System.out.println("Response: " + response.getValue());
                    System.out.println("Count Sensitivity: " + response.getCountSens());
                    System.out.println("Sum Sensitivity: " + response.getSumSens());
                    System.out.println("Budget: " + response.getBudget());
                    break;
                case 3:
                    groupedResponse(cmd_in);
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

    private void groupedResponse(Scanner cmd_in) {
        System.out.println("Available grouped counts: \n");
        System.out.println("1. Average age of a specific diagnosis.\n");

        System.out.print("Please select one of the previous options.\n");
        System.out.print(">");
        String option = cmd_in.nextLine();
        switch (option) {
            case "1":

                System.out.print("What's the diagnosis code?\n");
                System.out.print(">");
                String value = cmd_in.nextLine();

                CountRequest request = new CountRequest("diagnosis", value);
                CountResponse response = apiService.postAverageGroupedResponse(request);

                System.out.println("Attribute: " + response.getAttribute());
                System.out.println("Response: " + response.getValue());
                System.out.println("Count Sensitivity: " + response.getCountSens());
                System.out.println("Sum Sensitivity: " + response.getSumSens());
                System.out.println("Budget: " + response.getBudget());
                
                break;
            default:
                break;
        }
    }

}

