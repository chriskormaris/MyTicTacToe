package com.chriskormaris.mytictactoe.api.ai;

import com.chriskormaris.mytictactoe.api.board.Board;
import com.chriskormaris.mytictactoe.api.board.Move;

import java.util.List;
import java.util.Random;

public class RandomChoiceAI extends AI {

	public RandomChoiceAI(int aiPlayer) {
		super(aiPlayer);
	}

	// Initiates the random move.
	@Override
	public Move getNextMove(Board board) {
		Random r = new Random();

		List<Move> validMoves = board.getValidMoves(getAiPlayer());
		int randomMoveIndex = r.nextInt(validMoves.size());

		return validMoves.get(randomMoveIndex);
	}

}