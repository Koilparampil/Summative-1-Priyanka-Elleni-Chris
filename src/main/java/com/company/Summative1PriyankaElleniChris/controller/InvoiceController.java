package com.company.Summative1PriyankaElleniChris.controller;

import com.company.Summative1PriyankaElleniChris.model.Invoice;
import com.company.Summative1PriyankaElleniChris.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    InvoiceRepository invoiceRepository;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getInvoices(){
        return invoiceRepository.findAll();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Invoice getSingleInvoice(@PathVariable Integer id) throws NoSuchFieldException{
        Optional<Invoice> returnVal = invoiceRepository.findById(id);
        if (returnVal.isPresent()){
            return returnVal.get();
        } else {
            throw new NoSuchFieldException("Invoice not Found in Collection");
        }
    }
    @GetMapping("/CustomerName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getInvoiceByCustomerName (@PathVariable String name){
        return invoiceRepository.findByName(name);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)


}
