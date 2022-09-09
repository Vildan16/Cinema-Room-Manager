package cinema;

import java.util.Scanner;

public class Cinema {
    private static Scanner scanner = new Scanner(System.in);
    private static char[][] cinema;
    private static int purchasedTickets = 0;
    private static int currentIncome = 0;


    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        cinema = new char[rows + 1][seats + 1];
        generateCinema();

        while (true) {
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");
            int menuChoice = scanner.nextInt();
            switch (menuChoice) {
                case 1 -> printCinema();
                case 2 -> buyTicket();
                case 3 -> showStatistics();
            }
            if (menuChoice == 0)
                break;
        }
    }

    private static void showStatistics() {
        int totalSeats = (cinema.length - 1) * (cinema[0].length - 1);
        int totalIncome = getTotalIncome();

        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", (double)purchasedTickets / (double)totalSeats * 100);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);

    }

    private static int getTotalIncome() {
        int result = 0;
        if ((cinema.length - 1) * (cinema[0].length - 1) <= 60) {
            return (cinema.length - 1) * (cinema[0].length - 1) * 10;
        }
        else {
            int frontRows = (cinema.length - 1) / 2;
            result += frontRows * (cinema[0].length - 1) * 10;
            result += (cinema.length - 1 - frontRows) * (cinema[0].length - 1) * 8;
        }
        return result;
    }

    private static void buyTicket() {
        int[] place = new int[2];
        System.out.println("Enter a row number:");
        place[0] = scanner.nextInt();

        System.out.println("Enter a seat number in that row:");
        place[1] = scanner.nextInt();

        if (place[0] > cinema.length - 1 || place[1] > cinema[0].length - 1) {
            System.out.println("Wrong input!");
            buyTicket();
        }
        else if (cinema[place[0]][place[1]] == 'B') {
            System.out.println("That ticket has already been purchased!");
            buyTicket();
        }
        else {
            cinema[place[0]][place[1]] = 'B';
            purchasedTickets++;
            printPrice(place);
        }
    }

    private static void printCinema() {
        System.out.println("\nCinema:");
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[0].length; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void printPrice(int[] place) {
        int price;
        if (cinema.length * cinema[0].length <= 60)
            price = 10;
        else {
            int frontRows = (cinema.length - 1) / 2;
            if (place[0] <= frontRows)
                price = 10;
            else
                price = 8;
        }
        currentIncome += price;
        System.out.println("\nTicket price: $" + price);
    }

    private static void generateCinema() {
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[0].length; j++) {
                if (i == 0 && j != 0)
                    cinema[i][j] = String.valueOf(j).charAt(0);
                else if (j == 0 && i != 0)
                    cinema[i][j] = String.valueOf(i).charAt(0);
                else if (i == 0)
                    cinema[i][j] = ' ';
                else
                    cinema[i][j] = 'S';
            }
        }
    }
}