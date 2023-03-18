package com.chriskormaris.mytictactoe.gui.button;

import com.chriskormaris.mytictactoe.api.util.Constants;
import com.chriskormaris.mytictactoe.gui.GUI;

import java.awt.event.ActionEvent;


public class AiVsAiButton extends XOButton {

	// Empty: 0, X: 1, O: 0
	public int id;
	public int aiPlayer;

	GUI gui;


	public AiVsAiButton(int id, GUI gui) {
		setFocusable(false);
		this.id = id;
		this.addActionListener(this);
		setIcon(null);
		this.aiPlayer = Constants.EMPTY;
		this.gui = gui;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (aiPlayer == Constants.X) {
			setIcon(gui.XIcon);
		} else if (aiPlayer == Constants.O) {
			setIcon(gui.OIcon);
		}

		System.out.println(gui.board);

		removeActionListener(this);
	}

}
