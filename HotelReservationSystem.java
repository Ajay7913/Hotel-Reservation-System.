import java.io.*;

public class HotelReservationSystem {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static final String[] ROOM_TYPES = { "Single Occupancy", "Double Occupancy", "Triple Occupancy",
            "Deluxe Room",
            "Super Deluxe", "King Size", "Cottage", "Tent" };
    private static final double[] RENTS = { 1200, 2000, 3000, 5000, 6000, 7000, 8000, 2700 };
    private static final int[] ROOM_COUNTS = { 5, 10, 8, 7, 7, 8, 10, 10 };

    public void startHotelReservationSystem() {
        try {
            while (true) {
                displayMenu();

                System.out.println("Enter the Room Type: ");
                int option = getUserInput();

                if (option > 0 && option <= 8) {
                    processReservation(option, ROOM_TYPES, RENTS, ROOM_COUNTS);
                } else {
                    System.out.println("Entered wrong choice. Do you want to continue with the correct choice? " +
                            "Press Y for YES, any other character to exit: ");
                    String choice = reader.readLine();
                    if (!(choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("Yes"))) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void displayMenu() {
        System.out.println("\f Welcome To TRIDENT HOTEL AND LODGING  ");
        System.out.println("======================================================================");
        System.out.println("Press                  Type                Available   Rent");

        for (int i = 0; i < ROOM_TYPES.length; i++) {
            int diff = 26 - ROOM_TYPES[i].length();
            for (int j = 1; j <= diff; j++)
                ROOM_TYPES[i] += " ";
            System.out.println((i + 1) + "                  " + ROOM_TYPES[i] + ROOM_COUNTS[i] + "     Rs " + RENTS[i]);
        }
    }

    private int getUserInput() throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private void processReservation(int option, String[] roomTypes, double[] rents, int[] roomCount)
            throws IOException {
        if (roomCount[option - 1] == 0) {
            System.out.println("Sorry, no such room is available now. " +
                    "Do you want to try with other rooms? Press Y for Yes and any other key to EXIT: ");
            String choice = reader.readLine();
            if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("Yes"))
                return;
            else
                System.exit(0);
        } else {
            roomCount[option - 1]--;
            System.out.println("Please enter the name of the customer: ");
            String name = reader.readLine();
            System.out.println("Please enter the phone of the customer: ");
            long ph = Long.parseLong(reader.readLine());
            System.out.println("Please enter the number of days of booking: ");
            int d = Integer.parseInt(reader.readLine());
            printBill(name, ph, roomTypes[option - 1], d, rents[option - 1]);
            System.out.println("Do you want to continue to the next reservation? Y for YES, " +
                    "Any other character for EXIT : ");
            String choice = reader.readLine();
            if (!(choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("Yes")))
                System.exit(0);
        }
    }

    private void printBill(String name, long phone, String roomType, int days, double roomRent) {
        System.out.println(
                "============================\n------------------------------------------------------------------");
        System.out.println("            ***    TRIDENT HOTEL AND LODGING   ***");
        System.out.println("            ***   All Types Of Rooms Avilable   *** ");
        System.out.println("---------------------------------------------------------");
        System.out
                .println("            BILL NO :" + (int) (Math.random() * 100) + "7890" + (int) (Math.random() * 100));
        System.out.println("---------------------------------------------------------");
        System.out.println("      Name : " + name);
        System.out.println("      Phone : " + phone);
        System.out.println("      Room Type : " + roomType);
        System.out.println("     No of Days : " + days);
        System.out.println("---------------------------------------------------------");
        double totalAmount = days * roomRent;
        System.out.println("Total Payable Amount =   Rs " + totalAmount);
        System.out.println(
                "------------------------------------------------------------------\n====================================");
    }

    public static void main(String[] args) {
        new HotelReservationSystem().startHotelReservationSystem();
    }
}
