package com.chriskormaris.mytictactoe.api.ai;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RowCol {

	private int row;
	private int column;

	public RowCol() {
		this.row = 0;
		this.column = 0;
	}

	public RowCol(int row, int col) {
		this.row = row;
		this.column = col;
	}

	public RowCol(RowCol otherRowCol) {
		this.row = otherRowCol.getRow();
		this.column = otherRowCol.getColumn();
	}

}
