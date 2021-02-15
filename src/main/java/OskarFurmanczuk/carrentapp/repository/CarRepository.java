package OskarFurmanczuk.carrentapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import OskarFurmanczuk.carrentapp.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{
	
	public List<Car> findByClientId(String id);

}
