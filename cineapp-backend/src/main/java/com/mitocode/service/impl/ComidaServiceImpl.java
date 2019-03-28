package com.mitocode.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mitocode.dao.IComidaDAO;
import com.mitocode.model.Comida;
import com.mitocode.service.IComidaService;


@Service
public class ComidaServiceImpl implements IComidaService{

	@Autowired
	private IComidaDAO dao;
	
	@Override
	public Comida registrar(Comida obj) {
		//obj.getDetalle().forEach(det->det.setComida(obj));
		return dao.save(obj);
	}

	@Override
	public Comida modificar(Comida obj) {
		return dao.save(obj);
	}

	@Override
	public List<Comida> listar() {
		return dao.findAll();
	}

	@Override
	public Comida leer(Integer id) {
		Optional<Comida> op = dao.findById(id);
    	return op.isPresent() ? op.get() : new Comida();
	}

	@Override
	public void eliminar(Integer id) {
		dao.deleteById(id);
		
	}


}
