package primer04_CalculatorFrame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Frame sa panelom kalkulator
 */
public class CalculatorFrame extends JFrame {

	private static final long serialVersionUID = -2751471467610325447L;

	// private JButton display;
	private JLabel display;
	private JPanel panel;
	private double result;
	//poslednja komanda se cuva da bi se radilo racunanja
	private String lastCommand;
	//za onaci spremnost unosa brojeva
	private boolean start;

	public CalculatorFrame() {
		setTitle("Calculator");
		setLayout(new BorderLayout());

		result = 0;
		lastCommand = "=";
		start = true;

		// dodaje se displej

		display = new JLabel("");
		display.setPreferredSize(new Dimension(50, 50));
		//ispis teksta u lebeli je centralno orijentisan
		display.setHorizontalAlignment(SwingConstants.CENTER);

		add(display, BorderLayout.NORTH);

		ActionListener insert = new InsertAction();
		ActionListener command = new CommandAction();

		// panel sa dugmićima
		panel = new JPanel();

		// dugmici u matricu 4x4
		panel.setLayout(new GridLayout(4, 4, 5, 5));

		addButton("7", insert);
		addButton("8", insert);
		addButton("9", insert);
		addButton("/", command);

		addButton("4", insert);
		addButton("5", insert);
		addButton("6", insert);
		addButton("*", command);

		addButton("1", insert);
		addButton("2", insert);
		addButton("3", insert);
		addButton("-", command);

		addButton("0", insert);
		addButton(".", insert);
		addButton("=", command);
		addButton("+", command);

		add(panel, BorderLayout.CENTER);

		pack();
	}
	
	/**
	 * dugmici idu na centar panela border layouta
	 * 
	 * @param label
	 *            the button label
	 * @param listener
	 *            the button listener
	 */
	private void addButton(String label, ActionListener listener) {
		JButton button = new JButton(label);
		button.addActionListener(listener);
		panel.add(button);
	}

	/**
	 * ova akcija prikazuje vrednost na displej
	 */
	private class InsertAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// u stringu input će se naći tekst dugmeta
			String input = event.getActionCommand();
			if (start) {
				display.setText("");
				start = false;
			}
			display.setText(display.getText() + input);
		}
	}

	/**
	 * poziva komandu za odgovarajuce dugme
	 */
	private class CommandAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String command = event.getActionCommand();
			if (start) {
				if (command.equals("-")) {
					display.setText(command);
					start = false;
				} else
					lastCommand = command;
			} else {
				calculate(Double.parseDouble(display.getText()));
				lastCommand = command;
				start = true;
			}
		}
	}

	/**
	 * racuna
	 * 
	 * @param x
	 *            the value to be accumulated with the prior result.
	 */
	public void calculate(double x) {
		if (lastCommand.equals("+"))
			result += x;
		else if (lastCommand.equals("-"))
			result -= x;
		else if (lastCommand.equals("*"))
			result *= x;
		else if (lastCommand.equals("/"))
			result /= x;
		else if (lastCommand.equals("="))
			result = x;
		display.setText("" + result);
	}

}
