package entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author idam
 * @since 092526
 */

public class Controller {

    // Stores all orders
    static ArrayList<Order> orders = new ArrayList<>();

    // Keeps track of the next order number
    static int nextOrderNumber = 1;

    // File location
    static String folder = "C:\\cis2232";
    static String fileName = folder + "\\data_morrison_isaac.json";

    // Scanner for user input
    static Scanner scanner = new Scanner(System.in);

    // Gson for JSON
    static Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    // MAIN METHOD
    public static void main(String[] args) {

        createFolder();

        loadData();

        while (true) {

            System.out.println();
            System.out.println("------------------------------");
            System.out.println("     Italian Panini Shop");
            System.out.println("------------------------------");
            System.out.println("A) Add");
            System.out.println("V) View");
            System.out.println("X) eXit");
            System.out.println("------------------------------");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().toUpperCase();

            if (choice.equals("A")) {

                addOrder();

            } else if (choice.equals("V")) {

                viewOrders();

            } else if (choice.equals("X")) {

                saveData();

                System.out.println("Thanks for visiting!");

                break;

            } else {

                System.out.println("Invalid choice. Please choose A, V, or X.");
            }
        }

        scanner.close();
    }

    // Create the C:\cis2232 folder
    static void createFolder() {

        File directory = new File(folder);

        if (!directory.exists()) {

            directory.mkdirs();

            System.out.println("Created C:\\cis2232 folder.");
        }
    }

    // Add a new order
    static void addOrder() {

        Order order = new Order(nextOrderNumber);

        System.out.println();
        System.out.println("========== ADD ORDER ==========");

        boolean adding = true;

        while (adding) {

            addSandwich(order);

            System.out.print("Add another sandwich? (Y/N): ");

            String answer = scanner.nextLine().toUpperCase();

            if (!answer.equals("Y")) {
                adding = false;
            }
        }

        orders.add(order);

        nextOrderNumber++;

        saveData();

        System.out.println();
        System.out.println("Order #" + order.getOrderNum() + " saved!");

        System.out.printf("Subtotal: $%.2f%n", order.getSubtotal());
        System.out.printf("Tax: $%.2f%n", order.getTax());
        System.out.printf("Total: $%.2f%n", order.getTotal());
    }

