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

import spring.dto.MecanicoDto;
import spring.dto.MecanicoDtoCreate;
import spring.service.AparcamientoService;
import spring.service.MecanicoService;

@RestController
@RequestMapping("/mecanicos")
public class MecanicoController {
	
	@Autowired
	MecanicoService mecanicoService;
	
	@Autowired
	AparcamientoService aparcamientoService;

	@GetMapping
	public ResponseEntity<List<MecanicoDto>> findAll() {

		try {
			return new ResponseEntity<List<MecanicoDto>>(mecanicoService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	} 
	
	@GetMapping("/{dni}")
	public ResponseEntity<MecanicoDto> findMecanico(@PathVariable String dni) {

		try {
			return new ResponseEntity<MecanicoDto>(mecanicoService.findOne(dni), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> createMecanico(@RequestBody @Valid MecanicoDtoCreate mecanico) {

		try {
			mecanicoService.create(mecanico);
			return new ResponseEntity<String>("Mecanico añadido", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error al añadir", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/{dni}/{idPlaza}")
	public ResponseEntity<?> asignarPlaza(@PathVariable String dni, Integer idPlaza ) {

		try {
			mecanicoService.asignarPlaza(dni,idPlaza);;
			return new ResponseEntity<String>("Plaza asignada", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error al asignar", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{dni}")
	public ResponseEntity<?> updateMecanico(@RequestBody @Valid MecanicoDtoCreate mecanico, @PathVariable String dni) {
		try {
			if (dni.equals(mecanico.getDni())) {
				mecanicoService.update(mecanico); 
				return new ResponseEntity<String>("Mecanico modificado", HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {}

		return new ResponseEntity<String>("Error al modificar", HttpStatus.BAD_REQUEST);

	}

	@DeleteMapping("/{dni}")
	public ResponseEntity<?> deleteMecanico(@PathVariable String dni) {

		try {
			if(mecanicoService.findOne(dni).getIdAparcamiento() != null) {
				mecanicoService.quitarPlaza(dni);		
			}
			mecanicoService.delete(dni);
			return new ResponseEntity<String>("Mecanico borrado", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error al borrar", HttpStatus.BAD_REQUEST);
		}
	}
}
