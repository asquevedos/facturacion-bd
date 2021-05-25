package edu.ucacue.factura.infraestructura.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ucacue.factura.modelo.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {

	Persona findByCedula(String cedula);
}