    // Add one sandwich to an order
    static void addSandwich(Order order) {

        System.out.println();
        System.out.println("Choose a sandwich:");

        System.out.println("1. Classic Italian");
        System.out.println("2. Caprese");
        System.out.println("3. Meatball");
        System.out.println("4. Chicken Pesto");
        System.out.println("5. Vegetariano");
        System.out.println("6. Custom Sandwich");

        System.out.print("Choose: ");

        int choice = Integer.parseInt(scanner.nextLine());

        String sandwich;

        if (choice == 1) {

            sandwich = "Classic Italian";

        } else if (choice == 2) {

            sandwich = "Caprese";

        } else if (choice == 3) {

            sandwich = "Meatball";

        } else if (choice == 4) {

            sandwich = "Chicken Pesto";

        } else if (choice == 5) {

            sandwich = "Vegetariano";

        } else {

            sandwich = "Custom Sandwich";
        }


        // Choose size
        System.out.println();
        System.out.println("Choose size:");
        System.out.println("1. Lunch - $10.00");
        System.out.println("2. Full - $15.00");

        System.out.print("Choose: ");

        int size = Integer.parseInt(scanner.nextLine());

        double price;

        if (size == 1) {

            price = 10.00;

        } else {

            price = 15.00;
        }


        // Choose bread
        System.out.println();
        System.out.println("Choose bread:");
        System.out.println("1. Ciabatta - $0.00");
        System.out.println("2. Focaccia - $2.00");

        System.out.print("Choose: ");

        int bread = Integer.parseInt(scanner.nextLine());

        if (bread == 2) {

            price += 2.00;
        }


        // Custom sandwich toppings
        if (choice == 6) {

            System.out.println();
            System.out.println("Choose 4 toppings:");

            System.out.println("1. Mozzarella");
            System.out.println("2. Provolone");
            System.out.println("3. Salami");
            System.out.println("4. Pepperoni");
            System.out.println("5. Ham");
            System.out.println("6. Meatball");
            System.out.println("7. Chicken");
            System.out.println("8. Tomato");
            System.out.println("9. Peppers");

            String toppings = "";

            for (int i = 1; i <= 4; i++) {

                System.out.print("Choose topping " + i + ": ");

                int toppingChoice = Integer.parseInt(scanner.nextLine());

                String topping;

                if (toppingChoice == 1) {
                    topping = "Mozzarella";
                } else if (toppingChoice == 2) {
                    topping = "Provolone";
                } else if (toppingChoice == 3) {
                    topping = "Salami";
                } else if (toppingChoice == 4) {
                    topping = "Pepperoni";
                } else if (toppingChoice == 5) {
                    topping = "Ham";
                } else if (toppingChoice == 6) {
                    topping = "Meatball";
                } else if (toppingChoice == 7) {
                    topping = "Chicken";
                } else if (toppingChoice == 8) {
                    topping = "Tomato";
                } else {
                    topping = "Peppers";
                }

                toppings += topping;

                if (i < 4) {
                    toppings += ", ";
                }
            }


            // Choose sauce
            System.out.println();
            System.out.println("Choose 1 sauce:");

            System.out.println("1. Pesto");
            System.out.println("2. Mayo");
            System.out.println("3. Olive Oil");
            System.out.println("4. Balsamic");

            System.out.print("Choose: ");

            int sauceChoice = Integer.parseInt(scanner.nextLine());

            String sauce;

            if (sauceChoice == 1) {
                sauce = "Pesto";
            } else if (sauceChoice == 2) {
                sauce = "Mayo";
            } else if (sauceChoice == 3) {
                sauce = "Olive Oil";
            } else {
                sauce = "Balsamic";
            }

            sandwich = "Custom Sandwich (" + toppings + ", " + sauce + ")";
        }


        // Add sandwich to order
        order.addSandwich(sandwich, price);

        System.out.println();
        System.out.println(sandwich + " added!");
    }


    // Display all orders
    static void viewOrders() {

        System.out.println();
        System.out.println("========== ORDERS ==========");

        if (orders.isEmpty()) {

            System.out.println("There are no orders.");

            return;
        }

        for (Order order : orders) {

            System.out.println();
            System.out.println("Order #" + order.getOrderNum());

            System.out.println("Sandwiches:");

            for (String sandwich : order.getSandwiches()) {

                System.out.println("- " + sandwich);
            }

            System.out.printf("Subtotal: $%.2f%n", order.getSubtotal());
            System.out.printf("Tax: $%.2f%n", order.getTax());
            System.out.printf("Total: $%.2f%n", order.getTotal());

            System.out.println("-----------------------------");
        }
    }

    // Save orders to JSON
    static void saveData() {

        try {

            File directory = new File(folder);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            File file = new File(fileName);

            FileWriter writer = new FileWriter(file);

            gson.toJson(orders, writer);

            writer.close();

            System.out.println("SAVE SUCCESSFUL");
            System.out.println("File: " + file.getAbsolutePath());

        } catch (Exception e) {

            System.out.println("SAVE FAILED");
        }
    }


    // Load orders from JSON
    static void loadData() {

        File file = new File(fileName);

        if (!file.exists()) {

            System.out.println("No previous data found.");

            return;
        }

        try {

            FileReader reader = new FileReader(fileName);

            Order[] savedOrders = gson.fromJson(
                    reader,
                    Order[].class
            );

            reader.close();

            if (savedOrders != null) {

                for (Order order : savedOrders) {

                    orders.add(order);

                    if (order.getOrderNum() >= nextOrderNumber) {

                        nextOrderNumber = order.getOrderNum() + 1;
                    }
                }
            }

            System.out.println("Previous data loaded.");

        } catch (Exception e) {

            System.out.println("Error loading data.");
        }
    }
}

