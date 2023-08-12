
import java.util.Scanner;

public class Cinema {

    private static int rowNum;
    private static int seatNum;
    private static String[][] cinemaArray;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        rowNum = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seatNum = scanner.nextInt();
        cinemaArray = new String[rowNum][seatNum];

        boolean exit = false;
        while (!exit) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int userInput = scanner.nextInt();
            switch (userInput) {
                case 1:
                    showSeats();
                    break;
                case 2:
                    buyTicket(scanner);
                    break;
                case 3:
                    showStatistics();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }

    private static void showSeats() {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= seatNum; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < rowNum; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seatNum; j++) {
                if (cinemaArray[i][j] == null) {
                    cinemaArray[i][j] = "S";
                }
                System.out.print(cinemaArray[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void buyTicket(Scanner scanner) {
        int row;
        int seat;
        boolean purchased = false;

        while (!purchased) {
            System.out.println("Enter a row number:");
            row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seat = scanner.nextInt();

            if (row < 1 || row > rowNum || seat < 1 || seat > seatNum) {
                System.out.println("Wrong input!");
            } else if (cinemaArray[row - 1][seat - 1] != null && cinemaArray[row - 1][seat - 1].equals("B")) {
                System.out.println("That ticket has already been purchased!");
            } else {
                cinemaArray[row - 1][seat - 1] = "B";
                int price = calculateTicketPrice(row);
                System.out.println("Ticket price: $" + price);
                purchased = true;
            }
        }
    }


    private static void showStatistics() {
        int purchasedTickets = 0;
        int totalSeats = rowNum * seatNum;
        int currentIncome = 0;

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < seatNum; j++) {
                if (cinemaArray[i][j] != null && cinemaArray[i][j].equals("B")) {
                    purchasedTickets++;
                    currentIncome += calculateTicketPrice(i + 1);
                }
            }
        }

        double percentageOccupancy = (double) purchasedTickets / totalSeats * 100;
        int totalIncome = calculateTotalIncome();

        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.println("Percentage: " + String.format("%.2f", percentageOccupancy) + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }


    private static int calculateTicketPrice(int row) {
        int totalSeats = rowNum * seatNum;
        int firstHalf = rowNum / 2;
        int secondHalf = rowNum - firstHalf;

        if (totalSeats < 60 || row <= firstHalf) {
            return 10;
        } else {
            return 8;
        }
    }

    private static int calculateTotalIncome() {
        int totalIncome = 0;

        for (int i = 1; i <= rowNum; i++) {
            totalIncome += calculateTicketPrice(i) * seatNum;
        }

        return totalIncome;
    }
}
