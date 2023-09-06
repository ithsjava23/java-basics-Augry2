package org.example;

import java.util.Random;
import java.util.Scanner;

public class App {
    final static int ROWS = 24;
    final static int COLUMNS = 3;
    final static int HOUR_START = 0;
    final static int HOUR_STOP = 1;
    final static int PRICE = 2;


    public static void main(String[] args) {
        Scanner fetch = new Scanner(System.in);
        int[][] priceList = new int[ROWS][COLUMNS];

        showMenu(fetch, priceList);

    }


    private static void minMaxAvg(int[][] priceList) {
        int lowestPriceIndex = 0;
        int highestPriceIndex = 0;

        // iterating through the list and if a lowest price is found its saved in a variable, same with highest
        for (int i = 1; i < priceList.length; i++) {
            if (priceList[i][PRICE] < priceList[lowestPriceIndex][PRICE]) {
                lowestPriceIndex = i;
            }
            if (priceList[i][PRICE] > priceList[highestPriceIndex][PRICE]) {
                highestPriceIndex = i;
            }
        }

        // formatting a leading 0 if sub 10
        String lowestPriceHours = String.format("%02d-%02d", priceList[lowestPriceIndex][HOUR_START], priceList[lowestPriceIndex][HOUR_STOP]);
        String highestPriceHours = String.format("%02d-%02d", priceList[highestPriceIndex][HOUR_START], priceList[highestPriceIndex][HOUR_STOP]);

        // Calculating the average price
        int sum = 0;
        for (int i = 0; i < priceList.length; i++) {
            sum += priceList[i][PRICE];
        }
        double average = (double) sum / priceList.length;
        // formatting so it contains only 2 decimals, and replaces . with ,
        String formattedAverage = String.format("%.2f", average).replace('.', ',');

        String output = String.format("Lägsta pris: %s, %d öre/kWh\nHögsta pris: %s, %d öre/kWh\nMedelpris: %s öre/kWh\n",
                lowestPriceHours, priceList[lowestPriceIndex][PRICE],
                highestPriceHours, priceList[highestPriceIndex][PRICE],
                formattedAverage);

        System.out.println(output);
    }

    // for testing purposes
    private static void print2dList(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            // Print a newline after each row
            System.out.println();
        }
    }

    private static void bubbleSort2dArray(int[][] arr) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < ROWS - i - 1; j++) {
                if (arr[j][2] > arr[j + 1][2]) {
                    int[] temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }


    // for testing purpose
    private static int[][] handleInputRandomNr() {

        Random rand = new Random();
        int[][] tempList = new int[ROWS][COLUMNS];

        for (int i = 0; i < tempList.length; i++) {
            int randomNr = rand.nextInt(25) + 1; // generate random nr between 1 - 25
            tempList[i][HOUR_START] = i;
            tempList[i][HOUR_STOP] = i + 1;
            tempList[i][PRICE] = randomNr;
        }
        return tempList;
    }

    // TODO change into 2d array
    private static int[][] handleInput(Scanner fetch) {

        int value;
        int[][] tempList = new int[ROWS][COLUMNS];

        for (int i = 0; i < ROWS; i++) {

            boolean validEntry = false;

            // repeat this until user has entered a valid entry
            while (!validEntry) { // run while validEntry is false
                //System.out.println("enter value for " + i + " to " + (i + 1));
                if (fetch.hasNextInt()) {

                    value = fetch.nextInt();
                    fetch.nextLine();

                    tempList[i][PRICE] = value;
                    tempList[i][HOUR_START] = i;
                    tempList[i][HOUR_STOP] = i + 1;
                    validEntry = true; // jump out of while, and go to next iteration in the for loop
                } else {
                    System.out.println("invalid entry, try again..");
                    fetch.nextLine();
                }
            }
        }

        return tempList;


    }

    private static void showMenu(Scanner fetch, int[][] priceList) {
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
                    priceList = handleInput(fetch);
                    break;
                }

                case "2": {
                    minMaxAvg(priceList);
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

                case "e":
                case "E": {
                    System.out.println("Exiting program.. ");
                    runMenu = false;
                    break;
                }
                default:
                    System.out.println("Invalid input..");
            }
        } while (runMenu);
    }
}
