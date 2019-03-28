package com.mitocode.service;

import com.mitocode.dto.VentaDTO;
import com.mitocode.model.Venta;

public interface IVentaService extends ICRUD<Venta>{
	public Integer registrarTransaccional(VentaDTO venta);
}
