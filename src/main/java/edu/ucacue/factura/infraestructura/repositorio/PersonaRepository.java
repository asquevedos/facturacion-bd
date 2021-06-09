package edu.ucacue.factura.infraestructura.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.ucacue.factura.modelo.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {

	Persona findByCedula(String cedula);

	List<Persona> findByNombreLike(String nombre);

	List<Persona> findByNombreAndApellido(String nombre, String apellido);

	List<Persona> findByNombre(String nombre);

	// JPQL
	@Query("select p from Persona p where p.nombre =  :nombre")
	List<Persona> buscarPorNombres(@Param("nombre") String nombre);

	@Query("select p from Persona p where p.apellido = :apellido")
	List<Persona> buscarPorApellido(@Param("apellido") String apellido);

	@Query("select p from Persona p where p.apellido = :apellido and p.nombre = :nombre")
	List<Persona> buscarPorApellidoYnombre(@Param("apellido") String apellido, @Param("nombre") String nombre);
	
	@Query("select p from Persona p where p.nombre like :nombre")
	List<Persona> buscarPorNombreLike(@Param("nombre") String nombre);

	@Query("select p from Persona p where p.apellido like %:apellido%")
	List<Persona> buscarPorApellidoLike(@Param("apellido") String apellido);
	
	

}
