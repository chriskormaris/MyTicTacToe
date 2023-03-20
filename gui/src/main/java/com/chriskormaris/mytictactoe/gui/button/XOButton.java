package com.chriskormaris.mytictactoe.gui.button;

import com.chriskormaris.mytictactoe.gui.GUI;
import com.chriskormaris.mytictactoe.gui.enumeration.GuiStyle;

import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class XOButton extends JButton implements ActionListener {

	public int id;
	GUI gui;

	public XOButton(int id, GUI gui) {
		this.id = id;
		this.gui = gui;
		setFocusable(false);
		setBackground(Color.LIGHT_GRAY);
		addActionListener(this);
		setBorder(BorderFactory.createLoweredSoftBevelBorder());

		if (gui.gameParameters.getGuiStyle() == GuiStyle.SYSTEM_STYLE) {
			setUI(new MetalButtonUI());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
