package pogled;

import javax.swing.table.AbstractTableModel;

import repozitorijum.DnevnaTemperaturaRepozitorijum;

public class ApstraktnaTabelaDnevnihTemperatura extends AbstractTableModel {

	private DnevnaTemperaturaRepozitorijum dnevnaTemperaturaRepozitorijum;

	public ApstraktnaTabelaDnevnihTemperatura(DnevnaTemperaturaRepozitorijum dnevnaTemperaturaRepozitorijum) {
		this.dnevnaTemperaturaRepozitorijum = dnevnaTemperaturaRepozitorijum;
	}

	@Override
	public int getRowCount() {
		return dnevnaTemperaturaRepozitorijum.getDnevneTemperature().size();
	}

	@Override
	public int getColumnCount() {
		return dnevnaTemperaturaRepozitorijum.getColumnCount();
	}

	@Override
	public String getColumnName(int column) {
		return dnevnaTemperaturaRepozitorijum.getColumnName(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return dnevnaTemperaturaRepozitorijum.getValueAt(rowIndex, columnIndex);
	}

	public DnevnaTemperaturaRepozitorijum getDnevnaTemperaturaRepozitorijum() {
		return this.dnevnaTemperaturaRepozitorijum;
	}

}
