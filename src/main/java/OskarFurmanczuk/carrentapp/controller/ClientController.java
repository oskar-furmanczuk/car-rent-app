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
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private CarService carService;
	
	// display list of Clients
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "lastName", "asc", model);		
	}
	
	@GetMapping("/showNewClientForm")
	public String showNewClientForm(Model model) {
		// create model attribute to bind form data
		Client client = new Client();
		model.addAttribute("client", client);
		List<Car> freeCars = carService.getAllFreeCars();
		
		// set Client as a model attribute to pre-populate the form
		
		model.addAttribute("freeCars", freeCars);
		
		return "new_client";
	}
	
	@PostMapping("/saveClient")
	public String saveClient(@Valid @ModelAttribute("client") Client client, BindingResult br) {
		
		if (br.hasErrors()) {
			System.out.println(br.getAllErrors());
			return "new_client";
		}
		
		System.out.println(client.getCar());
		// save Client to database
		clientService.saveClient(client);
		return "redirect:/client/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get Client from the service
		Client client = clientService.getClientById(id);
		
		// set Client as a model attribute to pre-populate the form
		model.addAttribute("client", client);
		return "update_client";
	}
	
	@GetMapping("/deleteClient/{id}")
	public String deleteClient(@PathVariable (value = "id") long id) {
		
		// call delete Client method 
		this.clientService.deleteClientById(id);
		return "redirect:/client/";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Client> page = clientService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Client> listClients = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listClients", listClients);
		return "client_list";
	}
	@GetMapping("/showFormForRenting/{id}")
	public String showFormForRenting(@PathVariable ( value = "id") long id, Model model) {
		
		// get Client from the service
		Client client = clientService.getClientById(id);
		
		List<Car> freeCars = carService.getAllFreeCars();
	
		// set Client as a model attribute to pre-populate the form
		model.addAttribute("client", client);
		
		model.addAttribute("freeCars", freeCars);
		
		return "client_rent_car";
	}
}
