package me.nathangreen.digitalartefact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static me.nathangreen.digitalartefact.Utils.menu;

public class PointOfSale {
    public Connection conn;
    public Scanner sc;
    public Config config;

    public CustomerDao customerDao;
    public ItemDao itemDao;

    PointOfSale(String databasePath) {
        try {
            this.conn = DriverManager.getConnection(databasePath);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        this.sc = new Scanner(System.in);
        this.config = makeConfig();

        this.customerDao = new CustomerDao(conn);
        this.itemDao = new ItemDao(conn);
    }

    public Config makeConfig() {
        System.out.println("Hello and welcome! Let's collect some data to begin...");
        System.out.print("Firstly, what company are you setting up the EPOS for? ");
        String company = sc.nextLine();
        System.out.print("What's your first name? ");
        String fName = sc.nextLine();
        System.out.printf("Great, thanks %s. Can you provide your surname too? ", fName);
        String lName = sc.nextLine();
        return new Config(company, fName, lName);
    }

    public void mainMenuLoop() {
        boolean cont = true;
        while (cont) {
            int menuChoice = menu("Main menu for " + config.getCompany(), new String[]{
                    "Open point of sale",
                    "Manage stock and inventory",
                    "Manage customers"
            }, sc);
            switch (menuChoice) {
                case 1:
                    pointOfSaleMenu();
                    break;
                case 2:
                    stockManagementMenu(sc, itemDao);
                    break;
                case 3:
                    customerManagementMenu();
                    break;
                default:
                    System.out.println("Please enter a valid menu option.");
            }
            System.out.print("\nReturning to menu...\nContinue? [Y/n] ");
            cont = !sc.nextLine().trim().toLowerCase().startsWith("n");
        }
    }

    public void stockManagementMenu(Scanner sc, ItemDao itemDao) {
        List<Item> items = itemDao.getAll();
        List<String> menuItems = items.stream().map(item -> String.format(
                "Manage %s (%s, %s in stock)",
                item.getName(),
                item.getPriceFormatted(),
                item.getStock()
        )).toList();

        menuItems = new ArrayList<>(menuItems);
        menuItems.add("Add new item");

        int choice = menu("Stock management", menuItems.toArray(new String[0]), sc);
        boolean valid = false;
        while (!valid) {
            if (choice == menuItems.size()) {
                valid = true;

                System.out.println("Enter the name of the new item");
                String name = sc.nextLine();

                System.out.println("Enter the price of the item in £");
                System.out.print("£");
                double price = sc.nextDouble();
                sc.nextLine();

                System.out.println("Enter the current stock of the item");
                int stock = sc.nextInt();
                sc.nextLine();

                System.out.println("Enter the rating of the item");
                int rating = sc.nextInt();
                sc.nextLine();

                Item item = new Item(price, name, stock, rating);
                this.itemDao.add(item);
            } else if (choice >= 1 && choice <= items.size()) {
                valid = true;
                Item item = items.get(choice - 1);

                System.out.printf("Enter new price for %s (currently %s)\n£", item.getName(), item.getPriceFormatted());
                double price = sc.nextDouble();
                sc.nextLine();
                item.setPrice(price);

                System.out.printf("Enter new stock for %s (currently %s)\n", item.getName(), item.getStock());
                int stock = sc.nextInt();
                sc.nextLine();
                item.setStock(stock);

                itemDao.save(item);
            } else {
                System.out.println("Please enter a valid menu option.");
            }
        }
    }

    public static void pointOfSaleMenu() {
        System.out.println("This functionality is coming soon. Returning to menu...");
    }

    public void customerManagementMenu() {
        List<Customer> customers = customerDao.getAll();
        List<String> menuItems = customers.stream().map(customer -> String.format(
                "Manage %s (%s)",
                customer.getName(),
                customer.getPhoneNumberFormatted()
        )).toList();

        menuItems = new ArrayList<>(menuItems);
        menuItems.add("Add new customer");

        int choice = menu("Customer management", menuItems.toArray(new String[0]), sc);
        boolean valid = false;
        while (!valid) {
            if (choice == menuItems.size()) {
                valid = true;

                System.out.println("Enter the first name of the new customer");
                String firstName = sc.nextLine();

                System.out.println("Enter the last name of the new customer");
                String lastName = sc.nextLine();

                System.out.println("Enter the phone number for the customer");
                String phone = sc.nextLine();

                System.out.println("Enter address: line 1");
                String line1 = sc.nextLine();

                System.out.println("Enter address: line 2");
                String line2 = sc.nextLine();

                System.out.println("Enter address: town");
                String town = sc.nextLine();

                System.out.println("Enter address: postcode");
                String postcode = sc.nextLine();

                Customer customer = new Customer(phone, firstName, lastName, new Address(line1, line2, town, postcode));
                this.customerDao.add(customer);
            } else if (choice >= 1 && choice <= customers.size()) {
                valid = true;
                Customer customer = customers.get(choice - 1);

                System.out.printf("Enter new phone number for %s (currently %s)\n", customer.getName(), customer.getPhoneNumberFormatted());
                String phone = sc.nextLine();
                customer.setPhoneNumber(phone);

                customerDao.save(customer);
            } else {
                System.out.println("Please enter a valid menu option.");
            }
        }
    }
}
