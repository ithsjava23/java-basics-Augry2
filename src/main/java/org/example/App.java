package org.example;

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

    private static void bestChargingHours(int[][] priceList) {

        int[] prices4Hours = new int[20]; // will not check prices after 20 because theres not 4 hours left in the day

        // adding prices for each 4 hour bracket and putting that value into a new list
        for (int i = 0; i < 20; i++) {
            prices4Hours[i] = priceList[i][PRICE] + priceList[i + 1][PRICE] + priceList[i + 2][PRICE] + priceList[i + 3][PRICE];
        }

        // finding the best time to start charging
        int lowestPrice = prices4Hours[0];
        int lowestPriceIndex = 0;
        for (int i = 1; i < prices4Hours.length; i++) {
            if (prices4Hours[i] < lowestPrice) {
                lowestPriceIndex = i;
                lowestPrice = prices4Hours[i];
            }
        }

        // calculating avarage
        double avarage = lowestPrice / 4.0;

        String output = String.format("Påbörja laddning klockan %d\nMedelpris 4h: %.1f öre/kWh\n", lowestPriceIndex, avarage);
        System.out.println(output);
    }

    private static void printSortedList(int[][] priceList) {
        // creating a copy of pricelist so we dont change the actual list, because it will mess with the output of alternative 4.
        int[][] copyOfPriceList = new int[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                copyOfPriceList[i][j] = priceList[i][j];
            }
        }

        bubbleSort2dArrayDecending(copyOfPriceList);
        print2dList(copyOfPriceList);
    }

    private static void print2dList(int[][] priceList) {

        String formattedString = "";

        for (int[] ints : priceList) {
            String startHour = String.format("%02d", ints[HOUR_START]);
            String stopHour = String.format("%02d", ints[HOUR_STOP]);
            String lineToPrint = startHour + "-" + stopHour + " " + ints[PRICE] + " öre\n";

            formattedString += lineToPrint;
        }
        System.out.println(formattedString);
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
        for (int[] ints : priceList) {
            sum += ints[PRICE];
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

    private static void bubbleSort2dArrayDecending(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j][2] < arr[j + 1][2]) { // Change the comparison direction to descending
                    int[] temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

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
            printMenuItems();

            String choice = fetch.nextLine();

            switch (choice) {
                case "1" -> priceList = handleInput(fetch);
                case "2" -> minMaxAvg(priceList);
                case "3" -> printSortedList(priceList);
                case "4" -> bestChargingHours(priceList);
                case "e", "E" -> {
                    System.out.println("Exiting program.. ");
                    runMenu = false;
                }
                default -> System.out.println("Invalid input..");
            }
        } while (runMenu);
    }

    private static void printMenuItems() {
        System.out.print("""
                Elpriser
                ========
                1. Inmatning
                2. Min, Max och Medel
                3. Sortera
                4. Bästa Laddningstid (4h)
                e. Avsluta
                """);
    }
}
