package OskarFurmanczuk.carrentapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import OskarFurmanczuk.carrentapp.model.Car;
import OskarFurmanczuk.carrentapp.model.Client;
import OskarFurmanczuk.carrentapp.service.CarService;
import OskarFurmanczuk.carrentapp.service.ClientService;


@Controller
@RequestMapping("/car")
public class CarController {

	@Autowired
	private CarService carService;
	
	@Autowired
	private ClientService clientService;
	// display list of Cars
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "brand", "asc", model);		
	}
	
	@GetMapping("/showNewCarForm")
	public String showNewCarForm(Model model) {
		// create model attribute to bind form data
		Car car = new Car();
		model.addAttribute("car", car);
		return "new_car";
	}
	
	@PostMapping("/saveCar")
	public String saveCar(@Valid @ModelAttribute("car") Car car, BindingResult br) {
		
		if (br.hasErrors()) {
			return "new_car";
		}
		
		// save Car to database
		carService.saveCar(car);
		return "redirect:/car/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get Car from the service
		Car car = carService.getCarById(id);
		
		// set Car as a model attribute to pre-populate the form
		model.addAttribute("car", car);
		return "update_car";
	}
	
	@GetMapping("/deleteCar/{id}")
	public String deleteCar(@PathVariable (value = "id") long id) {
		
		Client client = clientService.getClientByCarId(id);
		if (client != null) {
			client.setCar(null);
			clientService.saveClient(client);
		}
		
		// call delete Car method 
		this.carService.deleteCarById(id);
		return "redirect:/car/";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Car> page = carService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Car> listCars = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listCars", listCars);
		
		return "car_list";
	}
	
	@GetMapping("/finishTheRent/{car_id}")
	public String returnCar(@PathVariable (value = "car_id") long id) {
		
		Client client = clientService.getClientByCarId(id);
		client.setCar(null);
		clientService.saveClient(client);
		
		return "redirect:/client/";
	}
}
