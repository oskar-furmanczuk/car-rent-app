package OskarFurmanczuk.carrentapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class StartController {


	// display list of Cars
	@GetMapping("/")
	public String startPage() {
		return "index";		
	}
	
}
