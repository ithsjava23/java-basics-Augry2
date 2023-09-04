package org.example;

import java.util.Random;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner fetch = new Scanner(System.in);
        int[] priceList = new int[24];

        showMenu(fetch, priceList);

    }

    private static void sortList(int[] arr){

        for (int i = 0; i < arr.length - 1 ; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {

            }
        }


    }

    // for testing purpose
    private static int[] handleInputRandomNr() {

        Random rand = new Random();
        int[] tempList = new int[24];

        for (int i = 0; i < tempList.length; i++) {
            int randomNr = rand.nextInt(25)+1; // generate random nr between 1 - 25
            tempList[i] = randomNr;
        }
        return tempList;
    }

    private static int[] handleInput(Scanner fetch) {

        int value;
        int[] tempList = new int[24];

        for (int i = 0; i < 24; i++) {

            boolean validEntry = false;

            // repeat this until user has entered a valid entry
            while (!validEntry) { // run while validEntry is false
                System.out.println("enter value for " + i + " to " + (i + 1));
                if (fetch.hasNextInt()) {
                    value = fetch.nextInt();
                    fetch.nextLine();
                    tempList[i] = value;
                    validEntry = true; // jump out of while, and go to next iteration in the for loop
                } else {
                    System.out.println("invalid entry, try again..");
                    fetch.nextLine();
                }
            }
        }

        return tempList;


    }

    private static void showMenu(Scanner fetch, int[] priceList) {
        boolean runMenu = true;
        do {
            System.out.print("Elpriser\n" +
                    "========\n" +
                    "1. Inmatning\n" +
                    "2. Min, Max och Medel\n" +
                    "3. Sortera\n" +
                    "4. Bästa Laddningstid (4h)\n" +
                    "e. Avsluta\n");

            String choice = fetch.nextLine();

            switch (choice) {

                case "1": {
                    //priceList = handleInput(fetch);
                    priceList = handleInputRandomNr();
                    break;
                }

                case "2": {

                    break;
                }
                case "3": {
                    System.out.println("test3");
                    break;
                }
                case "4": {
                    System.out.println("test4");
                    break;
                }

                case "e", "E": {
                    System.out.println("Exiting program.. ");
                    runMenu = false;
                    break;
                }
                default:
                    System.out.println("Invalid input..");
            }


        } while (runMenu);
        {


        }
    }
}
