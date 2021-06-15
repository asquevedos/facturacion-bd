package edu.ucacue.factura.controlador.factura;


import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.ucacue.factura.infraestructura.repositorio.FacturaCabeceraRepository;
import edu.ucacue.factura.infraestructura.repositorio.PersonaRepository;
import edu.ucacue.factura.modelo.DetalleFactura;
import edu.ucacue.factura.modelo.FacturaCabecera;
import edu.ucacue.factura.modelo.Persona;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

@Controller
public class FacturaUI extends JInternalFrame {
	
	private JTextField txtNumFactuta;
	private JTextField txtCedula;
	private JLabel lblFecha;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblCedula ;
	private Persona p;


	@Autowired
	PersonaRepository personaRepositorio;
	
	@Autowired
	FacturaCabeceraRepository fCR;
	
	List<DetalleFactura> detallesFacturas; 

	FacturaCabecera fc;
	private JButton btnGuardar;
	

	public FacturaUI() {
		detallesFacturas=new ArrayList<>();
		fc=new FacturaCabecera();
		setBounds(100, 100, 617, 340);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sistema de Facturación Integral");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(56, 11, 353, 45);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Número Factura");
		lblNewLabel_1.setBounds(398, 54, 85, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtNumFactuta = new JTextField();
		txtNumFactuta.setBounds(493, 51, 86, 20);
		getContentPane().add(txtNumFactuta);
		txtNumFactuta.setColumns(10);
		
		lblFecha = new JLabel("");
		lblFecha.setBounds(419, 11, 181, 14);
		getContentPane().add(lblFecha);
		lblFecha.setText(new Date().toString());
		
		txtCedula = new JTextField();
		txtCedula.setBounds(84, 67, 86, 20);
		getContentPane().add(txtCedula);
		txtCedula.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cedula=txtCedula.getText();
				p = personaRepositorio.findByCedula(cedula); 
				
				lblCedula.setText(p.getCedula());
				lblNombre.setText(p.getNombre());
				lblApellido.setText(p.getApellido());
				
				
			}
		});
		btnBuscar.setBounds(187, 67, 85, 20);
		getContentPane().add(btnBuscar);
		
		JLabel lblNewLabel_2 = new JLabel("Cédula");
		lblNewLabel_2.setBounds(10, 70, 64, 14);
		getContentPane().add(lblNewLabel_2);
		
		lblNombre = new JLabel("");
		lblNombre.setBounds(83, 116, 112, 14);
		getContentPane().add(lblNombre);
		
		lblApellido = new JLabel("");
		lblApellido.setBounds(195, 116, 100, 14);
		getContentPane().add(lblApellido);
		
		lblCedula = new JLabel("");
		lblCedula.setBounds(305, 116, 111, 14);
		getContentPane().add(lblCedula);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.setPersona(p);
				fc.setNumeroFactura(Integer.parseInt(txtNumFactuta.getText()));
				fc.setFechaEmision(new Date());
				fc.setDetallesFacturas(detallesFacturas);
				fCR.save(fc);
			}
		});
		btnGuardar.setBounds(263, 276, 89, 23);
		getContentPane().add(btnGuardar);

	}
}
