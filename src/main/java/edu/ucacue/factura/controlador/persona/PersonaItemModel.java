package edu.ucacue.factura.controlador.persona;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import edu.ucacue.factura.modelo.Persona;


public class PersonaItemModel extends AbstractTableModel {


	private static final long serialVersionUID = 1L;
	private List<Persona> personas;
	private static final String[] COLUMN_NAMES = {"Nombre", "Apellido","Telefono", "Cédula"};

	public PersonaItemModel(List<Persona> personas) {

		this.personas = personas;

	}

	@Override
	public int getRowCount() {
		return personas.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Object value = "??";
		Persona persona = personas.get(rowIndex);
		switch (columnIndex) {
		case 0:
			value = persona.getNombre();
			break;
		case 1:
			value = persona.getApellido();
			break;
		case 2:
			value = persona.getTelefono();
			break;
		
		case 3:
			value = persona.getCedula();
			break;
		}

		return value;

	}

	@Override
    public Class<?> getColumnClass(int columnIndex) {
        return Persona.class;
    }
	
    //the column header
    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

	/*
	 * Override this if you want the values to be editable...
	 * 
	 * @Override public void setValueAt(Object aValue, int rowIndex, int
	 * columnIndex) { //.... }
	 */

	/**
	 * This will return the user at the specified row...
	 * 
	 * @param row
	 * @return
	 */
	public Persona getPersonaAt(int row) {
		return personas.get(row);
	}

}
