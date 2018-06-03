public class Transitions {
    public int startState, input, nextState, changeInputTapeTo, move;

    public Transitions(int startState, int input, int nextState,
                       int changeInputTapeTo, int move) {
        this.startState = startState;
        this.input = input;
        this.nextState = nextState;
        this.changeInputTapeTo = changeInputTapeTo;
        this.move = move;
    }

    public int getStartState() {
        return startState;
    }

    public void setStartState(int startState) {
        this.startState = startState;
    }

    public int getInput() {
        return input;
    }

    public void setInput(int input) {
        this.input = input;
    }

    public int getNextState() {
        return nextState;
    }

    public void setNextState(int nextState) {
        this.nextState = nextState;
    }

    public int getChangeInputTapeTo() {
        return changeInputTapeTo;
    }

    public void setChangeInputTapeTo(int changeInputTapeTo) {
        this.changeInputTapeTo = changeInputTapeTo;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    @Override
    public String toString() {
        return ("start state: " + startState + "\n" +
        "input: " + input + "\n" +
        "next state: " + nextState + "\n" +
        "change input to: " + changeInputTapeTo + "\n" +
        "move: " + move);
    }
}
