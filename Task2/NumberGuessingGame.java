import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int secretNumber = random.nextInt(100) + 1;
        int guess = 0;
        int numAttempts = 0;
        String name;

        System.out.println("Guess a number between 1 and 100:");

        while (guess != secretNumber) {
            guess = scanner.nextInt();
            numAttempts++;

            if (guess < secretNumber) {
                System.out.println("Too low. Guess again:");
            } else if (guess > secretNumber) {
                System.out.println("Too high. Guess again:");
            } else {
                System.out.println("Congratulations! You guessed the number in " + numAttempts + " attempts.");
            }
        }

        scanner.close();
    }
}