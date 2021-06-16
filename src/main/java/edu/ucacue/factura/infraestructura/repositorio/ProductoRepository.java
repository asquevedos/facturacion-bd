package edu.ucacue.factura.infraestructura.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;

import edu.ucacue.factura.modelo.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {



}
