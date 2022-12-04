package com.pravin.warehouse.Shipmentcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pravin.warehouse.DTO.Shipment;
import com.pravin.warehouse.Shipmentserviceimpl.ShipmentImpl;

@Controller
@RequestMapping("/ship")
public class ShipmentController {

	@Autowired
	private ShipmentImpl service;

	@GetMapping("/show")
	public String showpage(Model model) {
		Shipment shipment = new Shipment();
		model.addAttribute("shipment", shipment);
		return "Shipment";
	}

	@PostMapping("/save")
	public String saveShipment(@ModelAttribute Shipment shipment, Model model) {
		
		if(shipment.getId()!=null) {
			Integer saveShipment = service.saveShipment(shipment);
			model.addAttribute("value", "Shipment '" + saveShipment + "' updated successfully");
			Shipment shipment1 = new Shipment();
			model.addAttribute("shipment", shipment1);
		}
		else {
			Integer saveShipment = service.saveShipment(shipment);
			model.addAttribute("value", "Shipment '" + saveShipment + "' saved successfully");
			Shipment shipment1 = new Shipment();
			model.addAttribute("shipment", shipment1);
		}

		return "Shipment";
	}

	@GetMapping("/list")
	public String listShipment(Model model) {

		List<Shipment> listAllShipment = service.listAllShipment();

		model.addAttribute("listship", listAllShipment);

		return "Shipmentlist";
	}

	@GetMapping("/delete/{id}")
	public String deleteShipment(@PathVariable Integer id, Model model) {

		if (service.exitShipment(id)) {

			service.deleteShipment(id);

			model.addAttribute("message", "Shipment deleted succeessfully");

			List<Shipment> listAllShipment = service.listAllShipment();

			model.addAttribute("listship", listAllShipment);
			
		} else {
			model.addAttribute("message", "Shipment not found in the list");
		}

		return "Shipmentlist";

	}
	
	@GetMapping("/edit/{id}")
	public String editShipment(@PathVariable Integer id, Model model) {
		
		Shipment shipment = service.getShipment(id);
		model.addAttribute("shipment", shipment);

		return "editshipment";
	}
}
