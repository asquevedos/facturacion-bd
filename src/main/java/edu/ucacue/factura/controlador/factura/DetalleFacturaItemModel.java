package edu.ucacue.factura.controlador.factura;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import edu.ucacue.factura.modelo.DetalleFactura;


public class DetalleFacturaItemModel extends AbstractTableModel {


	private static final long serialVersionUID = 1L;
	private List<DetalleFactura> detallesFacturas;
	private static final String[] COLUMN_NAMES = {"id","Producto", "Precio","Cantidad", "Valor de Venta"};

	public DetalleFacturaItemModel(List<DetalleFactura> detallesFacturas) {

		this.detallesFacturas = detallesFacturas;

	}

	@Override
	public int getRowCount() {
		return detallesFacturas.size();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Object value = "??";
		DetalleFactura dF = detallesFacturas.get(rowIndex);
		switch (columnIndex) {
		case 0:
			value = dF.getIdFactura();
			break;
		case 1:
			value = dF.getProducto().getNombre();
			break;
		case 2:
			value = dF.getProducto().getPrecio();
			break;
		case 3:
			value = dF.getCantidad();
			break;
		
		case 4:
			value =dF.getValorVenta();
			break;
		}

		return value;

	}

	@Override
    public Class<?> getColumnClass(int columnIndex) {
        return DetalleFactura.class;
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
	public DetalleFactura getDetalleFacturaAt(int row) {
		return detallesFacturas.get(row);
	}

}
