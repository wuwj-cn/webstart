package com.saber.applet;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Event;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

public class Calculate extends Applet {
	Button btnExit = new Button("Exit");
	Button btnRun = new Button("Run");
	Label lblIntro = new Label("Please inpput positive integer here: ");
	Label lblResult = new Label("The result is: ");
	TextField txtIntro = new TextField("0");
	TextField txtResult = new TextField("0");
	Panel p1 = new Panel();
	Panel p2 = new Panel();
	Panel p3 = new Panel();
	
	public void init() {
		resize(350, 200);
		setLayout(new BorderLayout());
		p1.add(lblIntro);
		p1.add(txtIntro);
		
		p2.add(lblResult);
		p2.add(txtResult);
		
		p3.add(btnRun);
		p3.add(btnExit);
		
		add("North", p1);
		add("Center", p2);
		add("South", p3);
	}

	public boolean action(Event evt, Object what) {
		if(evt.target instanceof Button) {
			String name = (String) what;
			if("Exit".equals(name)) System.exit(0);
			else if("Run".equals(name)){
				int sum = 0;
				for(int i = 0; i <= Integer.parseInt(txtIntro.getText().trim()); i++) sum += i;
				txtResult.setText(String.valueOf(sum));
			}
		}
		return super.action(evt, what);
	}
	
	
}