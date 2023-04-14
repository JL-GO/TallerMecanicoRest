package spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dto.MecanicoDto;
import spring.dto.MecanicoDtoCreate;
import spring.entity.Aparcamiento;
import spring.entity.Mecanico;
import spring.repository.AparcamientoRepository;
import spring.repository.MecanicoRepository;

@Service
public class MecanicoService {

	@Autowired
	MecanicoRepository mecanicoRepository;

	@Autowired
	AparcamientoRepository aparacamientoRepository;

	public List<MecanicoDto> findAll() {

		List<Mecanico> mecanicos = mecanicoRepository.findAll();
		List<MecanicoDto> dtos = new ArrayList<MecanicoDto>();

		for (Mecanico mec : mecanicos) {
			MecanicoDto temp = new MecanicoDto();
			BeanUtils.copyProperties(mec, temp);
			if(mec.getAparcamiento()!=null) {
				temp.setIdAparcamiento(mec.getAparcamiento().getId());
			}
			dtos.add(temp);
		}
		return dtos;
	}

	public MecanicoDto findOne(String dni) {

		Mecanico mecanico = mecanicoRepository.findById(dni).get();
		MecanicoDto dto = new MecanicoDto();
		BeanUtils.copyProperties(mecanico, dto);
		if(mecanico.getAparcamiento()!=null) {
			dto.setIdAparcamiento(mecanico.getAparcamiento().getId());
		}
		return dto;
	}

	public void create(MecanicoDtoCreate mecanico) {

		Mecanico entidad = new Mecanico();
		BeanUtils.copyProperties(mecanico, entidad);
		mecanicoRepository.save(entidad);
	}



	public void update(MecanicoDtoCreate mecanico) {

		Mecanico entidad = mecanicoRepository.findById(mecanico.getDni()).get();
		BeanUtils.copyProperties(mecanico, entidad);
		mecanicoRepository.save(entidad);

	}

	public void quitarPlaza(String dni) {
		Aparcamiento aparcamiento = aparacamientoRepository.findById(mecanicoRepository.findById(dni).get().getAparcamiento().getId()).get();	
		aparcamiento.setMecanico(null);
		aparacamientoRepository.saveAndFlush(aparcamiento);
		
		Mecanico mecanico = mecanicoRepository.findById(dni).get();
		mecanico.setAparcamiento(null);
		mecanicoRepository.saveAndFlush(mecanico);
		
	}
	
	public void asignarPlaza(String dni, Integer idPlaza) {

		Mecanico mecanico = mecanicoRepository.findById(dni).get();
		Aparcamiento aparcamiento = aparacamientoRepository.findById(idPlaza).get();
		aparcamiento.setMecanico(mecanico);
		aparacamientoRepository.save(aparcamiento);
		
	}
	
	public void delete(String dni) {

		mecanicoRepository.deleteById(dni);
	}
}
