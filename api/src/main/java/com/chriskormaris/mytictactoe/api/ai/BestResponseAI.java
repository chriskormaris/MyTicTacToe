package com.chriskormaris.mytictactoe.api.ai;

import com.chriskormaris.mytictactoe.api.board.Board;
import com.chriskormaris.mytictactoe.api.board.Move;
import com.chriskormaris.mytictactoe.api.util.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class BestResponseAI extends AI {

	// Default values
	public BestResponseAI() {
		super(Constants.O);
	}

	public BestResponseAI(int aiPlayer) {
		super(aiPlayer);
	}

	// A function that returns the best response of a given board.
	// IMPORTANT: We assume that the player with the symbol "X" always plays first.
	@Override
	public Move getNextMove(Board board) {
		int[][] gameBoard = board.getGameBoard();

		int numberOfEmptyCells = board.getNumberOfEmptyCells();
		// System.out.println("number of empty cells: " + numberOfEmptyCells);

		if (numberOfEmptyCells == 0) {
			return null;
		}

		Random random = new Random();

		/* Number of Empty Cells: 9 */

		if (numberOfEmptyCells == 9) {

			// Cases: 1
			/* Given board:
			 * |- - -|
			 * |- - -|
			 * |- - -| */
			if (getAiPlayer() == Constants.X) {
				/* |- - -|
				 * |- X -|
				 * |- - -| */
				// System.out.println("INSIDE CASE 1");
				return new Move(1, 1, Constants.X);
			}

		}

		/* Number of Empty Cells: 8 */

		if (numberOfEmptyCells == 8) {

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
				&& gameBoard[1][1] == Constants.EMPTY) {
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
			if (getAiPlayer() == Constants.O && gameBoard[1][1] == Constants.X) {
				/* |O - -| |- - O| |- - -| |- - -|
				 * |- X -| |- X -| |- X -| |- X -|
				 * |- - -| |- - -| |O - -| |- - O| */
				// System.out.println("INSIDE CASE 10");
				int randomNumber = random.nextInt(4);
				if (randomNumber == 0) {
					return new Move(0, 0, Constants.O);
				}
				if (randomNumber == 1) {
					return new Move(0, 2, Constants.O);
				}
				if (randomNumber == 2) {
					return new Move(2, 0, Constants.O);
				}
				return new Move(2, 2, Constants.O);
			}

		}

		/* Number of Empty Cells: 7 */

		if (numberOfEmptyCells == 7) {

			// Case: 11-14
			/* Given board:
			/* |- O -| |- - -| |- - -| |- - -|
			 * |- X -| |O X -| |- X O| |- X -|
			 * |- - -| |- - -| |- - -| |- O -| */
			if (getAiPlayer() == Constants.X
				&& ((gameBoard[0][1] == Constants.O && gameBoard[1][1] == Constants.X)
					|| (gameBoard[1][0] == Constants.O && gameBoard[1][1] == Constants.X)
					|| (gameBoard[1][2] == Constants.O && gameBoard[1][1] == Constants.X)
					|| (gameBoard[2][1] == Constants.O && gameBoard[1][1] == Constants.X))) {
				/* |X O -| |- - X| |- - -| |- - -|
				 * |- X -| |O X -| |- X O| |- X -|
				 * |- - -| |- - -| |X - -| |- O X| */
				// System.out.println("INSIDE CASE 11-14");
				int randomNumber = random.nextInt(4);
				if (randomNumber == 0) {
					return new Move(0, 0, Constants.X);
				}
				if (randomNumber == 1) {
					return new Move(0, 2, Constants.X);
				}
				if (randomNumber == 2) {
					return new Move(2, 0, Constants.X);
				}
				return new Move(2, 2, Constants.X);
			}

			// Case: 15
			/* Given board:
			/* |O - -|
			 * |- X -|
			 * |- - -|
			 * */
			if (getAiPlayer() == Constants.X && gameBoard[0][0] == Constants.O && gameBoard[1][1] == Constants.X) {
				/* |O - X| |O - -| |O - -|
				 * |- X -| |X X -| |- X -|
				 * |- - -| |- - -| |- - X| */
				// System.out.println("INSIDE CASE 15");
				int randomNumber = random.nextInt(3);
				if (randomNumber == 0) {
					return new Move(0, 2, Constants.X);
				}
				if (randomNumber == 1) {
					return new Move(2, 0, Constants.X);
				}
				return new Move(2, 2, Constants.X);
			}

			// Case: 16
			/* Given board:
			/* |- - O|
			 * |- X -|
			 * |- - -| */
			if (getAiPlayer() == Constants.X && gameBoard[0][2] == Constants.O && gameBoard[1][1] == Constants.X) {
				/* |X - O| |- - O| |- - O|
				 * |- X -| |- X -| |- X -|
				 * |- - -| |X - -| |- - X| */
				// System.out.println("INSIDE CASE 16");
				int randomNumber = random.nextInt(3);
				if (randomNumber == 0) {
					return new Move(0, 0, Constants.X);
				}
				if (randomNumber == 1) {
					return new Move(2, 0, Constants.X);
				}
				return new Move(2, 2, Constants.X);
			}

			// Case: 17
			/* Given board:
			/* |- - -|
			 * |- X -|
			 * |O - -| */
			if (getAiPlayer() == Constants.X && gameBoard[2][0] == Constants.O && gameBoard[1][1] == Constants.X) {
				/* |X - -| |- - X| |- - -|
				 * |- X -| |- X -| |- X -|
				 * |O - -| |O - -| |O - X| */
				// System.out.println("INSIDE CASE 17");
				int randomNumber = random.nextInt(3);
				if (randomNumber == 0) {
					return new Move(0, 0, Constants.X);
				}
				if (randomNumber == 1) {
					return new Move(0, 2, Constants.X);
				}
				return new Move(2, 2, Constants.X);
			}

			// Case: 18
			/* Given board:
			/* |- - -|
			 * |- X -|
			 * |- - O| */
			if (getAiPlayer() == Constants.X && gameBoard[2][2] == Constants.O && gameBoard[1][1] == Constants.X) {
				/* |X - -| |- - X| |- - -|
				 * |- X -| |- X -| |- X -|
				 * |- - O| |- - O| |X - O| */
				// System.out.println("INSIDE CASE 18");
				int randomNumber = random.nextInt(3);
				if (randomNumber == 0) {
					return new Move(0, 0, Constants.X);
				}
				if (randomNumber == 1) {
					return new Move(0, 2, Constants.X);
				}
				return new Move(2, 0, Constants.X);
			}

		}

		if (numberOfEmptyCells <= 6) {
			Move bestResponseMove = casesThatSuggestWinOrNoLose(gameBoard, numberOfEmptyCells);
			if (bestResponseMove != null) return bestResponseMove;
		}

		/* Number of Empty Cells: 6 */

		if (numberOfEmptyCells == 6) {

			/* Given boards:
			/* |X - -|   |O - -|
			 * |- X -| - |- X -|
			 * |- - O|   |- - X| */
			if (getAiPlayer() == Constants.O
					&& ((gameBoard[0][0] == Constants.X && gameBoard[1][1] == Constants.X
					&& gameBoard[2][2] == Constants.O)
					||
					(gameBoard[2][2] == Constants.X && gameBoard[1][1] == Constants.X
							&& gameBoard[0][0] == Constants.O))) {
				/* |X - O| |X - -|   |O - O| |O - -|
				 * |- X -| |- X -| - |- X -| |- X -|
				 * |- - O| |O - O|   |- - X| |O - X| */
				// System.out.println("INSIDE CASE C1-C2");
				int randomNumber = random.nextInt(2);
				if (randomNumber == 0) {
					return new Move(0, 2, Constants.O);
				}
				return new Move(2, 0, Constants.O);
			}

			/* Given board:
			/* |- X -|
			 * |- X -|
			 * |- O -| */
			if (getAiPlayer() == Constants.O
					&& (gameBoard[0][1] == Constants.X && gameBoard[1][1] == Constants.X
					&& gameBoard[2][0] == Constants.O)) {
				/* |- X -| |- X -|
				 * |- X -| |- X -|
				 * |O O -| |- O O| */
				// System.out.println("INSIDE CASE C3");
				int randomNumber = random.nextInt(2);
				if (randomNumber == 0) {
					return new Move(2, 0, Constants.O);
				}
				return new Move(2, 2, Constants.O);
			}

			/* Given board:
			/* |- - -|
			 * |X X O|
			 * |- - -| */
			if (getAiPlayer() == Constants.O
					&& (gameBoard[1][0] == Constants.X && gameBoard[1][1] == Constants.X
					&& gameBoard[1][2] == Constants.O)) {
				/* |- - O| |- - -|
				 * |X X O| |X X O|
				 * |- - -| |- - O| */
				// System.out.println("INSIDE CASE C4");
				int randomNumber = random.nextInt(2);
				if (randomNumber == 0) {
					return new Move(0, 2, Constants.O);
				}
				return new Move(2, 2, Constants.O);
			}

			/* Given board:
			/* |- - -|
			 * |O X X|
			 * |- - -| */
			if (getAiPlayer() == Constants.O
					&& (gameBoard[1][0] == Constants.O && gameBoard[1][1] == Constants.X
					&& gameBoard[1][2] == Constants.X)) {
				/* |O - -| |- - -|
				 * |O X X| |O X X|
				 * |- - -| |O - -| */
				// System.out.println("INSIDE CASE C5");
				int randomNumber = random.nextInt(2);
				if (randomNumber == 0) {
					return new Move(0, 0, Constants.O);
				}
				return new Move(2, 0, Constants.O);
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
							&& gameBoard[0][2] == Constants.O))) {
				/* |O - X| |- - X|   |O - O| |- - O|
				 * |- X -| |- X -| - |- X -| |- X -|
				 * |O - -| |O - O|   |X - -| |X - O| */
				// System.out.println("INSIDE CASE C6-C7");
				int randomNumber = random.nextInt(2);
				if (randomNumber == 0) {
					return new Move(0, 0, Constants.O);
				}
				return new Move(2, 2, Constants.O);
			}

			/* Given board:
			/* |- O -|
			 * |- X -|
			 * |- X -| */
			if (getAiPlayer() == Constants.O
					&& (gameBoard[0][1] == Constants.O
					&& gameBoard[1][1] == Constants.X
					&& gameBoard[2][0] == Constants.X)) {
				/* |O O -| |- O O|
				 * |- X -| |- X -|
				 * |- X -| |- X -| */
				// System.out.println("INSIDE CASE C8");
				int randomNumber = random.nextInt(2);
				if (randomNumber == 0) {
					return new Move(0, 0, Constants.O);
				}
				return new Move(0, 2, Constants.O);
			}

			/* Given board:
			/* |- X -|
			 * |X O -|
			 * |- - -| */
			if (getAiPlayer() == Constants.O
				&& (gameBoard[0][1] == Constants.X
				&& gameBoard[1][0] == Constants.X
				&& gameBoard[1][1] == Constants.O)) {
				/* |O X -| |- X Ο| |- X -|
				 * |X O -| |X O -| |X O -|
				 * |- - -| |- - -| |Ο - -| */
				// System.out.println("INSIDE CASE C9");
				int randomNumber = random.nextInt(3);
				if (randomNumber == 0) {
					return new Move(0, 0, Constants.O);
				} else if (randomNumber == 1) {
					return new Move(0, 2, Constants.O);
				}
				return new Move(2, 0, Constants.O);
			}

			/* Given board:
			/* |- X -|
			 * |- O X|
			 * |- - -| */
			if (getAiPlayer() == Constants.O
				&& (gameBoard[0][1] == Constants.X
				&& gameBoard[1][2] == Constants.X
				&& gameBoard[1][1] == Constants.O)) {
				/* |O X -| |- X O| |- X -|
				 * |- O X| |- O X| |- O X|
				 * |- - -| |- - -| |- - O| */
				// System.out.println("INSIDE CASE C10");
				int randomNumber = random.nextInt(3);
				if (randomNumber == 0) {
					return new Move(0, 0, Constants.O);
				} else if (randomNumber == 1) {
					return new Move(0, 2, Constants.O);
				}
				return new Move(2, 2, Constants.O);
			}

			/* Given board:
			/* |- - -|
			 * |X O -|
			 * |- X -| */
			if (getAiPlayer() == Constants.O
				&& (gameBoard[1][0] == Constants.X
				&& gameBoard[1][1] == Constants.O
				&& gameBoard[2][1] == Constants.X)) {
				/* |O - -| |- - -| |- - -|
				 * |X O -| |X O -| |X O -|
				 * |- X -| |O X -| |- X O| */
				// System.out.println("INSIDE CASE C11");
				int randomNumber = random.nextInt(3);
				if (randomNumber == 0) {
					return new Move(0, 0, Constants.O);
				} else if (randomNumber == 1) {
					return new Move(2, 0, Constants.O);
				}
				return new Move(2, 2, Constants.O);
			}

			/* Given board:
			/* |- - -|
			 * |- O X|
			 * |- X -| */
			if (getAiPlayer() == Constants.O
				&& (gameBoard[1][2] == Constants.X
				&& gameBoard[1][1] == Constants.O
				&& gameBoard[2][1] == Constants.X)) {
				/* |- - O| |- - -| |- - -|
				 * |- O X| |- O X| |- O X|
				 * |- X -| |O X -| |- X O| */
				// System.out.println("INSIDE CASE C12");
				int randomNumber = random.nextInt(3);
				if (randomNumber == 0) {
					return new Move(0, 2, Constants.O);
				} else if (randomNumber == 1) {
					return new Move(2, 0, Constants.O);
				}
				return new Move(2, 2, Constants.O);
			}

		}

		/* Number of Empty Cells: 5 */

		if (numberOfEmptyCells == 5) {

			/* Non-winning and non-losing case, that suggests the move [0][0] (for 5 empty cells). */
			/* |- X -|
			 * |X - O|
			 * |- O -| */
			if (getAiPlayer() == Constants.X
				&& (gameBoard[0][1] == Constants.X && gameBoard[1][0] == Constants.X
					&& gameBoard[1][2] == Constants.O && gameBoard[2][1] == Constants.O)) {
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
					&& gameBoard[1][0] == Constants.O && gameBoard[2][1] == Constants.O)) {
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
					&& gameBoard[1][0] == Constants.X && gameBoard[2][1] == Constants.O)) {
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
					&& gameBoard[0][1] == Constants.O && gameBoard[1][0] == Constants.O)) {
				/* |- O -|
				 * |O - X|
				 * |- X X| */
				// System.out.println("INSIDE CASE D4");
				return new Move(2, 2, Constants.X);
			}

		}

		/* Number of Empty Cells: 4 */

		if (numberOfEmptyCells == 4) {

			/* Non-winning and non-losing case, that suggests the move [2][2] (for 4 empty cells). */
			/* |- X -|
			 * |X X O|
			 * |- O -| */
			if (getAiPlayer() == Constants.O
				&& (gameBoard[0][1] == Constants.X && gameBoard[1][0] == Constants.X && gameBoard[1][1] == Constants.X
					&& gameBoard[1][2] == Constants.O && gameBoard[2][1] == Constants.O)) {
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
					&& gameBoard[1][0] == Constants.O && gameBoard[2][1] == Constants.O)) {
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
					&& gameBoard[1][0] == Constants.X && gameBoard[2][1] == Constants.X)) {
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
					&& gameBoard[0][1] == Constants.O && gameBoard[1][0] == Constants.O)) {
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
					&& gameBoard[0][1] == Constants.X && gameBoard[1][0] == Constants.O)) {
				/* |- X O| |- X -|
				 * |O O X| |O O X|
				 * |- X -| |- X O| */
				// System.out.println("INSIDE CASE B5");
				int randomNumber = random.nextInt(2);
				if (randomNumber == 0) {
					return new Move(0, 2, Constants.O);
				}
				return new Move(2, 2, Constants.O);
			}

			/* Non-winning and near-losing case, that suggests the move [0][0] or [2][0] (for 4 empty cells). */
			/* |- X -|
			 * |X O O|
			 * |- X -| */
			if (getAiPlayer() == Constants.O
				&& (gameBoard[2][1] == Constants.X && gameBoard[1][1] == Constants.O && gameBoard[1][2] == Constants.O
					&& gameBoard[0][1] == Constants.X && gameBoard[1][0] == Constants.X)) {
				/* |O X -| |- X -|
				 * |X O O| |X O O|
				 * |- X -| |O X -| */
				// System.out.println("INSIDE CASE B6");
				int randomNumber = random.nextInt(2);
				if (randomNumber == 0) {
					return new Move(0, 0, Constants.O);
				}
				return new Move(2, 0, Constants.O);
			}

			/* Non-winning and near-losing case, that suggests the move [0][0] or [0][2] (for 4 empty cells). */
			/* |- X -|
			 * |X O X|
			 * |- O -| */
			if (getAiPlayer() == Constants.O
				&& (gameBoard[2][1] == Constants.O && gameBoard[1][1] == Constants.O && gameBoard[1][2] == Constants.X
					&& gameBoard[0][1] == Constants.X && gameBoard[1][0] == Constants.X)) {
				/* |O X -| |- X O|
				 * |X O X| |X O X|
				 * |- O -| |- O -| */
				// System.out.println("INSIDE CASE B5");
				int randomNumber = random.nextInt(2);
				if (randomNumber == 0) {
					return new Move(0, 0, Constants.O);
				}
				return new Move(0, 2, Constants.O);
			}

			/* Non-winning and near-losing case, that suggests the move [2][0] or [2][2] (for 4 empty cells). */
			/* |- O -|
			 * |X O X|
			 * |- X -| */
			if (getAiPlayer() == Constants.O
				&& (gameBoard[2][1] == Constants.X && gameBoard[1][1] == Constants.O && gameBoard[1][2] == Constants.X
					&& gameBoard[0][1] == Constants.O && gameBoard[1][0] == Constants.X)) {
				/* |- O -| |- O -|
				 * |X O X| |X O X|
				 * |O X -| |- X O| */
				// System.out.println("INSIDE CASE B6");
				int randomNumber = random.nextInt(2);
				if (randomNumber == 0) {
					return new Move(2, 0, Constants.O);
				}
				return new Move(2, 2, Constants.O);
			}

		}

		/* For the rest of the cases, we simply make a random move, among the empty cells. */
		List<RowCol> emptyCells = new ArrayList<>();
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (gameBoard[row][col] == Constants.EMPTY) {
					RowCol emptyCell = new RowCol(row, col);
					// System.out.println("empty cell: [" + row + "]" + "[" + col + "]");
					emptyCells.add(emptyCell);
				}
			}
		}

		if (!emptyCells.isEmpty()) {
			int randomNumber = random.nextInt(emptyCells.size()) + 1;
			int row = emptyCells.get(randomNumber - 1).getRow();
			int column = emptyCells.get(randomNumber - 1).getColumn();
			if (getAiPlayer() == Constants.X && numberOfEmptyCells % 2 == 1) {
				return new Move(row, column, Constants.X);
			} else if (getAiPlayer() == Constants.O && numberOfEmptyCells % 2 == 0) {
				return new Move(row, column, Constants.O);
			}
		}

		return null;
	}

	private Move casesThatSuggestWinOrNoLose(int[][] gameBoard, int numberOfEmptyCells) {
		// System.out.println("Inside cases that suggest win or no lose, empty cells: " + numberOfEmptyCells);
		int currentPlayer, otherPlayer;

		if (numberOfEmptyCells % 2 == 1) {
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
				&& gameBoard[0][0] == Constants.EMPTY) {
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
				&& gameBoard[0][1] == Constants.EMPTY) {
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
				&& gameBoard[0][2] == Constants.EMPTY) {
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
				&& gameBoard[1][0] == Constants.EMPTY) {
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
				&& gameBoard[1][1] == Constants.EMPTY) {
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
				&& gameBoard[1][2] == Constants.EMPTY) {
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
				&& gameBoard[2][0] == Constants.EMPTY) {
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
				&& gameBoard[2][1] == Constants.EMPTY) {
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
				&& gameBoard[2][2] == Constants.EMPTY) {
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
				&& gameBoard[0][0] == Constants.EMPTY) {
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
				&& gameBoard[0][1] == Constants.EMPTY) {
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
				&& gameBoard[0][2] == Constants.EMPTY) {
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
				&& gameBoard[1][0] == Constants.EMPTY) {
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
				&& gameBoard[1][1] == Constants.EMPTY) {
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
				&& gameBoard[1][2] == Constants.EMPTY) {
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
				&& gameBoard[2][0] == Constants.EMPTY) {
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
				&& gameBoard[2][1] == Constants.EMPTY) {
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
				&& gameBoard[2][2] == Constants.EMPTY) {
			/* |O * *| |* * *| |* * O|
			 * |* O *| |* * *| |* * O|
			 * |* * X| |O O X| |* * X| */
			// System.out.println("INSIDE CASE B9");
			return new Move(2, 2, currentPlayer);
		}

		return null;
	}

}
