package ca.hccis.threads;

import ca.hccis.entity.Controller;

public class Runner1 implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println();
            System.out.println("------------------------------");
            System.out.println("     Italian Panini Shop");
            System.out.println("------------------------------");
            System.out.println("A) Add");
            System.out.println("V) View");
            System.out.println("X) Exit");
            System.out.println("------------------------------");
            System.out.print("Choose an option: ");

            String choice = Controller.scanner.nextLine().trim().toUpperCase();

            if (choice.equals("A")) {

                Controller.addOrder();

            } else if (choice.equals("V")) {

                Controller.viewOrders();

            } else if (choice.equals("X")) {

                Controller.saveData();

                System.out.println("Thanks for visiting!");

                break;

            } else {

                System.out.println("Invalid choice. Please choose A, V, or X.");
            }
        }
    }

}