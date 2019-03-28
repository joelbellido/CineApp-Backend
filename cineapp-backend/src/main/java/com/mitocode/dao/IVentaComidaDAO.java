package com.mitocode.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.VentaComida;

public interface IVentaComidaDAO extends JpaRepository<VentaComida, Integer>{

	@Modifying
	@Query(value = "INSERT INTO venta_comida(id_venta, id_comida) VALUES (:idVenta, :idComida)", nativeQuery = true)
	Integer registrar(@Param("idVenta") Integer idVenta, @Param("idComida") Integer idComida);

}
