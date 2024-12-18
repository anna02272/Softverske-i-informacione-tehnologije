package pogled;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

import dogadjaj.Observer;
import dogadjaj.UpdateEvent;
import repozitorijum.DnevnaTemperaturaRepozitorijum;

public class DnevneTemperatureTabela extends JTable implements Observer {

	private DnevnaTemperaturaRepozitorijum dnevnaTemperaturaRepozitorijum;

	public DnevneTemperatureTabela(DnevnaTemperaturaRepozitorijum dnevnaTemperaturaRepozitorijum) {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new ApstraktnaTabelaDnevnihTemperatura(dnevnaTemperaturaRepozitorijum));

		this.dnevnaTemperaturaRepozitorijum = dnevnaTemperaturaRepozitorijum;
		this.dnevnaTemperaturaRepozitorijum.addObserver(this);
	}

	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component c = super.prepareRenderer(renderer, row, column);
		if (isRowSelected(row)) {
			c.setBackground(Color.LIGHT_GRAY);
		} else {
			c.setBackground(Color.WHITE);
		}
		return c;
	}

	public DnevnaTemperaturaRepozitorijum getDnevnaTemperaturaRepozitorijum() {
		return dnevnaTemperaturaRepozitorijum;
	}

	@Override
	public void updatePerformed(UpdateEvent event) {
		ApstraktnaTabelaDnevnihTemperatura model = (ApstraktnaTabelaDnevnihTemperatura) this.getModel();
		model.fireTableDataChanged();
		GlavniProzor.getInstance().validate();
	}

}
