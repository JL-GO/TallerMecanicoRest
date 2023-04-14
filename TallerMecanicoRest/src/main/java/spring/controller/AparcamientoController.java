package spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.dto.AparcamientoDto;
import spring.dto.AparcamientoDtoCreate;
import spring.service.AparcamientoService;

@RestController
@RequestMapping("/parking")
public class AparcamientoController {

	@Autowired
	AparcamientoService aparcamientoService;
	
	@GetMapping
	public ResponseEntity<?> findAll() {

		try {
			return new ResponseEntity<List<AparcamientoDto>>(aparcamientoService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AparcamientoDto> findAparcamiento(@PathVariable Integer id) {

		try {
			return new ResponseEntity<AparcamientoDto>(aparcamientoService.findOne(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> createAparcamiento(@RequestBody AparcamientoDtoCreate aparcamiento) {

		try {
			aparcamientoService.create(aparcamiento);
			return new ResponseEntity<String>("Aparcamiento creado", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error al crear", HttpStatus.BAD_REQUEST);
		}
	}
	
}
