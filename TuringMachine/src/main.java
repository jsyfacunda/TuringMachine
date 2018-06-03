import java.util.ArrayList;
import java.util.Arrays;

public class main {

    //001000101000100100101110101001001001110    1 delta function
    //00100010100010010010111010100100100100101010010011101010     2 delta functions
    public static String input = "001000101000100100101110101001001001110";
    public static int Q, tapeSymbols, alphabet, endOfInput, space, accept, reject;
    public static int currentState = 1;
    public static int oneCounter = 0;
    public static ArrayList<Transitions> transitionsArrayList = new ArrayList<>();

    /**
     takes the input hard coded in String "input" and puts it into an array
     */
    public static int[] inputToArray(String input) {
        int[] inputArray = new int[input.length()];
        for(int i = 0; i < input.length(); i++) {
            inputArray[i] = Character.getNumericValue(input.charAt(i));
        }
        return inputArray;
    }

    /**
     takes array with input and takes the "actual input" and puts it into its own array
     */
    public static int[] actualInputToArray(int[] inputArray) {
        int threeOnes = 0;
        int twoThreeOnes = 0;
        int size = 0;
        boolean actualInput = false;


        for(int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] == 1) {
                threeOnes++;
            } else {
                threeOnes = 0;
            }

            if (threeOnes == 3) {
                twoThreeOnes++;
                threeOnes = 0;
            }

            if (actualInput == true) {
                size++;
                if(i == inputArray.length - 1) {
                    int[] tempArray = new int[size];
                    for (int j = 0; j < size; j++) {
                        tempArray[j] = Character.getNumericValue(input.charAt(inputArray.length - size + j));
                    }
                    return tempArray;
                }
            }

            if (twoThreeOnes == 2) {
                actualInput = true;
            }
        }
        return null;
    }

    /**
     takes array with input and takes the delta functions and puts it into its own array
     */
    public static int[] toTransitionArray(int[] inputArray) {
        int threeOnes = 0;
        int twoThreeOnes = 0;
        int size = 0, size2 = 3;
        boolean actualInput = false;


        for(int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] == 1) {
                threeOnes++;
            } else {
                threeOnes = 0;
            }

            if (actualInput == true) {
                size++;
                if(i == inputArray.length - 1) {
                    int[] tempArray = new int[size - size2];
                    for (int j = 0; j < (size- size2); j++) {
                        tempArray[j] = Character.getNumericValue(input.charAt(inputArray.length - size + j));
                    }
                    return tempArray;
                }
            }

            if (threeOnes == 3) {
                twoThreeOnes++;
                threeOnes = 0;
                actualInput = true;
            }

            if (twoThreeOnes == 2) {
                size2++;
            }
        }
        return null;
    }

    /**
     gives tuple variables
     */
    public static void getTuples(int[] inputArray) {
        int counter = 0;
        for(int i = 0; i < input.length(); i++) {
            if (oneCounter == 0) {
                if (inputArray[i] == 0) {
                    counter++;
                } else {
                    Q = counter;
                    oneCounter++;
                    counter = 0;
                }
            } else if (oneCounter == 1){
                if (inputArray[i] == 0) {
                    counter++;
                } else {
                    tapeSymbols = counter;
                    oneCounter++;
                    counter = 0;
                }
            } else if (oneCounter == 2){
                if (inputArray[i] == 0) {
                    counter++;
                } else {
                    alphabet = counter;
                    oneCounter++;
                    counter = 0;
                }
            }
            else if (oneCounter == 3){
                if (inputArray[i] == 0) {
                    counter++;
                } else {
                    endOfInput = counter;
                    oneCounter++;
                    counter = 0;
                }
            } else if (oneCounter == 4){
                if (inputArray[i] == 0) {
                    counter++;
                } else {
                    space = counter;
                    oneCounter++;
                    counter = 0;
                }
            } else if (oneCounter == 5){
                if (inputArray[i] == 0) {
                    counter++;
                } else {
                    accept = counter;
                    oneCounter++;
                    counter = 0;
                }
            } else if (oneCounter == 6){
                if (inputArray[i] == 0) {
                    counter++;
                } else {
                    reject = counter;
                    oneCounter++;
                    counter = 0;
                }
            }
        }
    }

    public static void printArray(int[] arrayToPrint) {

        for(int i = 0; i < arrayToPrint.length; i++) {
            System.out.println(arrayToPrint[i]);
        }
    }

    /**
     put delta functions to an arrayList
     */
    public static void deltaToList(int[] transitionsArray) {
        int startState = 0, input = 0, nextState = 0,
                changeInputTapeTo = 0, move = 0;
        int counter = 0, ones = 0;

        for (int i = 0; i < transitionsArray.length; i++) {
            if (transitionsArray[i] == 0) {
                counter++;
            } else if (ones == 0){
                startState = counter;
                counter = 0;
                ones++;
            } else if (ones == 1){
                input = counter;
                counter = 0;
                ones++;
            } else if (ones == 2){
                nextState = counter;
                counter = 0;
                ones++;
            } else if (ones == 3){
                changeInputTapeTo = counter;
                counter = 0;
                ones++;
            } else if (ones == 4){
                move = counter;
                counter = 0;
                ones = 0;
                transitionsArrayList.add(new Transitions(startState, input, nextState, changeInputTapeTo, move));
            }

            if (i == transitionsArray.length - 1) {
                move = counter;
                transitionsArrayList.add(new Transitions(startState, input, nextState, changeInputTapeTo, move));

            }
        }
    }

    /**
     runs the input
     */
    public static boolean runInput(int[] actualInputArray) {
        int tempState = 0;

        String[] splitVersion = Arrays.toString(actualInputArray)
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .replace(" ", "")
                .split("1");

        for (int j = 0; j < splitVersion.length; j++) {
            for (int i = 0; i < transitionsArrayList.size(); i++) {
                if (currentState == transitionsArrayList.get(i).getStartState() &&
                        splitVersion[j].length() == transitionsArrayList.get(i).getInput()) {
                    tempState = transitionsArrayList.get(i).getNextState();
                }
            }
            currentState = tempState;
        }

        //System.out.println("current state: " + currentState);

        if (currentState == accept) {
            return true;
        } else {
            return false;
        }

    }


    public static void main(String[] args) {
        int[] inputArray = inputToArray(input);
        int[] transitionArray = toTransitionArray(inputArray);
        int[] actualInputArray = actualInputToArray(inputArray);
        getTuples(inputArray);
        deltaToList(transitionArray);

        System.out.println("input: " + input + "\n");
        System.out.println(runInput(actualInputArray));

    }
}
