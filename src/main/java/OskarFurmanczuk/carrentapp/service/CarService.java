package OskarFurmanczuk.carrentapp.service;

import java.util.List;

import org.springframework.data.domain.Page;

import OskarFurmanczuk.carrentapp.model.Car;

public interface CarService {
	List<Car> getAllCars();
	void saveCar(Car car);
	Car getCarById(long id);
	void deleteCarById(long id);
	Page<Car> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	List<Car> getAllFreeCars();
}
