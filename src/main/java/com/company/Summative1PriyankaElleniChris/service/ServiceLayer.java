package com.company.Summative1PriyankaElleniChris.service;


import com.company.Summative1PriyankaElleniChris.model.Invoice;
import com.company.Summative1PriyankaElleniChris.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class ServiceLayer {

    private ConsoleRepository consoleRepository;
    private GameRepository gameRepository;
    private InvoiceRepository invoiceRepository;
    private ProcessingFeeRepository processingFeeRepository;
    private SalesTaxRatRepository salesTaxRatRepository;
    private T_shirtRepository t_shirtRepository;

    @Autowired
    public ServiceLayer(ConsoleRepository consoleRepository, GameRepository gameRepository, InvoiceRepository invoiceRepository, ProcessingFeeRepository processingFeeRepository, SalesTaxRatRepository salesTaxRatRepository, T_shirtRepository t_shirtRepository) {
        this.consoleRepository = consoleRepository;
        this.gameRepository = gameRepository;
        this.invoiceRepository = invoiceRepository;
        this.processingFeeRepository = processingFeeRepository;
        this.salesTaxRatRepository = salesTaxRatRepository;
        this.t_shirtRepository = t_shirtRepository;
    }

    @Transactional
    public Invoice  saveInvoice( Invoice invoice){
        switch(invoice.getItemType()){
            case "Console":
                invoice.setUnitPrice(consoleRepository.findById(invoice.getInvoiceId()).get().getPrice());
                break;
            case "Game":
                invoice.setUnitPrice(gameRepository.findById(invoice.getInvoiceId()).get().getPrice());
                break;
            case "T-Shirt":
                invoice.setUnitPrice(t_shirtRepository.findById(invoice.getInvoiceId()).get().getPrice());
                break;
        }

        invoice.setSubtotal(new BigDecimal(invoice.getQuantity()* new Integer(String.valueOf(invoice.getUnitPrice()))));

        float taxRate =salesTaxRatRepository.findByState(invoice.getState()).getRate();
        Float subtotal = new Float(String.valueOf(invoice.getSubtotal()));
        invoice.setTax(new BigDecimal(taxRate*subtotal));

        invoice.setProcessingFee(processingFeeRepository.findByProductType(invoice.getItemType()).getFee());
        if (invoice.getQuantity()>10){
            invoice.setProcessingFee(new BigDecimal(new Float(String.valueOf(invoice.getProcessingFee())+15.49)));
        }

        float tax= new Float(String.valueOf(invoice.getTax()));
        float processingFee = new Float(String.valueOf(invoice.getProcessingFee()));
        invoice.setTotal(new BigDecimal(subtotal+tax+processingFee));

        invoiceRepository.save(invoice);

        return invoice;
    }



}
