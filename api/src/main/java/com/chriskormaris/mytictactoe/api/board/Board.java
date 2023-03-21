package com.chriskormaris.mytictactoe.api.board;

import com.chriskormaris.mytictactoe.api.util.Constants;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Board {


	private final int[][] gameBoard;

	// Immediate move that led to this board.
	private Move lastMove;

	/* Variable containing who played last; whose turn resulted in this board.
	 * Even a new Board has a lastPlayer value; it the opponent of the player who will play first. */
	private int lastPlayer;

	private int winner;

	public Board() {
		this.lastMove = new Move();
		this.lastPlayer = Constants.O;
		this.gameBoard = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.gameBoard[i][j] = Constants.EMPTY;
			}
		}
		this.winner = Constants.EMPTY;
	}

	public Board(Board board) {
		this.lastMove = new Move(board.lastMove);
		this.lastPlayer = board.lastPlayer;
		this.gameBoard = new int[3][3];
		for (int i = 0; i < 3; i++) {
			System.arraycopy(board.gameBoard[i], 0, this.gameBoard[i], 0, 3);
		}
		this.winner = Constants.EMPTY;
	}

	// Checking if the board is empty.
	public static boolean isGameBoardEmpty(int[][] gameBoard) {
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				if (gameBoard[row][column] != Constants.EMPTY) {
					return false;
				}
			}
		}
		return true;
	}

	// Checking if the board is full,
	// by checking if there is at least one empty tile.
	public static boolean isGameBoardFull(int[][] gameBoard) {
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				if (gameBoard[row][column] == Constants.EMPTY) {
					return false;
				}
			}
		}
		return true;
	}

	public static int getNumberOfEmptyCells(int[][] gameBoard) {
		int number_of_empty_cells = 0;
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				if (gameBoard[row][column] == Constants.EMPTY) {
					number_of_empty_cells++;
				}
			}
		}
		return number_of_empty_cells;
	}

	// Make a move; it places a symbol on the board
	public void makeMove(int row, int column, int player) {
		this.gameBoard[row][column] = player;
		this.lastMove = new Move(row, column, evaluate());
		this.lastPlayer = player;
	}

	// Checks whether a move is valid; whether a square is empty
	public boolean isValidMove(int row, int column) {
		if ((row == -1) || (column == -1) || (row > 2) || (column > 2)) {
			return false;
		}
		return this.gameBoard[row][column] == Constants.EMPTY;
	}

	/* Generates the children of the state.
	 * Any square in the board that is empty results to a child. */
	public List<Board> getChildren(int symbol) {
		List<Board> children = new ArrayList<>();
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				if (isValidMove(row, column)) {
					Board child = new Board(this);
					child.makeMove(row, column, symbol);
					children.add(child);
				}
			}
		}
		return children;
	}

	public List<Move> getValidMoves(int symbol) {
		List<Move> moves = new ArrayList<>();
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				if (isValidMove(row, column)) {
					moves.add(new Move(row, column, symbol));
				}
			}
		}
		return moves;
	}

	/* +100 for EACH 3-in-a-line for X.
	 * +10 for EACH 2-in-a-line (with 1 empty cell) for X.
	 * +1 for EACH 1-in-a-line (with 2 empty cells) for X.
	 * Negative scores for O, i.e., -100, -10, -1 for EACH O's 3-in-a-line, 2-in-a-line and 1-in-a-line. */
	public int evaluate() {
		int Xlines = 0;
		int Olines = 0;
		int sum;

		// Checking rows
		for (int row = 0; row < 3; row++) {
			sum = gameBoard[row][0] + gameBoard[row][1] + gameBoard[row][2];
			if (sum == 3) {
				Xlines = Xlines + 100;
				// If the sum is 2, then there is definitely 1 empty cell.
			} else if (sum == 2) {
				Xlines = Xlines + 10;
				// If the sum is 1 and there is at least 1 empty cell, then the other cell left is definitely empty too.
			} else if (sum == 1 && (gameBoard[row][0] == 0 || gameBoard[row][1] == 0 || gameBoard[row][2] == 0)) {
				Xlines = Xlines + 1;
			} else if (sum == -3) {
				Olines = Olines + 100;
				// If the sum is -2, then there is definitely 1 empty cell.
			} else if (sum == -2) {
				Olines = Olines + 10;
				// If the sum is -1 and there is at least 1 empty cell, then the other cell left is definitely empty too.
			} else if (sum == -1 && (gameBoard[row][0] == 0 || gameBoard[row][1] == 0 || gameBoard[row][2] == 0)) {
				Olines = Olines + 1;
			}
		}

		// Checking columns
		for (int column = 0; column < 3; column++) {
			sum = gameBoard[0][column] + gameBoard[1][column] + gameBoard[2][column];
			if (sum == 3) {
				Xlines = Xlines + 100;
				// If the sum is 2, then there is definitely 1 empty cell.
			} else if (sum == 2) {
				Xlines = Xlines + 10;
				// If the sum is 1 and there is at least 1 empty cell, then the other cell left is definitely empty too.
			} else if (sum == 1 && (gameBoard[0][column] == 0 || gameBoard[1][column] == 0 || gameBoard[2][column] == 0)) {
				Xlines = Xlines + 1;
			} else if (sum == -3) {
				Olines = Olines + 100;
				// If the sum is -2, then there is definitely 1 empty cell.
			} else if (sum == -2) {
				Olines = Olines + 10;
				// If the sum is -1 and there is at least 1 empty cell, then the other cell left is definitely empty too.
			} else if (sum == -1 && (gameBoard[0][column] == 0 || gameBoard[1][column] == 0 || gameBoard[2][column] == 0)) {
				Olines = Olines + 1;
			}
		}

		// Checking diagonals
		sum = gameBoard[0][0] + gameBoard[1][1] + gameBoard[2][2];
		if (sum == 3) {
			Xlines = Xlines + 100;
			// If the sum is 2, then there is definitely 1 empty cell.
		} else if (sum == 2) {
			Xlines = Xlines + 10;
			// If the sum is 1 and there is at least 1 empty cell, then the other cell left is definitely empty too.
		} else if (sum == 1 && (gameBoard[0][0] == 0 || gameBoard[1][1] == 0 || gameBoard[2][2] == 0)) {
			Xlines = Xlines + 1;
		} else if (sum == -3) {
			Olines = Olines + 100;
			// If the sum is -2, then there is definitely 1 empty cell.
		} else if (sum == -2) {
			Olines = Olines + 10;
			// If the sum is -1 and there is at least 1 empty cell, then the other cell left is definitely empty too.
		} else if (sum == -1 && (gameBoard[0][0] == 0 || gameBoard[1][1] == 0 || gameBoard[2][2] == 0)) {
			Olines = Olines + 1;
		}
		sum = gameBoard[0][2] + gameBoard[1][1] + gameBoard[2][0];
		if (sum == 3) {
			Xlines = Xlines + 100;
			// If the sum is 2, then there is definitely 1 empty cell.
		} else if (sum == 2) {
			Xlines = Xlines + 10;
			// If the sum is 1 and there is at least 1 empty cell, then the other cell left is definitely empty too.
		} else if (sum == 1 && (gameBoard[0][2] == 0 || gameBoard[1][1] == 0 || gameBoard[2][0] == 0)) {
			Xlines = Xlines + 1;
		} else if (sum == -3) {
			Olines = Olines + 100;
			// If the sum is -2, then there is definitely 1 empty cell.
		} else if (sum == -2) {
			Olines = Olines + 10;
			// If the sum is -1 and there is at least 1 empty cell, then the other cell left is definitely empty too.
		} else if (sum == -1 && (gameBoard[0][2] == 0 || gameBoard[1][1] == 0 || gameBoard[2][0] == 0)) {
			Olines = Olines + 1;
		}

		return Xlines - Olines;
	}

	/*
	 * A state is terminal if there is a TicTacToe
	 * or no empty tiles are available
	 */
	public boolean isTerminal() {
		// Checking if there is a horizontal TicTacToe
		for (int row = 0; row < 3; row++) {
			if ((gameBoard[row][0] == gameBoard[row][1]) && (gameBoard[row][1] == gameBoard[row][2]) && (gameBoard[row][0] != Constants.EMPTY)) {
				setWinner(gameBoard[row][0]);
				return true;
			}
		}

		// Checking if there is a vertical TicTacToe
		for (int column = 0; column < 3; column++) {
			if ((gameBoard[0][column] == gameBoard[1][column]) && (gameBoard[1][column] == gameBoard[2][column]) && (gameBoard[0][column] != Constants.EMPTY)) {
				setWinner(gameBoard[0][column]);
				return true;
			}
		}

		// Checking if there is a diagonal TicTacToe
		if ((gameBoard[0][0] == gameBoard[1][1]) && (gameBoard[1][1] == gameBoard[2][2]) && (gameBoard[1][1] != Constants.EMPTY)) {
			setWinner(gameBoard[0][0]);
			return true;
		}
		if ((gameBoard[0][2] == gameBoard[1][1]) && (gameBoard[1][1] == gameBoard[2][0]) && (gameBoard[1][1] != Constants.EMPTY)) {
			setWinner(gameBoard[0][2]);
			return true;
		}

		// Checking if there is at least one empty tile
		return (Board.isGameBoardFull(gameBoard));
	}

	public void changeLastSymbolPlayed() {
		if (this.lastPlayer == Constants.X) {
			this.lastPlayer = Constants.O;
		} else if (this.lastPlayer == Constants.O) {
			this.lastPlayer = Constants.X;
		}
	}

	private String getGameBoardAsString() {
		StringBuilder output = new StringBuilder();
		output.append("*********").append("\n");
		int counter = 1;
		for (int row = 0; row < 3; row++) {
			output.append("* ");
			for (int column = 0; column < 3; column++) {
				switch (gameBoard[row][column]) {
					case Constants.X:
						output.append("X ");
						break;
					case Constants.O:
						output.append("O ");
						break;
					case Constants.EMPTY:
						output.append(counter).append(" ");
						break;
					default:
						break;
				}
				counter++;
			}
			output.append("*").append("\n");
		}
		output.append("*********").append("\n");
		return output.toString();
	}

	// Prints the board, using "X", "O" and 1-9 for ids
	@Override
	public String toString() {
		return getGameBoardAsString();
	}

}
