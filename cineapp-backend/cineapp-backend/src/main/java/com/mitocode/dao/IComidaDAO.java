package com.mitocode.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Comida;

public interface IComidaDAO extends JpaRepository<Comida, Integer>{

}
