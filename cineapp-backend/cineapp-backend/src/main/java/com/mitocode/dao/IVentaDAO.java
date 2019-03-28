package com.mitocode.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Venta;

public interface IVentaDAO extends JpaRepository<Venta, Integer>{

}
