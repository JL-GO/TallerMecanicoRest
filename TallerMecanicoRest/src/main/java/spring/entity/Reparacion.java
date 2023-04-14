package spring.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reparaciones")
public class Reparacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	@Temporal(TemporalType.DATE) 
	private Date fecha;
	
	@Column
	private Double precio;

	@ManyToOne(cascade = CascadeType.MERGE)
	private Coche coche;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	private Mecanico mecanico;
	
}
