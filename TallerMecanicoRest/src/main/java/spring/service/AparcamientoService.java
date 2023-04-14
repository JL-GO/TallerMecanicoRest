package spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dto.AparcamientoDto;
import spring.dto.AparcamientoDtoCreate;
import spring.entity.Aparcamiento;
import spring.repository.AparcamientoRepository;
import spring.repository.MecanicoRepository;

@Service
public class AparcamientoService {

	@Autowired
	AparcamientoRepository aparacamientoRepository;
	
	@Autowired
	MecanicoRepository mecanicoRepository;
	
	public List<AparcamientoDto> findAll(){
		
		List<Aparcamiento> aparacamientos = aparacamientoRepository.findAll();
		List<AparcamientoDto> dtos=new ArrayList<AparcamientoDto>();
		
		for (Aparcamiento apa : aparacamientos) {			
			AparcamientoDto temp= new AparcamientoDto();
			BeanUtils.copyProperties(apa, temp);
			if(apa.getMecanico()!=null) {
				temp.setDniMecanico(apa.getMecanico().getDni());
			}
			dtos.add(temp);
		}	
		return dtos;
	}
	
	public AparcamientoDto findOne (Integer id) {
		
		Aparcamiento aparcamiento = aparacamientoRepository.findById(id).get();
		AparcamientoDto dto = new AparcamientoDto();
		BeanUtils.copyProperties(aparcamiento, dto);
		if(aparcamiento.getMecanico()!=null) {
			dto.setDniMecanico(aparcamiento.getMecanico().getDni());
		}
		return dto;	
	}
	
	public void create(AparcamientoDtoCreate aparcamiento) {

		//Uso que busque todos los registros para que actualice los id ocupados
		aparacamientoRepository.findAll();
		Aparcamiento entidad = new Aparcamiento();
		BeanUtils.copyProperties(aparcamiento, entidad);
		aparacamientoRepository.save(entidad);
	}
}
