package ca.hccis.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JOptionPane;
import ca.hccis.bo.OrderBO;

import ca.hccis.threads.Runner1;
import ca.hccis.threads.Runner2;

/**
 * @author idam
 * @since 100226
 */

public class Controller {

    // Stores all orders
    static ArrayList<Order> orders = new ArrayList<>();

    // Keeps track of the next order number
    static AtomicInteger nextOrderNumber = new AtomicInteger(1);
    static AtomicInteger journalEntriesCount = new AtomicInteger(0);

    // File location
    static String folder = "C:\\cis2232";
    static String fileName = folder + "\\data_morrison_isaac.json";

    // Scanner for user input
    public static Scanner scanner = new Scanner(System.in);

    // Gson for JSON
    static Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    // MAIN METHOD
    public static void main(String[] args) throws InterruptedException {

        createFolder();

        loadData();

        Thread runner1 = new Thread(new Runner1());
        Thread runner2 = new Thread(new Runner2());

        runner1.start();
        runner2.start();

        runner1.join();
        runner2.join();
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
    public static void addOrder() {

        Order order = new Order(nextOrderNumber.getAndIncrement());

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

        saveData();

        System.out.println();
        System.out.println("Order #" + order.getOrderNum() + " saved!");

        System.out.printf("Subtotal: $%.2f%n", order.getSubtotal());
        System.out.printf("Tax: $%.2f%n", order.getTax());
        System.out.printf("Total: $%.2f%n", order.getTotal());
    }

    // Add one sandwich to an order
    public static void addSandwich(Order order) {

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
    public static void viewOrders() {

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
    public static synchronized void saveData() {

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

                    if (order.getOrderNum() >= nextOrderNumber.get()) {

                        nextOrderNumber.set(order.getOrderNum());
                    }
                }
            }

            System.out.println("Previous data loaded.");

        } catch (Exception e) {

            System.out.println("Error loading data.");
        }
    }

    public static void orderGUI() {

        String choice = "";

        while (!choice.equals("Exit")) {
            String[] options = {"Add Order", "View Orders", "Exit"};

            int selected = JOptionPane.showOptionDialog(
                    null,
                    "Aldo Reny's Gourmet Panini",
                    "Panini Shop",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (selected == 0) {
                addOrderGUI();
            } else if (selected == 1) {
                viewOrdersGUI();
            } else {
                choice = "Exit";
            }
        }

        saveData();
    }

    public static void addOrderGUI() {

        Order order = new Order(nextOrderNumber.get());

        while (true) {
            addSandwichGUI(order);

            if (order.getSandwiches().isEmpty()) {
                return;
            }

            int another = JOptionPane.showConfirmDialog(
                    null,
                    "Add another sandwich?",
                    "Panini Shop",
                    JOptionPane.YES_NO_OPTION
            );

            if (another != JOptionPane.YES_OPTION) {
                break;
            }
        }

        synchronized (orders) {
            nextOrderNumber.incrementAndGet();
            orders.add(order);
            journalEntriesCount.incrementAndGet();
        }

        saveData();

        double total = new OrderBO().calculate(order);

        JOptionPane.showMessageDialog(
                null,
                "Order #" + order.getOrderNum()
                        + "\nSubtotal: $" + String.format("%.2f", order.getSubtotal())
                        + "\nTax: $" + String.format("%.2f", order.getTax())
                        + "\nTotal: $" + String.format("%.2f", total)
                        + "\nOrders recorded: " + journalEntriesCount.get(),
                "Order Complete",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static void addSandwichGUI(Order order) {

        String[] sandwiches = {
                "Classic Italian",
                "Caprese",
                "Meatball",
                "Chicken Pesto",
                "Vegetariano",
                "Custom Sandwich"
        };

        String sandwich = (String) JOptionPane.showInputDialog(
                null,
                "Choose a sandwich:",
                "Sandwich Menu",
                JOptionPane.QUESTION_MESSAGE,
                null,
                sandwiches,
                sandwiches[0]
        );

        if (sandwich == null) {
            return;
        }

        String[] sizes = {"Lunch - $10.00", "Full - $15.00"};

        String size = (String) JOptionPane.showInputDialog(
                null,
                "Choose size:",
                "Sandwich Size",
                JOptionPane.QUESTION_MESSAGE,
                null,
                sizes,
                sizes[0]
        );

        if (size == null) {
            return;
        }

        double price;

        if (size.equals("Lunch - $10.00")) {
            price = 10.00;
        } else {
            price = 15.00;
        }

        String[] breads = {"Ciabatta - $0.00", "Focaccia - $2.00"};

        String bread = (String) JOptionPane.showInputDialog(
                null,
                "Choose bread:",
                "Bread Choice",
                JOptionPane.QUESTION_MESSAGE,
                null,
                breads,
                breads[0]
        );

        if (bread == null) {
            return;
        }

        if (bread.equals("Focaccia - $2.00")) {
            price += 2.00;
        }

        if (sandwich.equals("Custom Sandwich")) {

            String[] toppings = {
                    "Mozzarella",
                    "Provolone",
                    "Salami",
                    "Pepperoni",
                    "Ham",
                    "Meatball",
                    "Chicken",
                    "Tomato",
                    "Peppers"
            };

            String chosenToppings = "";

            for (int i = 1; i <= 4; i++) {

                String topping = (String) JOptionPane.showInputDialog(
                        null,
                        "Choose topping " + i + " of 4:",
                        "Custom Toppings",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        toppings,
                        toppings[0]
                );

                if (topping == null) {
                    return;
                }

                chosenToppings += topping;

                if (i < 4) {
                    chosenToppings += ", ";
                }
            }

            String[] sauces = {"Pesto", "Mayo", "Olive Oil", "Balsamic"};

            String sauce = (String) JOptionPane.showInputDialog(
                    null,
                    "Choose one sauce:",
                    "Sauce Choice",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    sauces,
                    sauces[0]
            );

            if (sauce == null) {
                return;
            }

            sandwich = "Custom Sandwich (" + chosenToppings + ", " + sauce + ")";
        }

        order.addSandwich(sandwich, price);

        JOptionPane.showMessageDialog(
                null,
                sandwich + " added!",
                "Panini Shop",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static void viewOrdersGUI() {

        synchronized (orders) {

            if (orders.isEmpty()) {
                JOptionPane.showMessageDialog(null, "There are no orders.");
                return;
            }

            for (Order order : orders) {

                double total = new OrderBO().calculate(order);

                JOptionPane.showMessageDialog(
                        null,
                        "Order #" + order.getOrderNum()
                                + "\nSandwiches: " + order.getSandwiches()
                                + "\nSubtotal: $" + String.format("%.2f", order.getSubtotal())
                                + "\nTax: $" + String.format("%.2f", order.getTax())
                                + "\nTotal: $" + String.format("%.2f", total),
                        "Order Details",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }
}