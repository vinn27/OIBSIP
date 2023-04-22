import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.random.*;
import java.util.function.*;

class Utils {
    public static <T> boolean listRemove(ArrayList<T> list, Predicate<T> pred) {
        int index = 0;

        for(T value : list) {
            if(pred.test(value)) {
                list.remove(index);
                return true;
            }
            ++index;
        }

        return false;
    }
}

class Reservation{

    private long pnr;
    private String source;
    private String destination;
    private int numberOfTickets;

    Reservation() {

    }



    public Reservation(long pnr, String source, String destination, int numberOfTickets) {
        this.pnr = pnr;
        this.source = source;
        this.destination = destination;
        this.numberOfTickets = numberOfTickets;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }
    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
    public long getPnr() {
        return pnr;
    }
    public void setPnr(long pnr) {
        this.pnr = pnr;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "PNR: " + this.getPnr() + "\n" +
            "Source: " + this.getSource() + "\n" +
            "Destination: " + this.getDestination() + "\n" +
            "Number of tickets: " + this.numberOfTickets + "\n";
    }
}

public class RailwayReservationSystem {

    private boolean loggedIn = false;
    private String username;
    private String password;

    private ArrayList<Reservation> reservations;
    // long pnr;

    RailwayReservationSystem() {
        this.reservations = new ArrayList<>();
    }

    private void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (username.equals(this.username) && password.equals(this.password)) {
            loggedIn = true;
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private void bookReservation() {
        if (!loggedIn) {
            System.out.println("You must be logged in to book a reservation.");
            return;
        }
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter source: ");
        String source = scanner.nextLine();

        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();

        System.out.print("Enter number of tickets: ");
        int numTickets = scanner.nextInt();
        scanner.nextLine();

        Random random = new Random();
        long pnr = (random.nextLong() % 90000000L) + 10000000L;

        reservations.add(
            new Reservation(pnr, source, destination, numTickets)
        );

        System.out.println("Reservation booked for " + numTickets + " tickets from " + source + " to " + destination + " with PNR no. :-" + pnr);
    }

    private void cancelReservation() {
        if (!loggedIn) {
            System.out.println("You must be logged in to cancel a reservation.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter PNR number: ");
        long pnr = scanner.nextLong();
        scanner.nextLine();

        System.out.println("THE PNR IS: " + pnr);

        if(Utils.listRemove(reservations, reservation -> {
            return reservation.getPnr() == pnr;
        })) {
            System.out.println("Reservations was successfully cancelled.");
        } else {
            System.out.println("Could not cancel the reservation.");
        }
        // if( reservationNumber == pnr)
        //     System.out.println("Reservation " + reservationNumber + " cancelled successfully.");
        // else{
        //     System.out.println("Entered Wrong PNR number");
        //     return;
        // }
    }

    public void listReservations() {
        System.out.println("\n[ Reservations ]\n");

        for(var reservation : reservations) {
            System.out.println(
                reservation.toString()
            );
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        this.username = scanner.nextLine();
        System.out.print("Enter password: ");
        this.password = scanner.nextLine();
        System.out.println("Railway reservation system started.");
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Book reservation");
            System.out.println("3. Cancel reservation");
            System.out.println("4. List reservations");
            System.out.println("5. Exit");
            System.out.print("Enter option: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    login();
                    break;
                case 2:
                    bookReservation();
                    break;
                case 3:
                    cancelReservation();
                    break;
                case 4:
                    listReservations();
                    break;
                case 5:
                    System.out.println("Railway reservation system exited.");
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        RailwayReservationSystem system = new RailwayReservationSystem();
        system.start();
    }
}