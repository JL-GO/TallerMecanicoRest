package spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dto.ReparacionDto;
import spring.dto.ReparacionDtoCreate;
import spring.dto.ReparacionDtoFiltrado;
import spring.dto.ReparacionDtoModificar;
import spring.entity.Reparacion;
import spring.repository.CocheRepository;
import spring.repository.MecanicoRepository;
import spring.repository.ReparacionRepository;

@Service
public class ReparacionService {

	@Autowired
	ReparacionRepository reparacionRepository;
	
	@Autowired
	CocheRepository cocheRepository;
	
	@Autowired
	MecanicoRepository mecanicoRepository;
	
	public List<ReparacionDto> ventasOrderByPriceDesc() {
		List<Reparacion> reparaciones = reparacionRepository.findAllByOrderByPrecioDesc();
		List<ReparacionDto> dtos = new ArrayList<ReparacionDto>();
		
		for (Reparacion rep : reparaciones) {
			ReparacionDto temp = new ReparacionDto();
			BeanUtils.copyProperties(rep, temp);
			temp.setDniMecanico(rep.getMecanico().getDni());
			temp.setMatriculaCoche(rep.getCoche().getMatricula());
			dtos.add(temp);
		}
		return dtos;
	}
	
	
	public List<ReparacionDto> findAll() {

		List<Reparacion> reparaciones = reparacionRepository.findAll();
		List<ReparacionDto> dtos = new ArrayList<ReparacionDto>();

		for (Reparacion rep : reparaciones) {
			ReparacionDto temp = new ReparacionDto();
			BeanUtils.copyProperties(rep, temp);
			temp.setDniMecanico(rep.getMecanico().getDni());
			temp.setMatriculaCoche(rep.getCoche().getMatricula());
			dtos.add(temp);
		}
		return dtos;
	}
	
	public List<ReparacionDtoFiltrado> findByCoche(String matricula) {

		List<Reparacion> reparaciones = reparacionRepository.findAllByCoche(cocheRepository.findById(matricula).get());
		List<ReparacionDtoFiltrado> dtos = new ArrayList<ReparacionDtoFiltrado>();

		for (Reparacion rep : reparaciones) {
			ReparacionDtoFiltrado temp = new ReparacionDtoFiltrado();
			BeanUtils.copyProperties(rep, temp);
			dtos.add(temp);
		}
		return dtos;
	}
	
	public List<ReparacionDtoFiltrado> findByMecanico(String dni) {

		List<Reparacion> reparaciones = reparacionRepository.findAllByMecanico(mecanicoRepository.findById(dni).get());
		List<ReparacionDtoFiltrado> dtos = new ArrayList<ReparacionDtoFiltrado>();

		for (Reparacion rep : reparaciones) {
			ReparacionDtoFiltrado temp = new ReparacionDtoFiltrado();
			BeanUtils.copyProperties(rep, temp);
			dtos.add(temp);
		}
		return dtos;
	}
	
	public ReparacionDto findOne(Integer id) {

		Reparacion rep = reparacionRepository.findById(id).get();
		ReparacionDto dto = new ReparacionDto();
		BeanUtils.copyProperties(rep, dto);
		dto.setDniMecanico(rep.getMecanico().getDni());
		dto.setMatriculaCoche(rep.getCoche().getMatricula());
		return dto;
	}
	
	public void create(ReparacionDtoCreate rep) {

		//busca todos los registros para que actualice los id ocupados
		reparacionRepository.findAll();
		Reparacion entidad = new Reparacion();
		BeanUtils.copyProperties(rep, entidad);
		entidad.setMecanico(mecanicoRepository.findById(rep.getDniMecanico()).get());
		entidad.setCoche(cocheRepository.findById(rep.getMatriculaCoche()).get());
		reparacionRepository.save(entidad);
	}
	
	public void update(ReparacionDtoModificar rep) {

		Reparacion entidad = reparacionRepository.findById(rep.getId()).get();
		BeanUtils.copyProperties(rep, entidad);
		reparacionRepository.save(entidad);

	}

	public void delete(Integer id) {

		reparacionRepository.deleteById(id);
	}

}
