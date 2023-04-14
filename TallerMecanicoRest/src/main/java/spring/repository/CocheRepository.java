package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.entity.Coche;

@Repository
public interface CocheRepository extends JpaRepository<Coche, String>{

}
