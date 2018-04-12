import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class BowlingScore {

    private static final String FILENAME = "C:\\workSpaceIJ\\input\\inputChallenge2.txt";
    private static final int STRIKE = 10;

    public static HashMap<Integer, ArrayList> playerRolls = new HashMap<Integer, ArrayList>();
    protected HashMap<Integer, String> output = new HashMap<Integer, String>();
    private static int rollsNumber = 0;
    private static BufferedReader reader = null;
    private static int AMOUNT_CASES = 0;

    public static void main(String[] args) {
        retrieveInput();
        calculateScores();
    }


    private static void retrieveInput() {
        File file = new File(FILENAME);
        StringBuilder contents = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(file));
            obtainCaseAmount();

            for (int i = 0; i < AMOUNT_CASES; i++) {
                rollsNumber();
                savePinsPerUser(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void obtainCaseAmount() {
        try {
            String contentLine = reader.readLine();
            if (contentLine != null) {
                AMOUNT_CASES = parseInt(contentLine);
            }
        } catch (IOException e) {
            System.out.println("Error reading number of Cases.");
        }

    }

    private static void rollsNumber() {
        try {
            String sCurrentLine = reader.readLine();
            if (sCurrentLine != null) {
                rollsNumber = parseInt(sCurrentLine);
            } else {
                System.out.println("Please insert a valid roll amount.");
            }
        } catch (IOException e) {
            System.out.println("Error reading rolls.");
        }
    }

    private static void savePinsPerUser(int index) {
        try {
            String contentLine = reader.readLine();
            if (contentLine == null) {
                System.out.println("You must enter a valid pin number.");
            } else {
                playerRolls.put(index, obtainPinArray(contentLine));
            }
        } catch (IOException e) {
            System.out.println("Error reading rolls input.");
        }
    }

    private static ArrayList<Integer> obtainPinArray(String line) {
        int pin;
        ArrayList pinList = new ArrayList();

        StringTokenizer pinToken = new StringTokenizer(line, " ");
        while (pinToken.hasMoreElements()) {
            pin = parseInt(pinToken.nextToken());
            pinList.add(pin);
        }

        return pinList;
    }

    private static void calculateScores() {
        for(int j = 0; j < AMOUNT_CASES; j++){
            calculateScorePerPlayer(j);
        }

    }

    private static void calculateScorePerPlayer(int roll) {
        int pin = 0;
    }



    private Boolean isAStrike(int score) {
        return (score == STRIKE);
    }
    private void getNextTwoPins(int pin) {
    }



    private Boolean isASpare() {
        Boolean isSpare = false;
        return isSpare;

    }


}
