package hw6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GUI extends JFrame implements ActionListener {
	// Application runtime related
	private Question head, current;
	
	// MainFrame Related
	private static final int WIDTH = 325, HEIGHT = 200;
	private JLabel Q_Remaining, Q_Prompt;
	private JButton A_Yes, A_No;
	private Container pane;
	private Font Display;
	
	public GUI(Question h) {
		// Keep track of the head question
		head = h;
		
		// Set standard JFrame properties
		pane = getContentPane();
		pane.setLayout(null);

		this.setResizable(false);
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("Musical Questions!");
		this.setLocationRelativeTo(null); // Cool trick to center the window
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		// Set the default font for the document
		Display = new Font("Serif", Font.PLAIN, 20);
		
		// Initialize the labels
		Q_Remaining = new JLabel();
		Q_Remaining.setFont(Display);
		Q_Remaining.setBounds(5, 5, WIDTH - 50, 30);
		Q_Remaining.setVerticalAlignment(JLabel.TOP);
		
		Q_Prompt = new JLabel();
		Q_Prompt.setFont(Display);
		Q_Prompt.setBounds(5, 40, WIDTH - 50, 75);
		Q_Prompt.setVerticalAlignment(JLabel.TOP);
		
		// Initialize the buttons
		A_Yes = new JButton("Yes");
		A_Yes.setFont(Display);
		A_Yes.setBounds(5, HEIGHT - 75, 125, 25);
		A_Yes.setMnemonic(KeyEvent.VK_Y);
		A_Yes.addActionListener(this);
		
		A_No = new JButton("No");
		A_No.setFont(Display);
		A_No.setBounds(WIDTH - 150, HEIGHT - 75, 125, 25);
		A_No.setMnemonic(KeyEvent.VK_N);
		A_No.addActionListener(this);
		
		// Add the controls to the content pane
		pane.add(Q_Remaining);
		pane.add(Q_Prompt);
		pane.add(A_Yes);
		pane.add(A_No);
		
		// Show our new amazing window!
		this.setVisible(true);
		
		// Start the game!
		this.loadNext(head);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == A_Yes) {
			loadNext(current.getIfYes());
		}
		else if (e.getSource() == A_No) {
			if (current == head) {
				JOptionPane.showMessageDialog(this, "Hurry up already!!!", "Waiting.........", JOptionPane.WARNING_MESSAGE);
			}
			loadNext(current.getIfNo());
		}
	}
	
	// Small helper method to assist with updating the display based on how many questions are remaining after the current question is answered
	private void loadNext(Question q) {
		if (q == null) {
			JOptionPane.showMessageDialog(this, "Thanks for playing!!!", "Goodbye.........", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		else {
			Q_Remaining.setText("<html>Questions Remaining: " + q.getNumLeft() + "</html>");
			Q_Prompt.setText("<html>" + q.getPrompt() + "</html>");
			current = q;
		}
	}
}
