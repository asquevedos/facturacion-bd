package edu.ucacue.factura.infraestructura.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;

import edu.ucacue.factura.modelo.FacturaCabecera;

public interface FacturaCabeceraRepository extends JpaRepository<FacturaCabecera, Integer> {

}
