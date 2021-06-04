package edu.ucacue.factura.controlador;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.ucacue.factura.infraestructura.repositorio.PersonaRepository;
import edu.ucacue.factura.modelo.Persona;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Controller
public class PersonaModificarUI extends JInternalFrame {
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtCedulaM;
	private JButton btnGuardar;

	@Autowired
	PersonaRepository personaRepositorio;
	
	Persona persona;
	public PersonaModificarUI() {
		this.setMaximizable(true); // maximize
		this.setIconifiable(true); // set minimize
		this.setClosable(true); // set closed
		this.setResizable(true); // set resizable
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingrese cédula");
		lblNewLabel.setBounds(31, 23, 76, 14);
		getContentPane().add(lblNewLabel);
		
		txtCedula = new JTextField();
		txtCedula.setBounds(117, 20, 86, 20);
		getContentPane().add(txtCedula);
		txtCedula.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cedula = txtCedula.getText();
				persona=personaRepositorio.findByCedula(cedula);
				ponerDatos(persona);
				btnGuardar.setEnabled(true);
				
			}
		});
		btnBuscar.setBounds(241, 19, 89, 23);
		getContentPane().add(btnBuscar);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(22, 60, 402, 14);
		getContentPane().add(horizontalStrut);
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setBounds(22, 85, 368, 150);
		getContentPane().add(contentPane);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(75, 8, 41, 14);
		contentPane.add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(206, 5, 86, 20);
		contentPane.add(txtNombre);
		
		JLabel lblNewLabel_1_1 = new JLabel("Apellido");
		lblNewLabel_1_1.setBounds(77, 33, 37, 14);
		contentPane.add(lblNewLabel_1_1);
		
		txtApellido = new JTextField();
		txtApellido.setText("");
		txtApellido.setColumns(10);
		txtApellido.setBounds(204, 30, 86, 20);
		contentPane.add(txtApellido);
		
		JLabel lblNewLabel_2 = new JLabel("Teléfono");
		lblNewLabel_2.setBounds(75, 58, 42, 14);
		contentPane.add(lblNewLabel_2);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(207, 55, 86, 20);
		contentPane.add(txtTelefono);
		
		JLabel lblNewLabel_3 = new JLabel("cédula");
		lblNewLabel_3.setBounds(80, 83, 31, 14);
		contentPane.add(lblNewLabel_3);
		
		txtCedulaM = new JTextField();
		txtCedulaM.setColumns(10);
		txtCedulaM.setBounds(201, 80, 86, 20);
		contentPane.add(txtCedulaM);
		
	btnGuardar = new JButton("Guardar");
	btnGuardar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String nombre = txtNombre.getText();
			String apellido = txtApellido.getText();
			String telefono = txtTelefono.getText();
			String cedula = txtCedula.getText();
			Persona personaModificada = new Persona(nombre, apellido, telefono, cedula);
			personaModificada.setId(persona.getId());
			personaRepositorio.save(personaModificada);
		}
	});
		btnGuardar.setEnabled(false);
		btnGuardar.setBounds(148, 105, 71, 23);
		contentPane.add(btnGuardar);

	}
	
	public void ponerDatos(Persona persona)
	{
		txtNombre.setText(persona.getNombre());
		txtApellido.setText(persona.getApellido());
		txtTelefono.setText(persona.getTelefono());
		txtCedulaM.setText(persona.getCedula());
	}
}
