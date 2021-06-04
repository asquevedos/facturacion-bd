package edu.ucacue.factura.controlador;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.ucacue.factura.infraestructura.repositorio.PersonaRepository;
import edu.ucacue.factura.modelo.Persona;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

@Controller
public class PersonaUI extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtCedula;
	private JButton btnGuardar;
	
	@Autowired
	PersonaRepository personaRepositorio;




	public PersonaUI() {
		setTitle("Nueva Persona");
		setBackground(Color.LIGHT_GRAY);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 384, 180);
		
		this.setMaximizable(true); // maximize
		this.setIconifiable(true); // set minimize
		this.setClosable(true); // set closed
		this.setResizable(true); // set resizable
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(75, 8, 41, 14);
		contentPane.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(206, 5, 86, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Apellido");
		lblNewLabel_1.setBounds(77, 33, 37, 14);
		contentPane.add(lblNewLabel_1);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(204, 30, 86, 20);
		txtApellido.setText("");
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Teléfono");
		lblNewLabel_2.setBounds(75, 58, 42, 14);
		contentPane.add(lblNewLabel_2);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(207, 55, 86, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("cédula");
		lblNewLabel_3.setBounds(80, 83, 31, 14);
		contentPane.add(lblNewLabel_3);
		
		txtCedula = new JTextField();
		txtCedula.setBounds(201, 80, 86, 20);
		contentPane.add(txtCedula);
		txtCedula.setColumns(10);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(148, 105, 71, 23);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			String nombre = txtNombre.getText();
			String apellido = txtApellido.getText();
			String telefono = txtTelefono.getText();
			String cedula = txtCedula.getText();
			
			Persona p = new Persona(nombre, apellido, telefono, cedula);
			System.out.println(p);
			guardarPersona(p);
			
			}
		});
		contentPane.add(btnGuardar);
	}
	
	public void borrarDatos()
	{
		txtNombre.setText("");
		txtApellido.setText("");
		txtTelefono.setText("");
		txtCedula.setText("");
	}
	
	
	public void guardarPersona(Persona p)
	{
		personaRepositorio.save(p);
		borrarDatos();
	}
}
