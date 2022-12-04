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



import com.pravin.warehouse.DTO.OrderMethod;
import com.pravin.warehouse.Shipmentserviceimpl.OrderMethodImpl;


@Controller
@RequestMapping("/ordermethod")
public class OrderMethodController {
	
	@Autowired
	private OrderMethodImpl orderservie;

	@GetMapping("/showorder")
	public  String showOrderMethod(Model model) {
		OrderMethod orderMethod = new OrderMethod();
		model.addAttribute("orderMethod", orderMethod);
		return "showordermethod";
		
	}
	
	@GetMapping("/listorder")
	public String  listOrderMethod(Model model) {
		List<OrderMethod> listAllOrderMethod = orderservie.listAllOrderMethod();
		model.addAttribute("listAllOrderMethod", listAllOrderMethod);
		return "listordermethod";
	}
	
	@PostMapping("/saveorder")
	public String saveOrderMethod(@ModelAttribute OrderMethod orderMethod,Model model) {
		
	       if(orderMethod.getId() !=null) {
			Integer value = orderservie.saveOrderMethod(orderMethod);
			model.addAttribute("mesage", "OrderMethod '" +value+" ' updated successfully");
			List<OrderMethod> listAllOrderMethod = orderservie.listAllOrderMethod();
	   		model.addAttribute("listAllOrderMethod", listAllOrderMethod);
		}
	       else {
	    	   Integer value = orderservie.saveOrderMethod(orderMethod);
	    	   model.addAttribute("mesage", "OrderMethod '" +value+" ' saved successfully");
	    	   List<OrderMethod> listAllOrderMethod = orderservie.listAllOrderMethod();
	   		   model.addAttribute("listAllOrderMethod", listAllOrderMethod);
	       }
		return "listordermethod";
	}
	
	@GetMapping("/editorder/{id}")
	public String editOrderMethod(@PathVariable Integer id,Model model) {
		OrderMethod orderMethod = orderservie.getOrderMethod(id);
		model.addAttribute("orderMethod", orderMethod);
		return "editordermethod";
	}
	
}
