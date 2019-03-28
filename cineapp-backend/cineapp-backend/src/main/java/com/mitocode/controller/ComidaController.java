package com.mitocode.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Comida;
import com.mitocode.model.Genero;
import com.mitocode.service.IComidaService;
import com.mitocode.service.IGeneroService;

@RestController
@RequestMapping("/clientes")
public class ComidaController {

	@Autowired
	private IComidaService service;
	
	@GetMapping
	public ResponseEntity<List<Comida>> listar(){
		List<Comida> lista = service.listar();
		return new ResponseEntity<List<Comida>>(lista, HttpStatus.OK);		
	}
	
	@GetMapping(value = "/{id}")
	public Resource<Comida> listarPorId(@PathVariable("id") Integer id){
		
		Comida clin = service.leer(id);
		if(clin.getIdComida() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO : " + id);
		}
		
		Resource<Comida> resource = new Resource<Comida>(clin);
		// /generos/{4}
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));
		resource.add(linkTo.withRel("cliente-resource"));
		
		return resource;
	}
	
	@PostMapping
	public ResponseEntity<Comida> registrar(@RequestBody Comida clin) {
		Comida c = service.registrar(clin);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getIdComida()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Object> modificar(@RequestBody Comida clien) {
		service.modificar(clien);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable("id") Integer id){
		Comida clien = service.leer(id);

		if (clien.getIdComida() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: " + id);
		} else {
			service.eliminar(id);
		}
	}
	
	
	
}
