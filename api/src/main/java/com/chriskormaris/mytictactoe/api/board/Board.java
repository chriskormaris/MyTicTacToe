package com.chriskormaris.mytictactoe.api.board;

import com.chriskormaris.mytictactoe.api.util.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

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
			Arrays.fill(this.gameBoard[i], Constants.EMPTY);
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
	public boolean isEmpty() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (gameBoard[i][j] != Constants.EMPTY) {
					return false;
				}
			}
		}
		return true;
	}

	// Checking if the board is full,
	// by checking if there is at least one empty tile.
	public boolean isGameBoardFull() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (gameBoard[i][j] == Constants.EMPTY) {
					return false;
				}
			}
		}
		return true;
	}

	public int getNumberOfEmptyCells() {
		int numberOfEmptyCells = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (gameBoard[i][j] == Constants.EMPTY) {
					numberOfEmptyCells++;
				}
			}
		}
		return numberOfEmptyCells;
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
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (isValidMove(i, j)) {
					Board child = new Board(this);
					child.makeMove(i, j, symbol);
					children.add(child);
				}
			}
		}
		return children;
	}

	public List<Move> getValidMoves(int symbol) {
		List<Move> moves = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (isValidMove(i, j)) {
					moves.add(new Move(i, j, symbol));
				}
			}
		}
		return moves;
	}

	/* Infinity if 3-in-a-line for X.
	 * +10 for EACH 2-in-a-line (with 1 empty cell) for X.
	 * +1 for EACH 1-in-a-line (with 2 empty cells) for X.
	 * Negative scores for O, i.e., -Infinity, -10, -1 for EACH O's 3-in-a-line, 2-in-a-line and 1-in-a-line. */
	public int evaluate() {
		int XScore = 0;
		int OScore = 0;

		// Checking rows
		for (int i = 0; i < 3; i++) {
			int sum = gameBoard[i][0] + gameBoard[i][1] + gameBoard[i][2];
			if (sum == 3) {
				return Integer.MAX_VALUE;
			} else if (sum == 2) {
				// If the sum is 2, then there is definitely 1 empty cell.
				XScore = XScore + 10;
			} else if (sum == 1 && (gameBoard[i][0] == 0 || gameBoard[i][1] == 0 || gameBoard[i][2] == 0)) {
				// If the sum is 1 and there is at least 1 empty cell, then the other cell left is definitely empty too.
				XScore = XScore + 1;
			} else if (sum == -3) {
				return Integer.MIN_VALUE;
			} else if (sum == -2) {
				// If the sum is -2, then there is definitely 1 empty cell.
				OScore = OScore + 10;
			} else if (sum == -1 && (gameBoard[i][0] == 0 || gameBoard[i][1] == 0 || gameBoard[i][2] == 0)) {
				// If the sum is -1 and there is at least 1 empty cell, then the other cell left is definitely empty too.
				OScore = OScore + 1;
			}
		}

		// Checking columns
		for (int j = 0; j < 3; j++) {
			int sum = gameBoard[0][j] + gameBoard[1][j] + gameBoard[2][j];
			if (sum == 3) {
				return Integer.MAX_VALUE;
			} else if (sum == 2) {
				// If the sum is 2, then there is definitely 1 empty cell.
				XScore = XScore + 10;
			} else if (sum == 1 && (gameBoard[0][j] == 0 || gameBoard[1][j] == 0 || gameBoard[2][j] == 0)) {
				// If the sum is 1 and there is at least 1 empty cell, then the other cell left is definitely empty too.
				XScore = XScore + 1;
			} else if (sum == -3) {
				return Integer.MIN_VALUE;
			} else if (sum == -2) {
				// If the sum is -2, then there is definitely 1 empty cell.
				OScore = OScore + 10;
			} else if (sum == -1 && (gameBoard[0][j] == 0 || gameBoard[1][j] == 0 || gameBoard[2][j] == 0)) {
				// If the sum is -1 and there is at least 1 empty cell, then the other cell left is definitely empty too.
				OScore = OScore + 1;
			}
		}

		// Checking diagonals

		// downward diagonal
		int sum = gameBoard[0][0] + gameBoard[1][1] + gameBoard[2][2];
		if (sum == 3) {
			return Integer.MAX_VALUE;
		} else if (sum == 2) {
			// If the sum is 2, then there is definitely 1 empty cell.
			XScore = XScore + 10;
		} else if (sum == 1 && (gameBoard[0][0] == 0 || gameBoard[1][1] == 0 || gameBoard[2][2] == 0)) {
			// If the sum is 1 and there is at least 1 empty cell, then the other cell left is definitely empty too.
			XScore = XScore + 1;
		} else if (sum == -3) {
			return Integer.MIN_VALUE;
		} else if (sum == -2) {
			// If the sum is -2, then there is definitely 1 empty cell.
			OScore = OScore + 10;
		} else if (sum == -1 && (gameBoard[0][0] == 0 || gameBoard[1][1] == 0 || gameBoard[2][2] == 0)) {
			// If the sum is -1 and there is at least 1 empty cell, then the other cell left is definitely empty too.
			OScore = OScore + 1;
		}

		// upward diagonal
		sum = gameBoard[0][2] + gameBoard[1][1] + gameBoard[2][0];
		if (sum == 3) {
			return Integer.MAX_VALUE;
		} else if (sum == 2) {
			// If the sum is 2, then there is definitely 1 empty cell.
			XScore = XScore + 10;
		} else if (sum == 1 && (gameBoard[0][2] == 0 || gameBoard[1][1] == 0 || gameBoard[2][0] == 0)) {
			// If the sum is 1 and there is at least 1 empty cell, then the other cell left is definitely empty too.
			XScore = XScore + 1;
		} else if (sum == -3) {
			return Integer.MIN_VALUE;
		} else if (sum == -2) {
			// If the sum is -2, then there is definitely 1 empty cell.
			OScore = OScore + 10;
		} else if (sum == -1 && (gameBoard[0][2] == 0 || gameBoard[1][1] == 0 || gameBoard[2][0] == 0)) {
			// If the sum is -1 and there is at least 1 empty cell, then the other cell left is definitely empty too.
			OScore = OScore + 1;
		}

		return XScore - OScore;
	}

	/*
	 * A state is terminal if there is a TicTacToe
	 * or no empty tiles are available
	 */
	public boolean isTerminal() {
		// Checking if there is a horizontal TicTacToe
		for (int i = 0; i < 3; i++) {
			if (gameBoard[i][0] == gameBoard[i][1]
					&& gameBoard[i][1] == gameBoard[i][2]
					&& gameBoard[i][0] != Constants.EMPTY) {
				setWinner(gameBoard[i][0]);
				return true;
			}
		}

		// Checking if there is a vertical TicTacToe
		for (int j = 0; j < 3; j++) {
			if (gameBoard[0][j] == gameBoard[1][j]
					&& gameBoard[1][j] == gameBoard[2][j]
					&& gameBoard[0][j] != Constants.EMPTY) {
				setWinner(gameBoard[0][j]);
				return true;
			}
		}

		// Checking if there is a diagonal TicTacToe
		if (gameBoard[0][0] == gameBoard[1][1]
				&& gameBoard[1][1] == gameBoard[2][2]
				&& gameBoard[1][1] != Constants.EMPTY) {
			setWinner(gameBoard[0][0]);
			return true;
		}
		if (gameBoard[0][2] == gameBoard[1][1]
				&& gameBoard[1][1] == gameBoard[2][0]
				&& gameBoard[1][1] != Constants.EMPTY) {
			setWinner(gameBoard[0][2]);
			return true;
		}

		// Checking if there is at least one empty tile
		return (isGameBoardFull());
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
		for (int i = 0; i < 3; i++) {
			output.append("* ");
			for (int j = 0; j < 3; j++) {
				switch (gameBoard[i][j]) {
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
