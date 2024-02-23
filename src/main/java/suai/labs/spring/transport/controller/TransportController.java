package suai.labs.spring.transport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import suai.labs.spring.transport.service.transport.TransportRepository;

@Controller
public class TransportController {

	@Autowired
	TransportRepository transportRepository;

	@GetMapping("/")
	public String openHomePage() {
		return "homePage";
	}

	@GetMapping("/viewAvailableTransports")
	public String openAvailableTransportsPage(Model model){
		return "viewAvailableTransportsPage";
	}

	@GetMapping("/addTransport")
	public String openAddTransportPage(Model model){
		return "addTransportPage";
	}

	@GetMapping("/crudTransport")
	public String openCrudTransportPage(Model model) {
		return "crudPage";
	}

}
