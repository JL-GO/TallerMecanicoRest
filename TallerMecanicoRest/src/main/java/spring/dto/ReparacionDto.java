package spring.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReparacionDto {

	private Integer id;

	private Date fecha;

	private Double precio;

	private String matriculaCoche;

	private String dniMecanico;
}
