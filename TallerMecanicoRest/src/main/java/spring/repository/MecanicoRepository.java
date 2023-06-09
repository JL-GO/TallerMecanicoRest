package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.entity.Mecanico;

@Repository
public interface MecanicoRepository extends JpaRepository<Mecanico, String>{

}
