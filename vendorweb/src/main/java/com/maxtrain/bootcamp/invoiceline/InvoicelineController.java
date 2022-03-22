package com.maxtrain.bootcamp.invoiceline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/invoicelines")

public class InvoicelineController {
	@Autowired
	private InvoicelineRepository invlnRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<Invoiceline>>getInvoicelines(){
		var invoicelines = invlnRepo.findAll();
		return new ResponseEntity<Iterable<Invoiceline>>(invoicelines, HttpStatus.OK);
		}
	
	
	@GetMapping("{id}")
	public ResponseEntity<Invoiceline>getinvoiceline(@PathVariable int id){
		var invln = invlnRepo.findById(id);
		if(invln.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Invoiceline>(invln.get(), HttpStatus.OK);
	}
	
		
	@PostMapping
	public ResponseEntity<Invoiceline>postInvoiceline(@RequestBody Invoiceline invoiceline){
		if(invoiceline == null || invoiceline.getId() !=0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
			var invln = invlnRepo.save(invoiceline);
			return new ResponseEntity<Invoiceline>(invln, HttpStatus.CREATED);
	}
	
	
	
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}")
	public ResponseEntity putInvoiceline(@PathVariable int id, 
			@RequestBody Invoiceline invoiceline) {
		if(invoiceline == null |invoiceline.getId()==0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var invln = invlnRepo.findById(invoiceline.getId());
		if(invln.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		invlnRepo.save(invoiceline);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteInvoiceline(@PathVariable int id) {
		var invln = invlnRepo.findById(id);
		if(invln.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		invlnRepo.delete(invln.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;	
	}
	
}
