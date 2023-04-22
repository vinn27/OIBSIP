import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class OnlineExam {
    
    // Sample user credentials
    static HashMap<String, String> users = new HashMap<String, String>() {{
        put("admin", "123");
        put("student", "12345");
    }};
    
    // Sample questions and answers
    static HashMap<Integer, String[]> questions = new HashMap<Integer, String[]>() {{
        put(1, new String[]{"What is the capital of France?", "A) Paris", "B) Berlin", "C) Madrid", "A"});
        put(2, new String[]{"What is the highest mountain in the world?", "A) Mount Everest", "B) K2", "C) Makalu", "A"});
        put(3, new String[]{"What is the largest mammal on earth?", "A) Elephant", "B) Whale", "C) Giraffe", "B"});
        put(4, new String[]{"What is the Capital of Maharashtra?", "A) Nagpur", "B) Mumbai", "C) Chh.SambhajiNagar", "B"});
        put(5, new String[]{"Who has the highest no. of Centuries in Cricket?", "A) Virat Kohli", "B) Ajinkya Rahane", "C) Sachin Tendulkar", "C"});
    }};
    
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("Welcome to the Online Exam System!");
        login();
    }
    
    public static void login() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        if (users.containsKey(username) && users.get(username).equals(password)) {
            System.out.println("Login successful!");
            displayMenu(username);
        } else {
            System.out.println("Invalid username or password. Please try again.");
            login();
        }
    }
    
    public static void displayMenu(String username) {
        System.out.println("Hello, " + username + "! What would you like to do?");
        System.out.println("1. Update profile and password");
        System.out.println("2. Start exam");
        System.out.println("3. Logout");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                updateProfile(username);
                break;
            case 2:
                startExam();
                break;
            case 3:
                logout();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                displayMenu(username);
                break;
        }
    }
    
    public static void updateProfile(String username) {
        System.out.print("New password: ");
        String newPassword = scanner.nextLine();
        users.put(username, newPassword);
        System.out.println("Password updated successfully!");
        displayMenu(username);
    }
    
    public static void startExam() {
        int totalQuestions = questions.size();
        int correctAnswers = 0;
        int remainingTime = 120; // 5 minutes
        System.out.println("You have " + remainingTime + " seconds to complete the exam.");
        for (int i = 1; i <= totalQuestions; i++) {
            System.out.println("Question " + i + ": " + questions.get(i)[0]);
            System.out.println(questions.get(i)[1]);
            System.out.println(questions.get(i)[2]);
            System.out.println(questions.get(i)[3]);
            String answer = scanner.nextLine();
            if (answer.equals(questions.get(i)[4])) {
                correctAnswers++;
            }
            remainingTime -= 30; // Each question takes 30 seconds to answer
            if (remainingTime <= 0) {
                // System.out.println("Time's up! Your exam has been automatically submitted.");
                break;
            }
        }
        System.out.println("You have completed the exam!");
        System.out.println("You got " + correctAnswers + " out of " + totalQuestions + " questions correct.");
        
        // Display time of submission
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date submissionTime = new Date(System.currentTimeMillis());
        System.out.println("Exam submitted at " + formatter.format(submissionTime));
        
        logout();
    }
    
    public static void logout() {
        System.out.println("Logging out...");
        System.out.println("Goodbye!");
        System.exit(0);
    }
}

