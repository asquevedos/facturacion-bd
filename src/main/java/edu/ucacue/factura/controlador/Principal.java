package edu.ucacue.factura.controlador;

import java.util.List;

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

	public void insertarPersona()
	{
		/*try {
		Persona p1= new Persona(2, "Sebastian", "Quevedo", "3423423", "534534534");
		System.out.print("hola");
		personaRepository.save(p1);
		}catch (Exception e) {
			System.out.println("Existe un errror el momento de guardar lapersoma");
		}*/
		
		/*List<Persona> personas = personaRepository.findAll();
		
		for (Persona p : personas) {
		
			System.out.println(p.getNombre() + " " + p.getApellido());
			
		}*/
		
		Persona encontrado= personaRepository.findByCedula("534534534");
		System.out.println(encontrado);
	}

}
