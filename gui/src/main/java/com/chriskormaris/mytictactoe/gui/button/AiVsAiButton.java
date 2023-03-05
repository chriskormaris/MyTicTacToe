package com.chriskormaris.mytictactoe.gui.button;

import com.chriskormaris.mytictactoe.api.util.Constants;
import com.chriskormaris.mytictactoe.gui.GUI;

import java.awt.event.ActionEvent;


public class AiVsAiButton extends XOButton {

	// Empty: 0, X: 1, O: 0
	public int id;
	public int aiPlayer;


	public AiVsAiButton(int id) {
		setFocusable(false);
		this.id = id;
		this.addActionListener(this);
		setIcon(null);
		this.aiPlayer = Constants.EMPTY;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (aiPlayer == Constants.X) {
			setIcon(GUI.XIcon);
		} else if (aiPlayer == Constants.O) {
			setIcon(GUI.OIcon);
		}

		System.out.println(GUI.board);

		removeActionListener(this);
	}

}
