# MotionCutIntenship
Java Programming Internship at Motioncut, this repository contains all my work submitted to Motioncut 
import java.util.Random;
import javax.swing.JOptionPane;

public class Main_guessgame {

    void User_guess_get_and_store() {
        int i;
        int guesses[] = new int[4];

        for (i = 0; i < guesses.length; i++) {
            String input = JOptionPane.showInputDialog("Enter guess " + (i + 1));
            
            while (!isNumeric(input)) {
                // Keep prompting until a valid integer is entered
                input = JOptionPane.showInputDialog("Invalid input. Please enter an integer:");
            }

            guesses[i] = Integer.parseInt(input);
        }

        Random rand = new Random();
        int Random_result[] = new int[4];
        for (i = 0; i < Random_result.length; i++) {
            Random_result[i] = rand.nextInt(12);
            if (guesses[i] == Random_result[i]) {
                JOptionPane.showMessageDialog(null,
                        "Your guess for position " + (i + 1) + " is correct. It took " + (i + 1) + " tries.\nThe other number was: " + Random_result[i]);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Your guess for position " + (i + 1) + " is incorrect. It took " + (i + 1) + " tries.\nThe other number was: " + Random_result[i]);
            }
        }
    }

    private boolean isNumeric(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        Main game = new Main();
        game.User_guess_get_and_store();
    }
}
