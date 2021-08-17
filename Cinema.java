package cinema;
import java.util.Scanner;

public class Cinema {

    public static void buyTicket(int[][] arr, Scanner scanner) {
        int rown;
        int seatn;
        while (true) {
            System.out.println("Enter a row number:");
            rown = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatn = scanner.nextInt();
            if (rown < 0 || rown > arr.length || seatn < 0 || seatn > arr[0].length) {
                System.out.println("Wrong input!");
                continue;
            }
            if (arr.length * arr[0].length <= 60)
                System.out.println("Ticket price: $10");
            else {
                if (rown <= arr.length / 2)
                    System.out.println("Ticket price: $10");
                else
                    System.out.println("Ticket price: $8");
            }
            if (arr[rown - 1][seatn - 1] == 0) {
                arr[rown - 1][seatn - 1] = 1;
                break;
            }
            else
                System.out.println("That ticket has already been purchased!");
        }
    }

    public static int getInput(Scanner scanner) {
        System.out.println("1. Show the seats\n2. Buy a ticket\n3.Statistics\n0. Exit");
        return scanner.nextInt();
    }

    public static void showSeats(int[][] arr) {
        System.out.print("Cinema:\n  ");
        for (int i = 1; i <= arr[0].length; i++)
            System.out.print(i + " ");
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 1)
                    System.out.print(" B");
                else
                    System.out.print(" S");
            }
            System.out.println();
        }
    }

    public static void showStatistics(int[][] arr) {
        int purchasedTickets = 0;
        int currentIncome = 0;
        int totalIncome = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr.length * arr[0].length <= 60)
                    totalIncome += 10;
                else {
                    if ((arr.length % 2 == 0) && i <= arr.length / 2)
                        totalIncome += 10;
                    else if (i <= arr.length / 2 - 1)
                        totalIncome += 10;
                    else
                        totalIncome += 8;
                }
                if (arr[i][j] == 1) {
                    purchasedTickets++;
                    if ((arr.length % 2 == 0) && i <= arr.length / 2)
                        currentIncome += 10;
                    else if (i <= arr.length / 2 - 1)
                        currentIncome += 10;
                    else
                        currentIncome += 8;
                }
            }
        }
        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", (float)purchasedTickets / arr.length / arr[0].length * 100);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        int[][] arr = new int[rows][seats];
        int input = -1;
        while (input != 0) {
            input = getInput(scanner);
            if (input == 1)
                showSeats(arr);
            else if (input == 2)
                buyTicket(arr, scanner);
            else if (input == 3)
                showStatistics(arr);
        }
    }
}