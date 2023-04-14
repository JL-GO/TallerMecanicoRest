package spring.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "coches")
public class Coche {
	
	@Id
	@Size(min = 6, max = 10)
	private String matricula;
	
	@Column
	private String marca;
	
	@Column
	private String modelo;
	
	@OneToMany(mappedBy ="coche", cascade = CascadeType.ALL)
	private List<Reparacion> reparaciones;

}
