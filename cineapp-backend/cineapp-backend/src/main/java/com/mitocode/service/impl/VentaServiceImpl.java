package com.mitocode.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.dao.IVentaComidaDAO;
import com.mitocode.dao.IVentaDAO;
import com.mitocode.dto.VentaDTO;
import com.mitocode.model.Venta;
import com.mitocode.service.IVentaService;


@Service
public class VentaServiceImpl implements IVentaService{

	@Autowired
	private IVentaDAO dao;
	
	
	@Autowired
	private IVentaComidaDAO vDao;
	
	
	@Override
	public Venta registrar(Venta obj) {
		obj.getDetalle().forEach(det->det.setVenta(obj));
		return dao.save(obj);
	}

	@Override
	public Venta modificar(Venta obj) {
		return dao.save(obj);
	}

	@Override
	public List<Venta> listar() {
		return dao.findAll();
	}

	@Override
	public Venta leer(Integer id) {
		Optional<Venta> op = dao.findById(id);
    	return op.isPresent() ? op.get() : new Venta();
	}

	@Override
	public void eliminar(Integer id) {
		dao.deleteById(id);
		
	}


	@Transactional
	@Override
	public Integer registrarTransaccional(VentaDTO ventaDTO) {
		ventaDTO.getVenta().getDetalle().forEach(det -> det.setVenta(ventaDTO.getVenta()));
		dao.save(ventaDTO.getVenta());
		
		ventaDTO.getLstComidas().forEach( c -> vDao.registrar(ventaDTO.getVenta().getIdVenta() , c.getIdComida()));
		return 1;
	}
	
//	@Transactional
//	@Override
//	public Integer registrarTransaccional(VentaDTO ventaDTO) {
//		ventaDTO.getVenta().getDetalle().forEach(det -> det.setVenta(ventaDTO.getVenta()));
//		dao.save(ventaDTO.getVenta());
//		
//		ventaDTO.getLstComidas().forEach( c -> vDao.registrar(ventaDTO.getVenta().getIdVenta() , c.getIdComida()));
//		return 1;
//	}



}
