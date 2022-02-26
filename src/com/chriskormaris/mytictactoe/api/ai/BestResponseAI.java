package com.chriskormaris.mytictactoe.api.ai;

import com.chriskormaris.mytictactoe.api.tic_tac_toe.Board;
import com.chriskormaris.mytictactoe.api.tic_tac_toe.Move;
import com.chriskormaris.mytictactoe.api.utility.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class BestResponseAI extends AI {

    // Default values
    public BestResponseAI() {
        super(Constants.O);
        // this.bestResponse = new Move();
    }

    public BestResponseAI(int aiPlayer) {
        super(aiPlayer);
        // this.bestResponse = new Move();
    }


    // A function that returns the best response of a given board.
    // IMPORTANT: We assume that the player with the symbol "X" always plays first.
    @Override
    public Move getNextMove(Board board) {
        int[][] gameBoard = board.getGameBoard();

        int number_of_empty_cells = Board.getNumberOfEmptyCells(gameBoard);
        // System.out.println("number of empty cells: " + number_of_empty_cells);

        if (number_of_empty_cells == 0) {
            return null;
        }

        Random r = new Random();


        /* Number of Empty Cells: 9 */

        // Cases: 1
        /* Given board:
         * |- - -|
         * |- - -|
         * |- - -| */
        if (getAiPlayer() == Constants.X
                && Board.isGameBoardEmpty(gameBoard)) {
            /* |- - -|
             * |- X -|
             * |- - -| */
            // System.out.println("INSIDE CASE 1");
            return new Move(1, 1, Constants.X);
        }


        /* Number of Empty Cells: 8 */

        // Cases: 2-9
		/* Given boards:
		/* |X - -| |- X -| |- - X| |- - -| 
		 * |- - -| |- - -| |- - -| |X - -|
		 * |- - -| |- - -| |- - -| |- - -|                                
		 * 
		 * |- - -| |- - -| |- - -| |- - -|
		 * |- - X| |- - -| |- - -| |- - -|
		 * |- - -| |X - -| |- X -| |- - X| */
        if (getAiPlayer() == Constants.O
                && ((gameBoard[0][0] == Constants.X || gameBoard[0][1] == Constants.X
                || gameBoard[0][2] == Constants.X || gameBoard[1][0] == Constants.X
                || gameBoard[1][2] == Constants.X || gameBoard[2][0] == Constants.X
                || gameBoard[2][1] == Constants.X || gameBoard[2][2] == Constants.X))
                && gameBoard[1][1] == Constants.EMPTY && number_of_empty_cells == 8) {
            /* |X - -| |- X -| |- - X| |- - -|
             * |- O -| |- O -| |- O -| |X O -|
             * |- - -| |- - -| |- - -| |- - -|
             *
             * |- - -| |- - -| |- - -| |- - -|
             * |- O X| |- O -| |- O -| |- O -|
             * |- - -| |X - -| |- X -| |- - X| */
            // System.out.println("INSIDE CASE 2-9");
            return new Move(1, 1, Constants.O);
        }

        // Case: 10
		/* Given board:
		/* |- - -|
		 * |- X -|
		 * |- - -| */
        if (getAiPlayer() == Constants.O
                && gameBoard[1][1] == Constants.X && number_of_empty_cells == 8) {
            /* |O - -| |- - O| |- - -| |- - -|
             * |- X -| |- X -| |- X -| |- X -|
             * |- - -| |- - -| |O - -| |- - O| */
            // System.out.println("INSIDE CASE 10");
            int random_number = r.nextInt(4);
            if (random_number == 0) {
                return new Move(0, 0, Constants.O);
            }
            if (random_number == 1) {
                return new Move(0, 2, Constants.O);
            }
            if (random_number == 2) {
                return new Move(2, 0, Constants.O);
            }
            if (random_number == 3) {
                return new Move(2, 2, Constants.O);
            }
        }


        /* Number of Empty Cells: 7 */

        // Case: 11-14
		/* Given board:
		/* |- O -| |- - -| |- - -| |- - -|
		 * |- X -| |O X -| |- X O| |- X -|
		 * |- - -| |- - -| |- - -| |- O -| */
        if (getAiPlayer() == Constants.X
                && ((gameBoard[0][1] == Constants.O && gameBoard[1][1] == Constants.X)
                || (gameBoard[1][0] == Constants.O && gameBoard[1][1] == Constants.X)
                || (gameBoard[1][2] == Constants.O && gameBoard[1][1] == Constants.X)
                || (gameBoard[2][1] == Constants.O && gameBoard[1][1] == Constants.X))
                && number_of_empty_cells == 7) {
            /* |X O -| |- - X| |- - -| |- - -|
             * |- X -| |O X -| |- X O| |- X -|
             * |- - -| |- - -| |X - -| |- O X| */
            // System.out.println("INSIDE CASE 11-14");
            int random_number = r.nextInt(4);
            if (random_number == 0) {
                return new Move(0, 0, Constants.X);
            }
            if (random_number == 1) {
                return new Move(0, 2, Constants.X);
            }
            if (random_number == 2) {
                return new Move(2, 0, Constants.X);
            }
            if (random_number == 3) {
                return new Move(2, 2, Constants.X);
            }
        }

        // Case: 15
		/* Given board:
		/* |O - -|
		 * |- X -|
		 * |- - -|
		 * */
        if (getAiPlayer() == Constants.X
                && (gameBoard[0][0] == Constants.O && gameBoard[1][1] == Constants.X)
                && number_of_empty_cells == 7) {
            /* |O - X| |O - -| |O - -|
             * |- X -| |X X -| |- X -|
             * |- - -| |- - -| |- - X| */
            // System.out.println("INSIDE CASE 15");
            int random_number = r.nextInt(3);
            if (random_number == 0) {
                return new Move(0, 2, Constants.X);
            }
            if (random_number == 1) {
                return new Move(2, 0, Constants.X);
            }
            if (random_number == 2) {
                return new Move(2, 2, Constants.X);
            }
        }

        // Case: 16
		/* Given board:
		/* |- - O|
		 * |- X -|
		 * |- - -| */
        if (getAiPlayer() == Constants.X
                && (gameBoard[0][2] == Constants.O && gameBoard[1][1] == Constants.X)
                && number_of_empty_cells == 7) {
            /* |X - O| |- - O| |- - O|
             * |- X -| |- X -| |- X -|
             * |- - -| |X - -| |- - X| */
            // System.out.println("INSIDE CASE 16");
            int random_number = r.nextInt(3);
            if (random_number == 0) {
                return new Move(0, 0, Constants.X);
            }
            if (random_number == 1) {
                return new Move(2, 0, Constants.X);
            }
            if (random_number == 2) {
                return new Move(2, 2, Constants.X);
            }
        }

        // Case: 17
		/* Given board:
		/* |- - -|
		 * |- X -|
		 * |O - -| */
        if (getAiPlayer() == Constants.X
                && (gameBoard[2][0] == Constants.O && gameBoard[1][1] == Constants.X)
                && number_of_empty_cells == 7) {
            /* |X - -| |- - X| |- - -|
             * |- X -| |- X -| |- X -|
             * |O - -| |O - -| |O - X| */
            // System.out.println("INSIDE CASE 17");
            int random_number = r.nextInt(3);
            if (random_number == 0) {
                return new Move(0, 0, Constants.X);
            }
            if (random_number == 1) {
                return new Move(0, 2, Constants.X);
            }
            if (random_number == 2) {
                return new Move(2, 2, Constants.X);
            }
        }

        // Case: 18
		/* Given board:
		/* |- - -|
		 * |- X -|
		 * |- - O| */
        if (getAiPlayer() == Constants.X
                && (gameBoard[2][2] == Constants.O && gameBoard[1][1] == Constants.X)
                && number_of_empty_cells == 7) {
            /* |X - -| |- - X| |- - -|
             * |- X -| |- X -| |- X -|
             * |- - O| |- - O| |X - O| */
            // System.out.println("INSIDE CASE 18");
            int random_number = r.nextInt(3);
            if (random_number == 0) {
                return new Move(0, 0, Constants.X);
            }
            if (random_number == 1) {
                return new Move(0, 2, Constants.X);
            }
            if (random_number == 2) {
                return new Move(2, 0, Constants.X);
            }
        }


        /* Number of Empty Cells: 6 */

        Move bestResponseMove = casesThatSuggestWinOrNoLose(gameBoard, number_of_empty_cells, 6);
        if (bestResponseMove != null) return bestResponseMove;

		/* Given boards:
		/* |X - -|   |O - -|
		 * |- X -| - |- X -|
		 * |- - O|   |- - X| */
        if (getAiPlayer() == Constants.O
                && ((gameBoard[0][0] == Constants.X && gameBoard[1][1] == Constants.X
                && gameBoard[2][2] == Constants.O)
                ||
                (gameBoard[2][2] == Constants.X && gameBoard[1][1] == Constants.X
                        && gameBoard[0][0] == Constants.O))
                && number_of_empty_cells == 6) {
            /* |X - O| |X - -|   |O - O| |O - -|
             * |- X -| |- X -| - |- X -| |- X -|
             * |- - O| |O - O|   |- - X| |O - X| */
            // System.out.println("INSIDE CASE C1-C2");
            int random_number = r.nextInt(2);
            if (random_number == 0) {
                return new Move(0, 2, Constants.O);
            }
            if (random_number == 1) {
                return new Move(2, 0, Constants.O);
            }
        }
		
		/* Given board:
		/* |- X -|
		 * |- X -|
		 * |- O -| */
        if (getAiPlayer() == Constants.O
                && (gameBoard[0][1] == Constants.X && gameBoard[1][1] == Constants.X
                && gameBoard[2][0] == Constants.O)
                && number_of_empty_cells == 6) {
            /* |- X -| |- X -|
             * |- X -| |- X -|
             * |O O -| |- O O| */
            // System.out.println("INSIDE CASE C3");
            int random_number = r.nextInt(2);
            if (random_number == 0) {
                return new Move(2, 0, Constants.O);
            }
            if (random_number == 1) {
                return new Move(2, 2, Constants.O);
            }
        }
		
		/* Given board:
		/* |- - -|
		 * |X X O|
		 * |- - -| */
        if (getAiPlayer() == Constants.O
                && (gameBoard[1][0] == Constants.X && gameBoard[1][1] == Constants.X
                && gameBoard[1][2] == Constants.O)
                && number_of_empty_cells == 6) {
            /* |- - O| |- - -|
             * |X X O| |X X O|
             * |- - -| |- - O| */
            // System.out.println("INSIDE CASE C4");
            int random_number = r.nextInt(2);
            if (random_number == 0) {
                return new Move(0, 2, Constants.O);
            }
            if (random_number == 1) {
                return new Move(2, 2, Constants.O);
            }
        }
		

		/* Given board:
		/* |- - -|
		 * |O X X|
		 * |- - -| */
        if (getAiPlayer() == Constants.O
                && (gameBoard[1][0] == Constants.X && gameBoard[1][1] == Constants.X
                && gameBoard[1][2] == Constants.O)
                && number_of_empty_cells == 6) {
            /* |O - -| |- - -|
             * |O X X| |O X X|
             * |- - -| |O - -| */
            // System.out.println("INSIDE CASE C5");
            int random_number = r.nextInt(2);
            if (random_number == 0) {
                return new Move(0, 0, Constants.O);
            }
            if (random_number == 1) {
                return new Move(2, 0, Constants.O);
            }
        }
		
		/* Given boards:
		/* |- - X|   |- - O|
		 * |- X -| - |- X -|
		 * |O - -|   |X - -| */
        if (getAiPlayer() == Constants.O
                && (gameBoard[0][2] == Constants.X && gameBoard[1][1] == Constants.X
                && gameBoard[2][0] == Constants.O
                ||
                (gameBoard[2][0] == Constants.X && gameBoard[1][1] == Constants.X
                        && gameBoard[0][2] == Constants.O))
                && number_of_empty_cells == 6) {
            /* |O - X| |- - X|   |O - O| |- - O|
             * |- X -| |- X -| - |- X -| |- X -|
             * |O - -| |O - O|   |X - -| |X - O| */
            // System.out.println("INSIDE CASE C6-C7");
            int random_number = r.nextInt(2);
            if (random_number == 0) {
                return new Move(0, 0, Constants.O);
            }
            if (random_number == 1) {
                return new Move(2, 2, Constants.O);
            }
        }
		
		/* Given board:
		/* |- O -|
		 * |- X -|
		 * |- X -| */
        if (getAiPlayer() == Constants.O
                && (gameBoard[0][1] == Constants.X
                && gameBoard[1][1] == Constants.X
                && gameBoard[2][0] == Constants.O)
                && number_of_empty_cells == 6) {
            /* |O O -| |- O O|
             * |- X -| |- X -|
             * |- X -| |- X -| */
            // System.out.println("INSIDE CASE C8");
            int random_number = r.nextInt(2);
            if (random_number == 0) {
                return new Move(0, 0, Constants.O);
            }
            if (random_number == 1) {
                return new Move(0, 2, Constants.O);
            }
        }


        /* Number of Empty Cells: 5 */

        bestResponseMove = casesThatSuggestWinOrNoLose(gameBoard, number_of_empty_cells, 5);
        if (bestResponseMove != null) return bestResponseMove;

        /* Non-winning and non-losing case, that suggests the move [0][0] (for 5 empty cells). */
        /* |- X -|
         * |X - O|
         * |- O -| */
        if (getAiPlayer() == Constants.X
                && (gameBoard[0][1] == Constants.X && gameBoard[1][0] == Constants.X
                && gameBoard[1][2] == Constants.O && gameBoard[2][1] == Constants.O)
                && number_of_empty_cells == 5) {
            /* |X X -|
             * |X - O|
             * |- O -| */
            // System.out.println("INSIDE CASE D1");
            return new Move(0, 0, Constants.X);
        }

        /* Non-winning and non-losing case, that suggests the move [0][2] (for 5 empty cells). */
        /* |- X -|
         * |O - X|
         * |- O -| */
        if (getAiPlayer() == Constants.X
                && (gameBoard[0][1] == Constants.X && gameBoard[1][2] == Constants.X
                && gameBoard[1][0] == Constants.O && gameBoard[2][1] == Constants.O)
                && number_of_empty_cells == 5) {
            /* |- X X|
             * |O - X|
             * |- O -| */
            // System.out.println("INSIDE CASE D2");
            return new Move(0, 2, Constants.X);
        }

        /* Non-winning and non-losing case, that suggests the move [2][0] (for 5 empty cells). */
        /* |- O -|
         * |X - O|
         * |- X -| */
        if (getAiPlayer() == Constants.X
                && (gameBoard[0][1] == Constants.X && gameBoard[1][2] == Constants.X
                && gameBoard[1][0] == Constants.X && gameBoard[2][1] == Constants.O)
                && number_of_empty_cells == 5) {
            /* |- O -|
             * |X - O|
             * |X X -| */
            // System.out.println("INSIDE CASE D3");
            return new Move(2, 0, Constants.X);
        }

        /* Non-winning and non-losing case, that suggests the move [2][2] (for 5 empty cells). */
        /* |- O -|
         * |O - X|
         * |- X -| */
        if (getAiPlayer() == Constants.X
                && (gameBoard[0][2] == Constants.X && gameBoard[2][1] == Constants.X
                && gameBoard[0][1] == Constants.O && gameBoard[1][0] == Constants.O)
                && number_of_empty_cells == 5) {
            /* |- O -|
             * |O - X|
             * |- X X| */
            // System.out.println("INSIDE CASE D4");
            return new Move(2, 2, Constants.X);
        }


        /* Number of Empty Cells: 4 */
        bestResponseMove = casesThatSuggestWinOrNoLose(gameBoard, number_of_empty_cells, 4);
        if (bestResponseMove != null) return bestResponseMove;

        /* Non-winning and non-losing case, that suggests the move [2][2] (for 4 empty cells). */
        /* |- X -|
         * |X X O|
         * |- O -| */
        if (getAiPlayer() == Constants.O
                && (gameBoard[0][1] == Constants.X && gameBoard[1][0] == Constants.X && gameBoard[1][1] == Constants.X
                && gameBoard[1][2] == Constants.O && gameBoard[2][1] == Constants.O)
                && number_of_empty_cells == 4) {
            /* |- X -|
             * |X X O|
             * |- O O| */
            // System.out.println("INSIDE CASE B1");
            return new Move(2, 2, Constants.O);
        }

        /* Non-winning and non-losing case, that suggests the move [2][0] (for 4 empty cells). */
        /* |- X -|
         * |O X X|
         * |- O -| */
        if (getAiPlayer() == Constants.O
                && (gameBoard[0][1] == Constants.X && gameBoard[1][2] == Constants.X && gameBoard[1][1] == Constants.X
                && gameBoard[1][0] == Constants.O && gameBoard[2][1] == Constants.O)
                && number_of_empty_cells == 4) {
            /* |- X -|
             * |O X X|
             * |O O -| */
            // System.out.println("INSIDE CASE B2");
            return new Move(2, 0, Constants.O);
        }

        /* Non-winning and non-losing case, that suggests the move [0][2] (for 4 empty cells). */
        /* |- O -|
         * |X X O|
         * |- X -| */
        if (getAiPlayer() == Constants.O
                && (gameBoard[0][1] == Constants.O && gameBoard[1][2] == Constants.O && gameBoard[1][1] == Constants.X
                && gameBoard[1][0] == Constants.X && gameBoard[2][1] == Constants.X)
                && number_of_empty_cells == 4) {
            /* |- O O|
             * |X X O|
             * |- X -| */
            // System.out.println("INSIDE CASE B3");
            return new Move(0, 2, Constants.O);
        }

        /* Non-winning and non-losing case, that suggests the move [0][0] (for 4 empty cells). */
        /* |- O -|
         * |O X X|
         * |- X -| */
        if (getAiPlayer() == Constants.O
                && (gameBoard[2][1] == Constants.X && gameBoard[1][1] == Constants.X && gameBoard[1][2] == Constants.X
                && gameBoard[0][1] == Constants.O && gameBoard[1][0] == Constants.O)
                && number_of_empty_cells == 4) {
            /* |O O -|
             * |O X X|
             * |- X -| */
            // System.out.println("INSIDE CASE B4");
            return new Move(0, 0, Constants.O);
        }

        /* Non-winning and near-losing case, that suggests the move [0][2] or [2][2] (for 4 empty cells). */
        /* |- X -|
         * |O O X|
         * |- X -| */
        if (getAiPlayer() == Constants.O
                && (gameBoard[2][1] == Constants.X && gameBoard[1][1] == Constants.O && gameBoard[1][2] == Constants.X
                && gameBoard[0][1] == Constants.X && gameBoard[1][0] == Constants.O)
                && number_of_empty_cells == 4) {
            /* |- X O| |- X -|
             * |O O X| |O O X|
             * |- X -| |- X O| */
            // System.out.println("INSIDE CASE B5");
            int random_number = r.nextInt(2);
            if (random_number == 0) {
                return new Move(0, 2, Constants.O);
            }
            if (random_number == 1) {
                return new Move(2, 2, Constants.O);
            }
        }

        /* Non-winning and near-losing case, that suggests the move [0][0] or [2][0] (for 4 empty cells). */
        /* |- X -|
         * |X O O|
         * |- X -| */
        if (getAiPlayer() == Constants.O
                && (gameBoard[2][1] == Constants.X && gameBoard[1][1] == Constants.O && gameBoard[1][2] == Constants.O
                && gameBoard[0][1] == Constants.X && gameBoard[1][0] == Constants.X)
                && number_of_empty_cells == 4) {
            /* |O X -| |- X -|
             * |X O O| |X O O|
             * |- X -| |O X -| */
            // System.out.println("INSIDE CASE B6");
            int random_number = r.nextInt(2);
            if (random_number == 0) {
                return new Move(0, 0, Constants.O);
            }
            if (random_number == 1) {
                return new Move(2, 0, Constants.O);
            }
        }

        /* Non-winning and near-losing case, that suggests the move [0][0] or [0][2] (for 4 empty cells). */
        /* |- X -|
         * |X O X|
         * |- O -| */
        if (getAiPlayer() == Constants.O
                && (gameBoard[2][1] == Constants.O && gameBoard[1][1] == Constants.O && gameBoard[1][2] == Constants.X
                && gameBoard[0][1] == Constants.X && gameBoard[1][0] == Constants.X)
                && number_of_empty_cells == 4) {
            /* |O X -| |- X O|
             * |X O X| |X O X|
             * |- O -| |- O -| */
            // System.out.println("INSIDE CASE B5");
            int random_number = r.nextInt(2);
            if (random_number == 0) {
                return new Move(0, 0, Constants.O);
            }
            if (random_number == 1) {
                return new Move(0, 2, Constants.O);
            }
        }

        /* Non-winning and near-losing case, that suggests the move [2][0] or [2][2] (for 4 empty cells). */
        /* |- O -|
         * |X O X|
         * |- X -| */
        if (getAiPlayer() == Constants.O
                && (gameBoard[2][1] == Constants.X && gameBoard[1][1] == Constants.O && gameBoard[1][2] == Constants.X
                && gameBoard[0][1] == Constants.O && gameBoard[1][0] == Constants.X)
                && number_of_empty_cells == 4) {
            /* |- O -| |- O -|
             * |X O X| |X O X|
             * |O X -| |- X O| */
            // System.out.println("INSIDE CASE B6");
            int random_number = r.nextInt(2);
            if (random_number == 0) {
                return new Move(2, 0, Constants.O);
            }
            if (random_number == 1) {
                return new Move(2, 2, Constants.O);
            }
        }


        /* Number of Empty Cells: 3 */
        bestResponseMove = casesThatSuggestWinOrNoLose(gameBoard, number_of_empty_cells, 3);
        if (bestResponseMove != null) return bestResponseMove;


        /* Number of Empty Cells: 2 */
        bestResponseMove = casesThatSuggestWinOrNoLose(gameBoard, number_of_empty_cells, 2);
        if (bestResponseMove != null) return bestResponseMove;


        /* Number of Empty Cells: 1 */
        bestResponseMove = casesThatSuggestWinOrNoLose(gameBoard, number_of_empty_cells, 1);
        if (bestResponseMove != null) return bestResponseMove;


        /* For the rest of the cases, we simply make a random move, among the empty cells. */
        List<List<Integer>> emptyCells = new ArrayList<>();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (gameBoard[row][col] == Constants.EMPTY) {
                    List<Integer> emptyCell = new ArrayList<>();
                    emptyCell.add(row);
                    emptyCell.add(col);
                    // System.out.println("empty cell: [" + row + "]" + "[" + col + "]");
                    emptyCells.add(emptyCell);
                }
            }
        }


        if (emptyCells.size() > 0) {
            int random_number = r.nextInt(emptyCells.size()) + 1;
            if (getAiPlayer() == Constants.X
                    && number_of_empty_cells % 2 == 1) {
                return new Move(emptyCells.get(random_number - 1).get(0),
                        emptyCells.get(random_number - 1).get(1),
                        Constants.X);
            } else if (getAiPlayer() == Constants.O
                    && number_of_empty_cells % 2 == 0) {
                return new Move(emptyCells.get(random_number - 1).get(0),
                        emptyCells.get(random_number - 1).get(1),
                        Constants.O);
            }
        }

        return null;
    }


    private Move casesThatSuggestWinOrNoLose(int[][] gameBoard, int number_of_empty_cells, int given_number_of_empty_cells) {
        // System.out.println("Inside cases that suggest win or lose, empty cells: " + number_of_empty_cells);
        int currentPlayer, otherPlayer;

        if (number_of_empty_cells % 2 == 1) {
            currentPlayer = Constants.X;
            otherPlayer = Constants.O;
        } else {
            currentPlayer = Constants.O;
            otherPlayer = Constants.X;
        }

        if (currentPlayer != getAiPlayer()) return null;


        /* Cases that suggest a win condition! */

        // Cases that suggest the move [0][0].
        /* |- * *| |- X X| |- * *|
         * |* X *| |* * *| |X * *|
         * |* * X| |* * *| |X * *| */
        if (((gameBoard[1][1] == currentPlayer && gameBoard[2][2] == currentPlayer)
                || (gameBoard[0][1] == currentPlayer && gameBoard[0][2] == currentPlayer)
                || (gameBoard[1][0] == currentPlayer && gameBoard[2][0] == currentPlayer))
                && gameBoard[0][0] == Constants.EMPTY
                && number_of_empty_cells == given_number_of_empty_cells) {
            /* |X * *| |X X X| |X * *|
             * |* X *| |* * *| |X * *|
             * |* * X| |* * *| |X * *| */
            // System.out.println("INSIDE CASE A1");
            return new Move(0, 0, currentPlayer);
        }

        // Cases that suggest the move [0][1].
        /* |X - X| |* - *|
         * |* * *| |* X *|
         * |* * *| |* X *|  */
        if (((gameBoard[0][0] == currentPlayer && gameBoard[0][2] == currentPlayer)
                || (gameBoard[1][1] == currentPlayer && gameBoard[2][1] == currentPlayer))
                && gameBoard[0][1] == Constants.EMPTY
                && number_of_empty_cells == given_number_of_empty_cells) {
            /* |X X X| |* X *|
             * |* * *| |* X *|
             * |* * *| |* X *| */
            // System.out.println("INSIDE CASE A2");
            return new Move(0, 1, currentPlayer);
        }

        // Cases that suggest the move [0][2].
        /* |* * -| |X X -| |- * -|
         * |* X *| |* * *| |* * X|
         * |X * *| |* * *| |* * X| */
        if (((gameBoard[1][1] == currentPlayer && gameBoard[2][0] == currentPlayer)
                || (gameBoard[0][0] == currentPlayer && gameBoard[0][1] == currentPlayer)
                || (gameBoard[1][2] == currentPlayer && gameBoard[2][2] == currentPlayer))
                && gameBoard[0][2] == Constants.EMPTY
                && number_of_empty_cells == given_number_of_empty_cells) {
            /* |* * X| |X X X| |- * X|
             * |* X *| |* * *| |* * X|
             * |X * *| |* * *| |* * X| */
            // System.out.println("INSIDE CASE A3");
            return new Move(0, 2, currentPlayer);
        }

        // Cases that suggest the move [1][0].
        /* |X * *| |* * *|
         * |- * *| |- X X|
         * |X * *| |* * *| */
        if (((gameBoard[0][0] == currentPlayer && gameBoard[2][0] == currentPlayer)
                || (gameBoard[1][1] == currentPlayer && gameBoard[1][2] == currentPlayer))
                && gameBoard[1][0] == Constants.EMPTY
                && number_of_empty_cells == given_number_of_empty_cells) {
            /* |X * *| |* * *|
             * |X * *| |X X X|
             * |X * *| |* * *| */
            // System.out.println("INSIDE CASE A4");
            return new Move(1, 0, currentPlayer);
        }

        // Cases that suggest the move [1][1].
        /* |X * *| |* X *| |* * X| |* * *|
         * |* - *| |* - *| |* - *| |X - X|
         * |* * X| |* X *| |X * *| |* * *| */
        if (((gameBoard[0][0] == currentPlayer && gameBoard[2][2] == currentPlayer)
                || (gameBoard[0][1] == currentPlayer && gameBoard[2][1] == currentPlayer)
                || (gameBoard[0][2] == currentPlayer && gameBoard[2][0] == currentPlayer)
                || (gameBoard[1][0] == currentPlayer && gameBoard[1][2] == currentPlayer))
                && gameBoard[1][1] == Constants.EMPTY
                && number_of_empty_cells == given_number_of_empty_cells) {
            /* |X * *| |* X *| |* * X| |* * *|
             * |* X *| |* X *| |* X *| |X X X|
             * |* * X| |* X *| |X * *| |* * *| */
            // System.out.println("INSIDE CASE A5");
            return new Move(1, 1, currentPlayer);
        }

        // Cases that suggest the move [1][2].
        /* |* * X| |* * *|
         * |* * -| |X X -| Cases that suggest a win condition
         * |* * X| |* * *| and win condition! */
        if (((gameBoard[0][2] == currentPlayer && gameBoard[2][2] == currentPlayer)
                || (gameBoard[1][0] == currentPlayer && gameBoard[1][1] == currentPlayer))
                && gameBoard[1][2] == Constants.EMPTY
                && number_of_empty_cells == given_number_of_empty_cells) {
            /* |* * X| |* * *|
             * |* * X| |X X X|
             * |* * X| |* * *| */
            // System.out.println("INSIDE CASE A6");
            return new Move(1, 2, currentPlayer);
        }

        // Cases that suggest the move [2][0].
        /* |* * X| |* * *| |X * *|
         * |* X *| |* * *| |X * *|
         * |- * *| |- X X| |- * *| and win condition! */
        if (((gameBoard[0][2] == currentPlayer && gameBoard[1][1] == currentPlayer)
                || (gameBoard[2][1] == currentPlayer && gameBoard[2][2] == currentPlayer)
                || (gameBoard[0][0] == currentPlayer && gameBoard[1][0] == currentPlayer))
                && gameBoard[2][0] == Constants.EMPTY
                && number_of_empty_cells == given_number_of_empty_cells) {
            /* |* * X| |* * *| |X * *|
             * |* X *| |* * *| |X * *|
             * |X * *| |X X X| |X * *| */
            // System.out.println("INSIDE CASE A7");
            return new Move(2, 0, currentPlayer);
        }

        // Cases that suggest the move [2][1].
        /* |* * *| |* X *|
         * |* * *| |* X *|
         * |X - X| |* - *| */
        if (((gameBoard[2][0] == currentPlayer && gameBoard[2][2] == currentPlayer)
                || (gameBoard[0][1] == currentPlayer && gameBoard[1][1] == currentPlayer))
                && gameBoard[2][1] == Constants.EMPTY
                && number_of_empty_cells == given_number_of_empty_cells) {
            /* |* * *| |* X *|
             * |* * *| |* X *|
             * |X X X| |* X *| */
            // System.out.println("INSIDE CASE A8");
            return new Move(2, 1, currentPlayer);
        }

        // Cases that suggest the move [2][2].
        /* |X * *| |* * *| |* * X|
         * |* X *| |* * *| |* * X|
         * |* * -| |X X -| |* * -| */
        if (((gameBoard[0][0] == currentPlayer && gameBoard[1][1] == currentPlayer)
                || (gameBoard[2][0] == currentPlayer && gameBoard[2][1] == currentPlayer)
                || (gameBoard[0][2] == currentPlayer && gameBoard[1][2] == currentPlayer))
                && gameBoard[2][2] == Constants.EMPTY
                && number_of_empty_cells == given_number_of_empty_cells) {
            /* |X * *| |* * *| |* * X|
             * |* X *| |* * *| |* * X|
             * |* * X| |X X X| |* * X| */
            // System.out.println("INSIDE CASE A9");
            return new Move(2, 2, currentPlayer);
        }


        /* Cases that suggest a no-lose condition! */

        // Cases that suggest the move [0][0].
        /* |- * *| |- O O| |- * *|
         * |* O *| |* * *| |O * *|
         * |* * O| |* * *| |O * *| */
        if (((gameBoard[1][1] == otherPlayer && gameBoard[2][2] == otherPlayer)
                || (gameBoard[0][1] == otherPlayer && gameBoard[0][2] == otherPlayer)
                || (gameBoard[1][0] == otherPlayer && gameBoard[2][0] == otherPlayer))
                && gameBoard[0][0] == Constants.EMPTY
                && number_of_empty_cells == given_number_of_empty_cells) {
            /* |> * *| |> O O| |> * *|
             * |* O *| |* * *| |O * *|
             * |* * O| |* * *| |O * *| */
            // System.out.println("INSIDE CASE B1");
            return new Move(0, 0, currentPlayer);
        }

        // Cases that suggest the move [0][1].
        /* |O - O| |* - *|
         * |* * *| |* O *|
         * |* * *| |* O *| */
        if (((gameBoard[0][0] == otherPlayer && gameBoard[0][2] == otherPlayer)
                || (gameBoard[1][1] == otherPlayer && gameBoard[2][1] == otherPlayer))
                && gameBoard[0][1] == Constants.EMPTY
                && number_of_empty_cells == given_number_of_empty_cells) {
            /* |O X O| |* X *|
             * |* * *| |* O *|
             * |* * *| |* O *| */
            // System.out.println("INSIDE CASE B2");
            return new Move(0, 1, currentPlayer);
        }

        // Cases that suggest the move [0][2].
        /* |* * -| |O O -| |- * -|
         * |* O *| |* * *| |* * O|
         * |O * *| |* * *| |* * O| */
        if (((gameBoard[1][1] == otherPlayer && gameBoard[2][0] == otherPlayer)
                || (gameBoard[0][0] == otherPlayer && gameBoard[0][1] == otherPlayer)
                || (gameBoard[1][2] == otherPlayer && gameBoard[2][2] == otherPlayer))
                && gameBoard[0][2] == Constants.EMPTY
                && number_of_empty_cells == given_number_of_empty_cells) {
            /* |* * X| |O O X| |- * X|
             * |* O *| |* * *| |* * O|
             * |O * *| |* * *| |* * O| */
            // System.out.println("INSIDE CASE B3");
            return new Move(0, 2, currentPlayer);
        }

        // Cases that suggest the move [1][0].
        /* |O * *| |* * *|
         * |- * *| |- O O|
         * |O * *| |* * *| */
        if (((gameBoard[0][0] == otherPlayer && gameBoard[2][0] == otherPlayer)
                || (gameBoard[1][1] == otherPlayer && gameBoard[1][2] == otherPlayer))
                && gameBoard[1][0] == Constants.EMPTY
                && number_of_empty_cells == given_number_of_empty_cells) {
            /* |O * *| |* * *|
             * |X * *| |X O O|
             * |O * *| |* * *| */
            // System.out.println("INSIDE CASE B4");
            return new Move(1, 0, currentPlayer);
        }

        // Cases that suggest the move [1][1].
        /* |O * *| |* O *| |* * O| |* * *|
         * |* - *| |* - *| |* - *| |O - O|
         * |* * O| |* O *| |O * *| |* * *| */
        if (((gameBoard[0][0] == otherPlayer && gameBoard[2][2] == otherPlayer)
                || (gameBoard[0][1] == otherPlayer && gameBoard[2][1] == otherPlayer)
                || (gameBoard[0][2] == otherPlayer && gameBoard[2][0] == otherPlayer)
                || (gameBoard[1][0] == otherPlayer && gameBoard[1][2] == otherPlayer))
                && gameBoard[1][1] == Constants.EMPTY
                && number_of_empty_cells == given_number_of_empty_cells) {
            /* |O * *| |* O *| |* * O| |* * *|
             * |* X *| |* X *| |* X *| |O X O|
             * |* * O| |* O *| |O * *| |* * *| */
            // System.out.println("INSIDE CASE B5");
            return new Move(1, 1, currentPlayer);
        }

        // Cases that suggest the move [1][2].
        /* |* * O| |* * *|
         * |* * -| |O O -|
         * |* * O| |* * *| */
        if (((gameBoard[0][2] == otherPlayer && gameBoard[2][2] == otherPlayer)
                || (gameBoard[1][0] == otherPlayer && gameBoard[1][1] == otherPlayer))
                && gameBoard[1][2] == Constants.EMPTY
                && number_of_empty_cells == given_number_of_empty_cells) {
            /* |* * O| |* * *|
             * |* * X| |O O X|
             * |* * O| |* * *| */
            // System.out.println("INSIDE CASE B6");
            return new Move(1, 2, currentPlayer);
        }

        // Cases that suggest the move [2][0].
        /* |* * O| |* * *| |O * *|
         * |* O *| |* * *| |O * *|
         * |- * *| |- O O| |- * *| */
        if (((gameBoard[0][2] == otherPlayer && gameBoard[1][1] == otherPlayer)
                || (gameBoard[2][1] == otherPlayer && gameBoard[2][2] == otherPlayer)
                || (gameBoard[0][0] == otherPlayer && gameBoard[1][0] == otherPlayer))
                && gameBoard[2][0] == Constants.EMPTY
                && number_of_empty_cells == given_number_of_empty_cells) {
            /* |* * O| |* * *| |O * *|
             * |* O *| |* * *| |O * *|
             * |X * *| |X O O| |X * *| */
            // System.out.println("INSIDE CASE B7");
            return new Move(2, 0, currentPlayer);
        }

        // Cases that suggest the move [2][1].
        /* |* * *| |* O *|
         * |* * *| |* O *|
         * |O - O| |* - *| */
        if (((gameBoard[2][0] == otherPlayer && gameBoard[2][2] == otherPlayer)
                || (gameBoard[0][1] == otherPlayer && gameBoard[1][1] == otherPlayer))
                && gameBoard[2][1] == Constants.EMPTY
                && number_of_empty_cells == given_number_of_empty_cells) {
            /* |* * *| |* O *|
             * |* * *| |* O *|
             * |O X O| |* X *| */
            // System.out.println("INSIDE CASE B8");
            return new Move(2, 1, currentPlayer);
        }

        // Cases that suggest the move [2][2].
        /* |O * *| |* * *| |* * O|
         * |* O *| |* * *| |* * O|
         * |* * -| |O O -| |* * -| */
        if (((gameBoard[0][0] == otherPlayer && gameBoard[1][1] == otherPlayer)
                || (gameBoard[2][0] == otherPlayer && gameBoard[2][1] == otherPlayer)
                || (gameBoard[0][2] == otherPlayer && gameBoard[1][2] == otherPlayer))
                && gameBoard[2][2] == Constants.EMPTY
                && number_of_empty_cells == given_number_of_empty_cells) {
            /* |O * *| |* * *| |* * O|
             * |* O *| |* * *| |* * O|
             * |* * X| |O O X| |* * X| */
            // System.out.println("INSIDE CASE B9");
            return new Move(2, 2, currentPlayer);
        }

        return null;
    }

}
