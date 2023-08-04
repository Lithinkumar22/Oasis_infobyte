import java.util.Random;
import java.util.Scanner;
class GuessTheNumber{
    public static void main(String[] args) {
        playGuessTheNumberGame();
    }
    public static void playGuessTheNumberGame() {
        Scanner scanner=new Scanner(System.in);
        Random random=new Random();
        int min=1;
        int max=100;
        int targetted=random.nextInt(max-min+1)+min;
        int maxAttempt=10;
        int attempts=0;
        int score=0;
        System.out.println("I choose a number between " + min + " and " + max + ".");
        System.out.println("You have " + maxAttempt + " attempts to guess the number.");
        while (attempts < maxAttempt) {
            System.out.print("Attempt " + (attempts + 1) + ": Enter your guessed number = ");
            int guess = scanner.nextInt();
            if (guess == targetted) {
                System.out.println("You guessed the number " + targetted + " in " + (attempts + 1) + " attempts.");
                score += (maxAttempt - attempts) * 10;
                break;
            } else if (guess < targetted) {
                System.out.println("Your guess number  is lower than the target number.");
            } else {
                System.out.println("Your guess number  is higher than the target number.");
            }
            attempts++;
        }
        if (attempts==maxAttempt) {
            System.out.println("Your attempts are over . The target number is: " + targetted);
        }
        System.out.println("Your score: " + score);
        System.out.print(" want to play again? (yes/no): ");
        String playAgain = scanner.next();
        if (playAgain.equalsIgnoreCase("yes")) {
            playGuessTheNumberGame();
        } else {
            System.out.println("Thank you for playing! Goodbye!");
        }
        scanner.close();
    }
}
