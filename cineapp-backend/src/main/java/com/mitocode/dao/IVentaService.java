package com.mitocode.dao;

import com.mitocode.dto.VentaDTO;
import com.mitocode.model.Venta;
import com.mitocode.service.ICRUD;

public interface IVentaService extends ICRUD<Venta>{
	public Integer registrarTransaccional(VentaDTO venta);

}
