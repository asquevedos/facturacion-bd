package edu.ucacue.factura.controlador.persona;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.ucacue.factura.infraestructura.repositorio.PersonaRepository;
import edu.ucacue.factura.modelo.Persona;

import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.persistence.PostLoad;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.JTabbedPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Controller
public class PersonaUI extends JInternalFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtCedula;
	private JTextField txtApellido;
	private JTextField txtTelefono;

	JButton btnEliminar;
	JButton btnActualizar;

	Persona personaActualizar;

	boolean banderaActualizar = false;

	PersonaItemModel personaModel;

	@Autowired
	PersonaRepository personaRepositorio;
	private JTable tablePersona;
	private JLabel lblNewLabel_2;

	public PersonaUI() {

		this.setMaximizable(true); // maximize
		this.setIconifiable(true); // set minimize
		this.setClosable(true); // set closed
		this.setResizable(true); // set resizable

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Nombre");
		lblNewLabel_3.setBounds(130, 11, 74, 24);
		contentPane.add(lblNewLabel_3);

		txtNombre = new JTextField();
		txtNombre.setBounds(234, 11, 122, 24);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Apellido");
		lblNewLabel_1.setBounds(130, 46, 81, 24);
		contentPane.add(lblNewLabel_1);

		txtApellido = new JTextField();
		txtApellido.setBounds(234, 46, 122, 24);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);

		JLabel lblNewLabel = new JLabel("Cédula");
		lblNewLabel.setBounds(130, 86, 96, 14);
		contentPane.add(lblNewLabel);

		txtCedula = new JTextField();
		txtCedula.setBounds(234, 81, 122, 24);
		contentPane.add(txtCedula);
		txtCedula.setColumns(10);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(235, 155, 121, 35);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Persona p;
				if (banderaActualizar == true) {
					p = personaActualizar;
					p.setApellido(txtApellido.getText());
					p.setNombre(txtNombre.getText());
					p.setTelefono(txtTelefono.getText());
					p.setCedula(txtCedula.getText());
					banderaActualizar = false;

				} else {
					p = new Persona(txtNombre.getText(), txtApellido.getText(), txtTelefono.getText(),txtCedula.getText());
				}
				personaRepositorio.save(p);
				limpiarInterfaz();
				generarTabla();
			}
		});
		contentPane.add(btnGuardar);

		tablePersona = new JTable();
		tablePersona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnEliminar.setEnabled(true);
				btnActualizar.setEnabled(true);
				//System.out.println(tablePersona.getSelectedRow());
				//System.out.println(personaModel.getPersonaAt(tablePersona.getSelectedRow()));
			}
		});

		tablePersona.setBounds(36, 214, 546, 102);
		contentPane.add(tablePersona);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Persona pEliminar = personaModel.getPersonaAt(tablePersona.getSelectedRow());
				personaRepositorio.delete(pEliminar);
				generarTabla();

			}
		});
		btnEliminar.setBounds(193, 352, 89, 23);
		contentPane.add(btnEliminar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				personaActualizar = personaModel.getPersonaAt(tablePersona.getSelectedRow());
				txtCedula.setText(personaActualizar.getCedula());
				txtNombre.setText(personaActualizar.getNombre());
				txtApellido.setText(personaActualizar.getApellido());
				txtTelefono.setText(personaActualizar.getTelefono());
				banderaActualizar = true;
			}
		});
		btnActualizar.setEnabled(false);
		btnActualizar.setBounds(324, 352, 112, 23);
		contentPane.add(btnActualizar);
		
		lblNewLabel_2 = new JLabel("Teléfono");
		lblNewLabel_2.setBounds(130, 117, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(234, 124, 122, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);

	}

	public void limpiarInterfaz() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtCedula.setText("");
		txtTelefono.setText("");
	}

	public void generarTabla() {

		List<Persona> personas = personaRepositorio.findAll();
		
		personaModel = new PersonaItemModel(personas);
		
		tablePersona.setModel(personaModel);
		
		btnEliminar.setEnabled(false);
		btnActualizar.setEnabled(false);
		// personaRepositorio.findAll();
	}
}
