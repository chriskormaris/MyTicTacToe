package com.chriskormaris.mytictactoe.api.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/* A class describing a move in the board
 * Every produced child corresponds to a move,
 * and we need to keep the moves as well as the states.
 */
@Getter
@Setter
@ToString
public class Move implements Serializable {

	private int row;
	private int column;
	private int value;

	public Move() {
		this.row = 0;
		this.column = 0;
	}

	public Move(int row, int col) {
		this.row = row;
		this.column = col;
		this.value = 0;
	}

	public Move(int value) {
		this.row = 0;
		this.column = 0;
		this.value = value;
	}

	public Move(int row, int col, int value) {
		this.row = row;
		this.column = col;
		this.value = value;
	}

	public Move(Move otherMove) {
		this.row = otherMove.getRow();
		this.column = otherMove.getColumn();
		this.value = otherMove.getValue();
	}

}
