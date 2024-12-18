package primer05_GridBagLayoutTest;

import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

class FontDialogFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FontDialogFrame() {
		setTitle("FontDialog");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);

		JLabel fontLabel = new JLabel("Font: ");

		font = new JComboBox<>(new String[] { "Calibri font", "Cambria font", "Garamond font", "Georgia font", "Helvetica font", "Book Antiqua", "Lucida Sans Unicode font"});

		JLabel sizeLabel = new JLabel("Size to chose: ");

		size = new JComboBox<>(new String[] { "8", "10", "12", "15", "18", "24", "36", "48" });

		bold = new JCheckBox("Bold");

		italic = new JButton("Italic");
		sample = new JTextArea();
		sample.setText("Knowing the top fonts in use today and when and where to use them can give your printing company a competitive edge.  More and more brands are becoming font conscious when designing their campaigns. Being able to provide your input on typeface selection early in the sales cycle can land you quality customers for the long run");
		sample.setEditable(false);
		sample.setWrapStyleWord(true);
		sample.setLineWrap(true);

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.EAST;
		c.weightx = 0;
		c.weighty = 0;
		add(fontLabel, c);

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 100;
		c.weighty = 0;
		add(font, c);

		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		c.weightx = 0;
		c.weighty = 0;
		add(sizeLabel, c);

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(2, 2, 2, 2);
		c.weightx = 100;
		c.weighty = 0;
		add(size, c);

		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 100;
		c.weighty = 100;
		add(bold, c);

		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.EAST;
		c.weightx = 100;
		c.weighty = 100;
		add(italic, c);

		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 4;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.weighty = 100;
		add(sample, c);
	}

	public static void main(String[] args) {
		FontDialogFrame frame = new FontDialogFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static final int DEFAULT_WIDTH = 500;
	public static final int DEFAULT_HEIGHT = 200;

	private JComboBox<String> font;
	private JComboBox<String> size;
	private JCheckBox bold;
	private JButton italic;
	private JTextArea sample;

}
