package edu.ucacue.factura.controlador;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.ucacue.factura.infraestructura.repositorio.PersonaRepository;
import edu.ucacue.factura.modelo.Persona;

@Controller
public class Principal {

	@Autowired
	PersonaRepository personaRepository;

	public Principal() {

	}

	public void GUI() {
		Scanner entradaEscaner = new Scanner(System.in);
		int entradaTeclado;
		do {
			System.out.println("**************MENÚ PRINCIPAL*********************");
			System.out.println(" ");
			System.out.println("1. Ingresar Persona ");
			System.out.println("2. Modificar Persona ");
			System.out.println("3. Eliminar Persona ");
			System.out.println("4. Listar Personas ");
			System.out.println("5. Salir ");
			entradaTeclado = entradaEscaner.nextInt();
			switch (entradaTeclado) {
			case 1:
				//Gererar el objeto Persona pidiendo los datos por teclado
				System.out.println("Ingrese el nombre: ");
				String nombre= entradaEscaner.next();
				System.out.println("Ingrese el apellido: ");
				String apellido= entradaEscaner.next();
				System.out.println("Ingrese la cédula: ");
				String cedula= entradaEscaner.next();
				System.out.println("Ingrese el teléfono: ");
				String telefono= entradaEscaner.next();
				
				Persona p = new Persona( nombre, apellido, telefono, cedula);
				personaRepository.save(p);
				
				break;
			case 2:
				//1 Pedir al usuario que persona quiero modificar "Pedir la cédula"
				//2 ENcontrar a la persona por el número de la cédula
				//3 modificar y cambiar los datos
				break;
			case 3:
				try {
				System.out.println("Ingrese el número de cédula que desea eliminar: ");
				String cedulaBuscar= entradaEscaner.next();
				Persona personaRecuperada= personaRepository.findByCedula(cedulaBuscar);
				personaRepository.delete(personaRecuperada);
				//1 Pedir al usuario que persona quiero modificar "Pedir la cédula"
				//2 ENcontrar a la persona por el número de la cédula
				//3 Eliminar
				}catch(Exception e) {System.out.println("Existe un problema con la cédula ingresada ");}
				break;
			case 4:
					//Buscar todas las personas que están en la BD e imprimir os resultados
				
				List<Persona> personas= personaRepository.findAll();
				System.out.println("Cédula|"+ "\t"+ "|Nombre|"+"\t" + "|Apellido|");
				for (Persona persona : personas) {
					System.out.println(persona.getCedula()+" "+ persona.getNombre()+" " + persona.getApellido());
				}
				break;
			case 5:
				System.exit(0);	
				break;

			default:
				break;
			}
		} while (entradaTeclado != 5);

	}



}
