package spring.controller;

import java.util.List;

import javax.validation.Valid;

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

import spring.dto.CocheDto;
import spring.service.CocheService;

@RestController
@RequestMapping("/vehiculos")
public class CocheController {

	@Autowired
	CocheService cocheService;
	
	@GetMapping
	public ResponseEntity<List<CocheDto>> findAll() {

		try {
			return new ResponseEntity<List<CocheDto>>(cocheService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	} 
	
	@GetMapping("/{matricula}")
	public ResponseEntity<CocheDto> findCoche(@PathVariable String matricula) {

		try {
			return new ResponseEntity<CocheDto>(cocheService.findOne(matricula), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> createCoche(@RequestBody @Valid CocheDto coche) {

		try {
			cocheService.create(coche);
			return new ResponseEntity<String>("Coche añadido", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error al añadir", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{matricula}")
	public ResponseEntity<?> updateCoche(@RequestBody @Valid CocheDto coche, @PathVariable String matricula) {
		try {
			if (matricula.equals(coche.getMatricula())) {
				cocheService.update(coche); 
				return new ResponseEntity<String>("Vehículo modificado", HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {}

		return new ResponseEntity<String>("Error al modificar", HttpStatus.BAD_REQUEST);

	}
	
	@DeleteMapping("/{matricula}")
	public ResponseEntity<?> deleteCoche(@PathVariable String matricula) {

		try {
			cocheService.delete(matricula);
			return new ResponseEntity<String>("Vehiculo borrado", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error al borrar", HttpStatus.BAD_REQUEST);
		}
	}
	
}
