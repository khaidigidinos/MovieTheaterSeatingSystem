import java.awt.*;
import java.util.*;
import java.util.List;

public class MovieTheaterSeating {
    public final static  int DISTANCE_EACH_ROW = 3;
    public final static int MAX_EACH_ROW = 20 / (DISTANCE_EACH_ROW + 1);

    private int rows = 10;
    private int cols = 20;
    private Map<String, List<String>> map = new HashMap<>();
    private String[][] seats = new String[rows][cols];
    private int[] remainingSeatsEachRow = new int[rows];
    private int totalSeatsLeft = MAX_EACH_ROW * rows / 2;
    private int satisfactions = 0;
    private int totalCustomersHavingSeat = 0;
    private int totalCustomers = 0;
    private StringBuilder sb = new StringBuilder();

    private final List<Integer> orderToCheck = new ArrayList<>() {
        {
            add(4);
            add(2);
            add(6);
            add(0);
            add(8);
        }
    };

    public MovieTheaterSeating() {
        Arrays.fill(remainingSeatsEachRow, MAX_EACH_ROW);
    }

    public enum ReservationStatus {
        NEGATIVE_NUMBER_OR_ZERO, OUT_OF_SEATS, SUCCESS
    }

    public void bookSeatAndStoreResult(String reservation) {
        ReservationStatus status = bookSeat(reservation);
        String reservationNumber = reservation.split(" ")[0];
        if(status == ReservationStatus.OUT_OF_SEATS) {
            sb.append(reservationNumber + " not enough seats left\n");
        } else if(status == ReservationStatus.NEGATIVE_NUMBER_OR_ZERO) {
            sb.append(reservationNumber + " cannot book a negative number of seats or zero seat\n");
        } else {
            sb.append(reservationNumber + " ");
            map.get(reservationNumber).forEach((seat) -> {
                sb.append(seat + ",");
            });
            sb.deleteCharAt(sb.length() - 1);
            sb.append("\n");
        }
    }

    private ReservationStatus bookSeat(String reservation) {
        String[] array = reservation.split(" ");
        String reservationNumber = array[0];
        int numOfSeatsToBook = Integer.parseInt(array[1]);

        totalCustomers += numOfSeatsToBook;

        // Check for negative number or zero
        if(numOfSeatsToBook <= 0) {
            return ReservationStatus.NEGATIVE_NUMBER_OR_ZERO;
        }

        // Check for out of seats
        if(numOfSeatsToBook > totalSeatsLeft) {
            return ReservationStatus.OUT_OF_SEATS;
        }

        totalCustomersHavingSeat += numOfSeatsToBook;
        totalSeatsLeft -= numOfSeatsToBook;

        while(numOfSeatsToBook > MAX_EACH_ROW) {
            manageSeats(MAX_EACH_ROW, reservationNumber);
            numOfSeatsToBook -= MAX_EACH_ROW;
        }

        if(numOfSeatsToBook != 0) manageSeats(numOfSeatsToBook, reservationNumber);

        return ReservationStatus.SUCCESS;
    }

    private void manageSeats(int numOfSeats, String reservationNumber) {

        // Try to manage these customers in one row
        for(int rowOrder : orderToCheck) {
            if(numOfSeats <= remainingSeatsEachRow[rowOrder]) {
                for(int index = 0; index < cols && numOfSeats > 0; index += DISTANCE_EACH_ROW + 1) {
                    if(seats[rowOrder][index] == null) {
                        seats[rowOrder][index] = reservationNumber;
                        numOfSeats--;
                        satisfactions++; // Because all customers of one reservation seat in the same row
                        remainingSeatsEachRow[rowOrder]--;

                        updateResult("" + (char)(rowOrder + 65) + (index + 1), reservationNumber);
                    }
                }
            }
        }

        if(numOfSeats == 0) return;

        // Cannot find one row to allocate for these customers.
        // Therefore, try allocating for all remaining seats
        for(int rowOrder : orderToCheck) {
            if(remainingSeatsEachRow[rowOrder] > 0) {
                for(int index = 0; index < cols && numOfSeats > 0; index += 4) {
                    if(seats[rowOrder][index] == null) {
                        seats[rowOrder][index] = reservationNumber;
                        numOfSeats--;
                        remainingSeatsEachRow[rowOrder]--;
                        updateResult("" + (char)(rowOrder + 65) + (index + 1), reservationNumber);
                    }
                }
            }
        }
    }

    private void  updateResult(String seatNumber, String reservationNumber) {
        map.putIfAbsent(reservationNumber, new ArrayList<>());
        map.get(reservationNumber).add(seatNumber);
    }

    public void printResult() {
        System.out.println(sb);
    }

    public void printInformation() {
        System.out.println("Information: ");
        System.out.println("Total satisfactions: " + satisfactions);
        System.out.println("Total customers having seat: " + totalCustomersHavingSeat);
        System.out.println("Total seats left: " + totalSeatsLeft);
        System.out.println("Total customers tried to book: " + totalCustomers);
    }
}
