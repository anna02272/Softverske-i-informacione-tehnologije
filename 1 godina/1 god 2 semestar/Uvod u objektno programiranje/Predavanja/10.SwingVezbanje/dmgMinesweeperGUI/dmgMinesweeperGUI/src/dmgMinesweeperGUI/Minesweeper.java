package dmgMinesweeperGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Minesweeper extends JFrame {

	// EASY
	//public static int n = 9;
	//public static int b = 10;

	// MEDIUM
	static int n = 16;
	static int b = 10;

	// HARD
	// final static int n = 22;
	// final static int b = 100;

	private JPanel jTopPanel;
	private JPanel jBoardPanel;
	
	private JLabel informations;

	private JButton jBtnStatus;

	private static Integer[][] matrixMines = new Integer[n][n];
	private static Integer[][] matrixAround = new Integer[n][n];
	private static Integer[][] matrixOpened = new Integer[n][n];
	private static Integer[][] matrixFlags = new Integer[n][n];
	private static JButton[][] buttons  = new JButton[n][n];

	public Minesweeper() {
		setTitle("dmgMinesweeper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(n * 48, n * 48));

		drawComponents();

		setVisible(true);
	}

	private void floodFill(int i, int j) {

		if (i < 0 || i > n - 1 || j < 0 || j > n - 1)
			return;

		if (matrixAround[i][j] > 0 || matrixAround[i][j] == -1)
			return;

		if (matrixAround[i][j] == 0)
			matrixAround[i][j] = -1;

		matrixOpened[i][j] = 1;

		floodFill(i, j + 1);
		floodFill(i, j - 1);
		floodFill(i + 1, j);
		floodFill(i - 1, j);
		floodFill(i - 1, j + 1);
		floodFill(i - 1, j - 1);
		floodFill(i + 1, j + 1);
		floodFill(i + 1, j - 1);
	}

	private int getNeighbours(Integer[][] matrix, int i, int j, int n) {

		int s = 0;

		if (0 <= i - 1 && i - 1 < n && 0 <= j - 1 && j - 1 < n
				&& matrix[i - 1][j - 1] == 1)
			s = s + 1;
		if (0 <= i - 1 && i - 1 < n && 0 <= j && j < n && matrix[i - 1][j] == 1)
			s = s + 1;
		if (0 <= i - 1 && i - 1 < n && 0 <= j + 1 && j + 1 < n
				&& matrix[i - 1][j + 1] == 1)
			s = s + 1;
		if (0 <= i && i < n && 0 <= j - 1 && j - 1 < n && matrix[i][j - 1] == 1)
			s = s + 1;
		if (0 <= i && i < n && 0 <= j + 1 && j + 1 < n && matrix[i][j + 1] == 1)
			s = s + 1;
		if (0 <= i + 1 && i + 1 < n && 0 <= j - 1 && j - 1 < n
				&& matrix[i + 1][j - 1] == 1)
			s = s + 1;
		if (0 <= i + 1 && i + 1 < n && 0 <= j && j < n && matrix[i + 1][j] == 1)
			s = s + 1;
		if (0 <= i + 1 && i + 1 < n && 0 <= j + 1 && j + 1 < n
				&& matrix[i + 1][j + 1] == 1)
			s = s + 1;

		return s;
	}

	private int checkForMinusOne(Integer[][] matrix, int i, int j, int n) {

		if (matrix[i][j] == -1)
			return 0;

		if (0 <= i - 1 && i - 1 < n && 0 <= j - 1 && j - 1 < n
				&& matrix[i - 1][j - 1] == -1)
			return -1;
		if (0 <= i - 1 && i - 1 < n && 0 <= j && j < n
				&& matrix[i - 1][j] == -1)
			return -1;
		if (0 <= i - 1 && i - 1 < n && 0 <= j + 1 && j + 1 < n
				&& matrix[i - 1][j + 1] == -1)
			return -1;
		if (0 <= i && i < n && 0 <= j - 1 && j - 1 < n
				&& matrix[i][j - 1] == -1)
			return -1;
		if (0 <= i && i < n && 0 <= j + 1 && j + 1 < n
				&& matrix[i][j + 1] == -1)
			return -1;
		if (0 <= i + 1 && i + 1 < n && 0 <= j - 1 && j - 1 < n
				&& matrix[i + 1][j - 1] == -1)
			return -1;
		if (0 <= i + 1 && i + 1 < n && 0 <= j && j < n
				&& matrix[i + 1][j] == -1)
			return -1;
		if (0 <= i + 1 && i + 1 < n && 0 <= j + 1 && j + 1 < n
				&& matrix[i + 1][j + 1] == -1)
			return -1;

		return 0;
	}

	private int checkForWin() {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				if (matrixOpened[i][j] == 0 && matrixFlags[i][j] == 0)
					return -1;

				if (matrixFlags[i][j] != matrixMines[i][j])
					return -1;
			}

		return 1;
	}

	private void drawComponents() {
		jTopPanel = new JPanel();
		jBoardPanel = new JPanel();

		final String separatorPutanje = System.getProperty("file.separator");

		jBtnStatus = new JButton();
		jBtnStatus.setName("status");
		jBtnStatus.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"images" + separatorPutanje + "smiley.png")));
		jBtnStatus.setBackground(Color.white);

		jBtnStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// RESTART
				Minesweeper.this.dispose();
				new Minesweeper();
			}
		});

		informations = new JLabel(" Broj mina na tabli je: " + b);
		
		jTopPanel.setLayout(new BorderLayout());
		
		jTopPanel.add(informations, BorderLayout.NORTH);
		jTopPanel.add(jBtnStatus);

		while (true) {
			int bomb = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (Math.random() > 0.9 && bomb < b) {
						matrixMines[i][j] = 1;
						bomb++;
					} else
						matrixMines[i][j] = 0;
				}
			}
			if (bomb == b)
				break;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrixAround[i][j] = getNeighbours(matrixMines, i, j, n);
				matrixOpened[i][j] = 0;
				matrixFlags[i][j] = 0;
			}
		}

		jBoardPanel.setLayout(new GridLayout(n, n));

		final ImageIcon imgMine = new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("images" + separatorPutanje + "minesweeper.png"));
		final ImageIcon imgFlag = new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("images" + separatorPutanje + "flag.png"));

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				final JButton btn = new JButton();
				buttons[i][j] = btn;
				btn.setBackground(new Color(0, 169, 255));
				btn.setName(i + " " + j);

				btn.addMouseListener(new MouseListener() {
					public void mouseClicked(MouseEvent e) {

						String[] tokens = btn.getName().split(" ");

						int x = Integer.parseInt(tokens[0]);
						int y = Integer.parseInt(tokens[1]);

						if (SwingUtilities.isRightMouseButton(e)
								&& e.getClickCount() == 1
								&& matrixOpened[x][y] == 0) {

							if (matrixFlags[x][y] == 0) {
								btn.setIcon(imgFlag);
								btn.setBackground(new Color(204, 204, 204));

								matrixFlags[x][y] = 1;
							} else {
								btn.setIcon(null);
								btn.setBackground(new Color(0, 169, 255));

								matrixFlags[x][y] = 0;
							}

							if (checkForWin() == 1) {
								String message = "YOU WIN";
								String[] characters = message.split(""); 
								int height = n / 2;
								int width  = (n - 7) / 2;
								
								for (int i=0; i<message.length(); i++) {
									buttons[height][width + i].setForeground(new Color(255, 255, 255));
									buttons[height][width + i].setBackground(new Color(0, 0, 0));
									buttons[height][width + i].setIcon(null);
									buttons[height][width + i].setText(characters[i+1]);
								}
							}
						}
					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						// btn.setBackground(new Color(78, 134, 236));
					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						// btn.setBackground(new Color(0, 169, 255));
					}

					@Override
					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseReleased(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}
				});

				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						String[] tokens = btn.getName().split(" ");

						int x = Integer.parseInt(tokens[0]);
						int y = Integer.parseInt(tokens[1]);

						if (matrixOpened[x][y] == 0 && matrixFlags[x][y] == 0) {
							matrixOpened[x][y] = 1;
							if (matrixMines[x][y] == 1) {
								btn.setBackground(Color.red);
								jBtnStatus.setIcon(new ImageIcon(Toolkit
										.getDefaultToolkit().getImage(
												"images" + separatorPutanje
														+ "sady.png")));

								for (int i = 0; i < n; i++) {
									for (int j = 0; j < n; j++) {
										matrixOpened[i][j] = 1;
									}
								}

								btn.setIcon(imgMine);
							} else {
								btn.setBackground(new Color(204, 204, 204));
								if (matrixAround[x][y] != 0) {
									btn.setText(matrixAround[x][y] + "");
									
									switch (matrixAround[x][y]) {
									case 1:
										btn.setForeground(new Color(0, 0, 255));
										break;
									case 2:
										btn.setForeground(new Color(10, 209, 27));
										break;
									case 3:
										btn.setForeground(new Color(255, 0, 0));
										break;
									case 4:
										btn.setForeground(new Color(0, 0, 128));
										break;
									case 5:
										btn.setForeground(new Color(128, 0, 0));
										break;
									case 6:
										btn.setForeground(new Color(0, 128, 0));
										break;
									case 7:
										btn.setForeground(new Color(128, 0, 128));
										break;
									case 8:
										btn.setForeground(new Color(0, 0, 0));
										break;
									}
								} else {
									floodFill(x, y);

									for (int i = 0; i < n; i++) {
										for (int j = 0; j < n; j++) {
											if (matrixOpened[i][j] == 1) {
												buttons[i][j]
														.setBackground(new Color(
																204, 204, 204));
											}

											if (checkForMinusOne(matrixAround,
													i, j, n) == -1) {
												buttons[i][j]
														.setBackground(new Color(
																204, 204, 204));
												switch (matrixAround[i][j]) {
												case 1:
													buttons[i][j].setForeground(new Color(0, 0, 255));
													break;
												case 2:
													buttons[i][j].setForeground(new Color(10, 209, 27));
													break;
												case 3:
													buttons[i][j].setForeground(new Color(255, 0, 0));
													break;
												case 4:
													buttons[i][j].setForeground(new Color(0, 0, 128));
													break;
												case 5:
													buttons[i][j].setForeground(new Color(128, 0, 0));
													break;
												case 6:
													buttons[i][j].setForeground(new Color(0, 128, 0));
													break;
												case 7:
													buttons[i][j].setForeground(new Color(128, 0, 128));
													break;
												case 8:
													buttons[i][j].setForeground(new Color(0, 0, 0));
													break;
												}
												matrixOpened[i][j] = 1;
												
												buttons[i][j]
														.setText(matrixAround[i][j]
																+ "");
											}

										}
									}

								}
							}
						}

						if (checkForWin() == 1) {
							String message = "YOU WIN";
							String[] characters = message.split(""); 
							int height = n / 2;
							int width  = (n - 7) / 2;
							
							for (int i=0; i<message.length(); i++) {
								buttons[height][width + i].setForeground(new Color(255, 255, 255));
								buttons[height][width + i].setBackground(new Color(0, 0, 0));
								buttons[height][width + i].setIcon(null);
								buttons[height][width + i].setText(characters[i+1]);
							}
						}
					}
				});

				jBoardPanel.add(btn);
			}
		}

		getContentPane().add(jTopPanel, BorderLayout.NORTH);
		getContentPane().add(jBoardPanel, BorderLayout.CENTER);
	}

}
