package spring.dto;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MecanicoDtoCreate {

	@Size(min = 8, max = 10)
	private String dni;

	private String nombre;

	private String ciudad;

	private Double salario;

}
