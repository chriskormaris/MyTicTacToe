package com.chriskormaris.mytictactoe.gui.button;

import com.chriskormaris.mytictactoe.api.util.Constants;
import com.chriskormaris.mytictactoe.gui.GUI;

import java.awt.event.ActionEvent;


public class AiVsAiButton extends XOButton {

	// Empty: 0, X: 1, O: 0
	public int aiPlayer;

	public AiVsAiButton(int id, GUI gui) {
		super(id, gui);
		this.aiPlayer = Constants.EMPTY;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (aiPlayer == Constants.X) {
			setIcon(gui.XIcon);
		} else if (aiPlayer == Constants.O) {
			setIcon(gui.OIcon);
		}

		System.out.println(gui.board);

		removeActionListener(this);
	}

}
