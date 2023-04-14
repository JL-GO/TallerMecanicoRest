package spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AparcamientoDto {

	private Integer id;
	
	private Integer fila;
	
	private String columna;

	private String dniMecanico;
}
