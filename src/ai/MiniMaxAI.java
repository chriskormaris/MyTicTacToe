package ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tic_tac_toe.Board;
import tic_tac_toe.Move;
import utility.Constants;

public class MiniMaxAI extends AI {

    // Variable that holds the maximum depth the MiniMax algorithm will reach for this player.
    private int maxDepth;

    public MiniMaxAI() {
        super(Constants.O);
        maxDepth = 3;
    }

    public MiniMaxAI(int maxDepth, int playerLetter) {
        super(playerLetter);
        this.maxDepth = maxDepth;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    // Initiates the MiniMax algorithm
    @Override
    public Move getNextMove(Board board) {
        // If the X plays then it wants to maximize the heuristics value
        if (getAiPlayer() == Constants.X) {
            return max(new Board(board), 0);
        }
        // If the O plays then it wants to minimize the heuristics value
        else {
            return min(new Board(board), 0);
        }
    }

    // The max and min functions are called interchangeably, one after another until a max depth is reached
    public Move max(Board board, int depth) {
        Random r = new Random();

        /* If MAX is called on a state that is terminal or after a maximum depth is reached,
         * then a heuristic is calculated on the state and the move returned.
         */
        if ((board.isTerminal()) || (depth == maxDepth)) {
            int value = board.evaluate();
            // System.out.println("MAX function, Depth: " + depth + ", move value: " + value);
            return new Move(board.getLastMove().getRow(), board.getLastMove().getColumn(), value);
        }
        // The children-moves of the state are calculated
        List<Board> children = new ArrayList<>(board.getChildren(Constants.X));
        Move maxMove = new Move(Integer.MIN_VALUE);
        for (Board child : children) {
            // And for each child min is called, on a lower depth
            Move move = min(child, depth + 1);
            // The child-move with the greatest value is selected and returned by max
            if (move.getValue() >= maxMove.getValue()) {
                if ((move.getValue() == maxMove.getValue())) {
                    // If the heuristic has the same value then we randomly choose one of the two moves
                    if (r.nextInt(2) == 0) {
                        maxMove.setRow(child.getLastMove().getRow());
                        maxMove.setColumn(child.getLastMove().getColumn());
                        maxMove.setValue(move.getValue());
                    }
                } else {
                    maxMove.setRow(child.getLastMove().getRow());
                    maxMove.setColumn(child.getLastMove().getColumn());
                    maxMove.setValue(move.getValue());
                }
            }
        }
        return maxMove;
    }

    // Min works similarly to max
    public Move min(Board board, int depth) {
        Random r = new Random();

        if ((board.isTerminal()) || (depth == maxDepth)) {
            int value = board.evaluate();
            // System.out.println("MIN function, Depth: " + depth + ", move value: " + value);
            Move lastMove = new Move(board.getLastMove().getRow(), board.getLastMove().getColumn(), value);
            return lastMove;
        }
        List<Board> children = new ArrayList<>(board.getChildren(Constants.O));
        Move minMove = new Move(Integer.MAX_VALUE);
        for (Board child : children) {
            Move move = max(child, depth + 1);
            if (move.getValue() <= minMove.getValue()) {
                if ((move.getValue() == minMove.getValue())) {
                    if (r.nextInt(2) == 0) {
                        minMove.setRow(child.getLastMove().getRow());
                        minMove.setColumn(child.getLastMove().getColumn());
                        minMove.setValue(move.getValue());
                    }
                } else {
                    minMove.setRow(child.getLastMove().getRow());
                    minMove.setColumn(child.getLastMove().getColumn());
                    minMove.setValue(move.getValue());
                }
            }
        }
        return minMove;
    }

}
