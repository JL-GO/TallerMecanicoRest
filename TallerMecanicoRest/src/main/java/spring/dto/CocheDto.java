package spring.dto;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CocheDto {

	@Size(min = 6, max = 10)
	private String matricula;

	private String marca;

	private String modelo;
}
