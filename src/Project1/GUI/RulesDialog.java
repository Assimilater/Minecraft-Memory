package Project1.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RulesDialog extends JPanel implements ActionListener {
	private static RulesDialog instance;
	public static RulesDialog get() { return instance; }
	
	public RulesDialog() {
		instance = this;
	}
	public void actionPerformed(ActionEvent e) {
		
	}
}
