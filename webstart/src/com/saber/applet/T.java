package com.saber.applet;

import java.applet.Applet;
import java.awt.Graphics;

import com.saber.comm.Comm;

public class T extends Applet {

	Comm comm = new Comm();
	
	public void paint(Graphics g) {
		g.drawString("hello, this is my applet test...", 10, 30);
	}
}
