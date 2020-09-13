	package simpleCalculator;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class UserInterface extends JFrame {
	
	private JPanel textPanel;
	private JPanel buttonPanel;
	private JTextField textField;
	private static final int DEFAULT_WIDTH = 250;
	private static final int DEFAULT_HEIGHT = 300;
	
	public UserInterface() {
				
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		textPanel = new JPanel();
		buttonPanel = new JPanel();
		textField = new JTextField(15);
		textPanel.add(textField);
		
		buttonPanel.setLayout(new GridLayout(6, 3));
		
		for(int i = 0; i < 10; i++) {
			JButton button = makeButton(Integer.toString(i));
		}
		JButton plus =  makeButton(" + ");
		JButton minus =  makeButton(" - ");
		JButton product =  makeButton(" * ");
		JButton divide =  makeButton(" / ");
		JButton openBracers =  makeButton("( ");
		JButton closeBracers =  makeButton(" )");
		
		JButton deleteButton = new JButton("<-");
		buttonPanel.add(deleteButton);
		deleteButton.addActionListener(action -> {
			if(!textField.getText().isEmpty())
				textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
		});
		
		JButton resultButton = new JButton("=");
		buttonPanel.add(resultButton);
		resultButton.addActionListener(action -> {
			ConsoleCalculator calculator = new ConsoleCalculator();
			int answer = calculator.makeCalculations(textField.getText());
			textField.setText(Integer.toString(answer));
		});
		
		add(buttonPanel, BorderLayout.CENTER);
		add(textPanel, BorderLayout.NORTH);
		
	}
	
	public JButton makeButton(String string) {
		JButton button = new JButton(string);
		buttonPanel.add(button);
		button.addActionListener(action -> {
			textField.setText(textField.getText() + string);
		});
		return button;
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			UserInterface ui = new UserInterface();
			ui.setTitle("Calculator");
			ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ui.setVisible(true);
		});
	}
	
}
