package spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import spring.dto.ReparacionDto;
import spring.dto.ReparacionDtoCreate;
import spring.dto.ReparacionDtoFiltrado;
import spring.dto.ReparacionDtoModificar;
import spring.service.ReparacionService;

@RestController
@RequestMapping("/reparaciones")
public class ReparacionController {

	@Autowired
	ReparacionService reparacionService;

	@GetMapping
	public ResponseEntity<List<ReparacionDto>> findAll() {

		try {
			return new ResponseEntity<List<ReparacionDto>>(reparacionService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/vehiculo/{matricula}")
	public ResponseEntity<List<ReparacionDtoFiltrado>> findAllByCoche(@PathVariable String matricula) {

		try {
			return new ResponseEntity<List<ReparacionDtoFiltrado>>(reparacionService.findByCoche(matricula),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/mecanico/{dni}")
	public ResponseEntity<List<ReparacionDtoFiltrado>> findAllByMecanico(@PathVariable String dni) {

		try {
			return new ResponseEntity<List<ReparacionDtoFiltrado>>(reparacionService.findByMecanico(dni),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReparacionDto> findReparacion(@PathVariable Integer id) {

		try {
			return new ResponseEntity<ReparacionDto>(reparacionService.findOne(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> createReparacion(@RequestBody ReparacionDtoCreate rep) {

		try {
			reparacionService.create(rep);
			return new ResponseEntity<String>("Reparación añadida", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error al añadir", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updatePrecio(@RequestBody ReparacionDtoModificar rep, @PathVariable Integer id) {
		try {
			if (id == rep.getId()) {
				reparacionService.update(rep); 
				return new ResponseEntity<String>("Precio modificado", HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {}

		return new ResponseEntity<String>("Error al modificar", HttpStatus.BAD_REQUEST);

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteReparacion(@PathVariable Integer id) {

		try {
			reparacionService.delete(id);
			return new ResponseEntity<String>("Reparación borrada", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error al borrar", HttpStatus.BAD_REQUEST);
		}
	}

}
