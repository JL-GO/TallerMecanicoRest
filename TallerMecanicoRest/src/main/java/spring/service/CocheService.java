package spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dto.CocheDto;
import spring.entity.Coche;
import spring.repository.CocheRepository;

@Service
public class CocheService {


	@Autowired
	CocheRepository cocheRepository;

	public List<CocheDto> findAll() {

		List<Coche> coches = cocheRepository.findAll();
		List<CocheDto> dtos = new ArrayList<CocheDto>();

		for (Coche coche : coches) {
			CocheDto temp = new CocheDto();
			BeanUtils.copyProperties(coche, temp);
			dtos.add(temp);
		}
		return dtos;
	}
	
	public CocheDto findOne(String matricula) {

		Coche coche = cocheRepository.findById(matricula).get();
		CocheDto dto = new CocheDto();
		BeanUtils.copyProperties(coche, dto);
		return dto;
	}
	
	public void create(CocheDto coche) {

		Coche entidad = new Coche();
		BeanUtils.copyProperties(coche, entidad);
		cocheRepository.save(entidad);
	}
	
	public void update(CocheDto coche) {

		Coche entidad = cocheRepository.findById(coche.getMatricula()).get();
		BeanUtils.copyProperties(coche, entidad);
		cocheRepository.save(entidad);

	}

	public void delete(String marca) {

		cocheRepository.deleteById(marca);
	}
}
