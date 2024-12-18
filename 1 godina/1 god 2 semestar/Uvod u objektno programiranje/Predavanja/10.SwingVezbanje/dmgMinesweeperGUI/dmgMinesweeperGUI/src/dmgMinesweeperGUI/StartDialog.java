package dmgMinesweeperGUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class StartDialog extends JDialog {
	
	private ButtonGroup levels;
	private JRadioButton easy;
	private JRadioButton medium;
	private JRadioButton hard;
	private JButton accept;
	
	static int n = 9;
	
	public StartDialog() {
		setTitle("dmgMinesweeper - odaberi nivo");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(300, 120);
		setResizable(false);
		
		levels = new ButtonGroup();
		
		easy = new JRadioButton("Lako");
		easy.setSelected(true);
		medium = new JRadioButton("Srednje");
		hard = new JRadioButton("Teško");
		
		levels.add(easy);
		levels.add(medium);
		levels.add(hard);
		
		getContentPane().setLayout(new FlowLayout());
		
		getContentPane().add(easy);
		getContentPane().add(medium);
		getContentPane().add(hard);
		
		accept = new JButton(" Započni igru ");
		accept.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Minesweeper();
				
			}
		});
		getContentPane().add(accept);
		
		accept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (easy.isSelected()) {
					Minesweeper.n = 9;
					Minesweeper.b = 10;
				}
				else if (medium.isSelected()){
					System.out.println(" Srednje ");
					Minesweeper.n = 10;
					Minesweeper.b = 15;
				}
				else if (hard.isSelected()){
					System.out.println(" Tesko ");
					Minesweeper.n = 15;
					Minesweeper.b = 30;
				}
			}
		});
		
		setVisible(true);
	}
}
