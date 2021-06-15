package edu.ucacue.factura.controlador;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.ucacue.factura.controlador.factura.FacturaUI;
import edu.ucacue.factura.controlador.persona.PersonaUI;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Controller
public class Index extends JFrame {

	//private JPanel contentPane;
	
	JDesktopPane desktopPanel;
	
	@Autowired
	PersonaUI pUI;
	
	@Autowired
	PersonaModificarUI pModificarUI;

	@Autowired
	FacturaUI facturaUI;
	/**
	 * Create the frame.
	 */
	public Index() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		
		desktopPanel= new JDesktopPane();
		getContentPane().add(desktopPanel);
		JMenu mnNewMenu = new JMenu("Persona");
		menuBar.add(mnNewMenu);
		
		JMenuItem miPNuevo = new JMenuItem("Nueva");
		miPNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				pUI.setVisible(true);
				desktopPanel.add(pUI);

			}
		});
		mnNewMenu.add(miPNuevo);
		
		JMenuItem miPModificar = new JMenuItem("Modificar");
		miPModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pModificarUI.setVisible(true);
				desktopPanel.add(pModificarUI);
			}
		});
		mnNewMenu.add(miPModificar);
		
		JMenuItem miPEliminar = new JMenuItem("Eliminar");
		mnNewMenu.add(miPEliminar);
		
		JMenuItem miPListar = new JMenuItem("Listar");
		mnNewMenu.add(miPListar);
		
		JMenu mnNewMenu_1 = new JMenu("Factura");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Nueva Factura");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facturaUI.setVisible(true);
				desktopPanel.add(facturaUI);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem);
		//contentPane = new JPanel();
		//setContentPane(contentPane);
		
		
		
	}
}
