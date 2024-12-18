package grid.bag.layout;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TestFrameGB extends JFrame {
	
	private static final long serialVersionUID = 1L;
	JLabel settingsLabel = new JLabel("Dashboard Settings");
	JLabel nameLabel = new JLabel("Name:");
	JLabel zipLabel = new JLabel("Zip Code:");
	JLabel emailLabel = new JLabel("Email:");
	JLabel avatarLabel = new JLabel("Avatar Image:");
	JTextField fNameField = new JTextField(10);
	JTextField mNameField = new JTextField(1);
	JTextField lNameField = new JTextField(15);
	JTextField zipField = new JTextField(5);
	JTextField emailField = new JTextField(20);
	JTextField avatarField = new JTextField(30);
	JButton okBttn = new JButton("Ok");
	JButton cancelBttn = new JButton("Cancel");
	JButton helpBttn = new JButton("Help");

	public TestFrameGB() {
		setTitle("GridBag Layout test");
		setSize(300, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		pack();
	}

	private void initGUI() {
		add(buildStandardDashboard());
	}

    private JPanel buildStandardDashboard() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        buildGridBagForm(panel);
        return panel;
    }

    private void buildGridBagForm(JPanel panel) {

        //explicitly setting insets using an empty border
        panel.setBorder(BorderFactory.createEmptyBorder(17,17,17,17));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 4; //gui ima najvise 4 celije po redu
        constraints.insets = new Insets(0,0,15,0);
        panel.add(settingsLabel, constraints);

        constraints.insets = new Insets(0,0,0,0);
        constraints.gridy++;
        constraints.gridwidth = 1;
        //alignment for each label must be explicitly set
        constraints.anchor = GridBagConstraints.EAST;
        panel.add(nameLabel, constraints);

        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(fNameField, constraints);
        panel.add(mNameField, constraints);
        panel.add(lNameField, constraints);
        //velicina text field-ova odredjena je njihovim konstruktorima
        //velicina je u kolonama i nije ogranicenje za broj karaktera koji se moze uneti
        //jedna kolona se otprilike moze tretirati kao jedan karakter, ali kranja velicina polja zavisi i od fonta
        
        constraints.gridy++;
        constraints.anchor = GridBagConstraints.EAST;
        panel.add(zipLabel, constraints);

        constraints.anchor = GridBagConstraints.WEST;
        panel.add(zipField, constraints);

        constraints.gridy++;
        constraints.anchor = GridBagConstraints.EAST;
        panel.add(emailLabel,constraints);

        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridwidth = 3;
        panel.add(emailField, constraints);

        constraints.gridwidth = 1;
        constraints.gridy++;
        constraints.anchor = GridBagConstraints.EAST;
        panel.add(avatarLabel, constraints);

        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(avatarField, constraints);

       /* constraints.gridy++;
        constraints.gridwidth = 3;
        panel.add(helpBttn, constraints);*/

        constraints.gridy++;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.CENTER;
        
        JPanel pButtons = new JPanel();
//        pButtons.setLayout(new FlowLayout(FlowLayout.CENTER));
        pButtons.add(helpBttn);
        pButtons.add(cancelBttn);
        pButtons.add(okBttn);
        panel.add(pButtons, constraints);
//        Box buttonBox = Box.createHorizontalBox();
//        buttonBox.add(cancelBttn);
//        buttonBox.add(Box.createHorizontalStrut(5));
//        buttonBox.add(okBttn);
//        panel.add(buttonBox, constraints);
    }
}
