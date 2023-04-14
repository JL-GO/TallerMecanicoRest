package spring.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mecanicos")
public class Mecanico {
	
	@Id
	@Size(min = 8, max = 10)
	private String dni;
	
	@Column
	private String nombre;
	
	@Column
	private String ciudad;
	
	@Column
	private Double salario;
	
	@OneToMany(mappedBy ="mecanico", cascade = CascadeType.ALL)
	private List<Reparacion> reparaciones;
	
	@Nullable
	@OneToOne(mappedBy = "mecanico", cascade = CascadeType.ALL)
	private Aparcamiento aparcamiento;

}
