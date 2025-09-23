package com.cp.lab09sec1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cp.lab09sec1.service.WebClientCustomerService;
import com.cp.lab09sec1.dto.CustomerRequest;
import com.cp.lab09sec1.dto.CustomerResponse;
import com.cp.lab09sec1.model.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class CustomerFrontController {
	@Autowired
	WebClientCustomerService customerService;

	@GetMapping("/")
	public String getIndex() {
		return "index";
	}

	@GetMapping("/web/authors")
	public String getAllCustomer(Model model) {
		model.addAttribute("customers",
				customerService.getAllCustomer()
				.collectList().block()); // Flux<Author>.block() -> List<Author>																					// // types
		return "authorListCRUD";
	}

	@GetMapping("/web/authors/{id}") // view
	public String getViewCustomerById(@PathVariable Long id, Model model) {
		CustomerResponse customer = customerService.getCustomerById(id).block(); //Mon<Author>.block()->Author
		model.addAttribute("customer", customer);
		return "authorDetail";
	}

	@GetMapping("/web/createauthor") // Enter a new Author
	public String createCustomer(Model model) {
		//Customer customer = new Customer();
		//model.addAttribute("customer", customer);
		model.addAttribute("customerRequest", new CustomerRequest());
		return "addAuthorForm";
	}

	@PostMapping("/web/addAuthor") // save the inputed new author
	public String addCustomer(@ModelAttribute CustomerRequest authorRequest, 
			Model model) {
		System.out.println("add a new customer");
		// ต้องมีตัวแปรมารับ Mono<Author> ก่อน
		Mono<CustomerResponse> monoAuthor = customerService.createCustomer(authorRequest);
		// ต้องเพิ่ม model ด้วยหลังจากสร้าง new Author ใหม่ เพราะถ้าไม่สร้างจะไม่แสดงใน
		// "authorListCRUD"

		model.addAttribute("customer", monoAuthor.block());
		return "redirect:/web/authors";
	}

	@GetMapping("/web/editauthor/{id}") // editing a specific author by id
	public String editCustomer(@PathVariable Long id, Model model) {
		Mono<CustomerResponse> monoAuthor = customerService.getCustomerById(id);
		CustomerResponse customerRequest = monoAuthor.blockOptional().get();
		model.addAttribute("customer", customerRequest);
		return "editAuthorForm";
	}

	@PostMapping("/web/updateauthor/{id}") // Edit, save the edited author
	public String updateCustomer(@PathVariable Long id,
			@ModelAttribute CustomerRequest authorRequest, Model model) {

		Mono<CustomerResponse> monoAuthor = customerService.updateCustomer(id,
											authorRequest);
		model.addAttribute("customer", monoAuthor.block());
		return "redirect:/web/authors";
	}

	@GetMapping("/web/deleteauthor/{id}") // delete
	public String deleteCustomer(@PathVariable Long id) {
		// การลบ ควรหาตัวมารับ เพราะใน Service มีสองแบบ คือตัวเดี่ยว ๆ ใช้ Mono
		// ถ้าเป็นกลุ่มใช้ Flux การแปลง จาก Mono เป็น instance ของคลาสใด ๆ ใช้ block()	
		 customerService.deleteCustomerById(id).block();
      //  model.addAttribute("author",monoDeletedAuthor.block());
	    return "redirect:/web/authors";
	  
	}
}
