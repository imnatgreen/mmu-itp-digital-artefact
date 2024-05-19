package me.nathangreen.digitalartefact;

public class Main {
    public static void main(String[] args) {
        PointOfSale pos = new PointOfSale("jdbc:sqlite:database.db");

        System.out.printf("""
                Okay, that's everything! Welcome, %s :)
                Now, let's begin...
                """, pos.config.getFullName());

        pos.mainMenuLoop();

        System.out.printf("Bye %s!", pos.config.getFirstName());
        pos.sc.close();
    }
}