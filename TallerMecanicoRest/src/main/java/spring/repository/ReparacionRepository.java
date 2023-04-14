package spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.entity.Coche;
import spring.entity.Mecanico;
import spring.entity.Reparacion;

@Repository
public interface ReparacionRepository extends JpaRepository<Reparacion, Integer>{

	public List<Reparacion> findAllByCoche(Coche coche);
	
	public List<Reparacion> findAllByMecanico(Mecanico mecanico);
	
	public List<Reparacion> findAllByOrderByPrecioDesc();
}

