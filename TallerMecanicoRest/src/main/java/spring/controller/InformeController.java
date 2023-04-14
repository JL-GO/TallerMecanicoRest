package spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.dto.ReparacionDto;
import spring.service.ReparacionService;

@RestController
@RequestMapping("/informe")
public class InformeController {

	@Autowired
	ReparacionService reparacionService;

	@GetMapping
	public ResponseEntity<List<ReparacionDto>> findAllReparacionesOrderByPriceDesc() {

		try {
			return new ResponseEntity<List<ReparacionDto>>(reparacionService.ventasOrderByPriceDesc(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
