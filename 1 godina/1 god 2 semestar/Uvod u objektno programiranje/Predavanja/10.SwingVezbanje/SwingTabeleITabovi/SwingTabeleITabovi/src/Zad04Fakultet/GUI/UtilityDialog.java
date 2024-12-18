package Zad04Fakultet.GUI;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class UtilityDialog {

	public static final int UNOS = 0;
	public static final int IZMENA = 1;
	public static final int PREGLED = 2;
	public static final int PREZMI = 3;
	
	public static GridBagConstraints vratiGBC(int x, int y, int dX, int dY, int poravnanje, Insets in){
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = dX;
		gbc.gridheight = dY;
		gbc.anchor = poravnanje;
		if(in!=null)
			gbc.insets =in;
		return gbc;
	}
	
	public static GridBagConstraints vratiGBCFill(int x, int y, int fill, int wX, int wY, Insets in){
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.fill=fill;
		if(wX!=-1)
			gbc.weightx = 1;
		if(wY!=-1)
			gbc.weighty = 1;
		if(in!=null)
			gbc.insets =in;
		return gbc;
	}
}
