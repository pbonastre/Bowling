import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class BowlingScore {

    private static final String FILENAME = "C:\\workSpaceIJ\\input\\inputChallenge2.txt";
    private static final int TEN = 10;

    public static HashMap<Integer, ArrayList> playerRolls = new HashMap<Integer, ArrayList>();
    protected static HashMap<Integer, ArrayList> output = new HashMap<Integer, ArrayList>();
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
        int totalScore = 0;
        int outputIndex = 0;

        ArrayList playerIndividialRoll = playerRolls.get(roll);
        ArrayList playerTotalScore = new ArrayList();

        for(int i= 0; i < playerIndividialRoll.size(); i++){
            Integer pin = (Integer) playerIndividialRoll.get(i);
            if(isAStrike(pin)){
                totalScore = totalScore + pin + addNextThree(i, playerIndividialRoll);
            }else if(isASpare(i, playerIndividialRoll)){
                totalScore = totalScore + pin + addNextThree(i, playerIndividialRoll);
                i++;
            }else {
                totalScore = totalScore + pin + (Integer) playerIndividialRoll.get(i+1);
                i++;
            }
            playerTotalScore.add(totalScore);
        }
        output.put(roll,playerTotalScore);


    }

    private static Integer addNextThree(int i, ArrayList playerIndRoll) {
        Integer strikeScore = 0;
        strikeScore = strikeScore + (Integer) playerIndRoll.get(i+1) + (Integer) playerIndRoll.get(i+2);
        return strikeScore;
    }

    private static Boolean isAStrike(int move) {
        return (move == TEN);
    }

    private static Boolean isASpare(int index, ArrayList playerIndRoll) {
        return (((Integer) playerIndRoll.get(index)+ (Integer) playerIndRoll.get(index+1)) == TEN);
    }


}
