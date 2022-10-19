package com.company.Summative1PriyankaElleniChris.service;


import com.company.Summative1PriyankaElleniChris.model.Invoice;
import com.company.Summative1PriyankaElleniChris.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;

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
        //This Switch is to decide what unit price the item is
        switch(invoice.getItemType()){
            case "Console":
                invoice.setUnitPrice(consoleRepository.findById(invoice.getItemId()).get().getPrice());
                break;
            case "Game":
                invoice.setUnitPrice(gameRepository.findById(invoice.getItemId()).get().getPrice());
                break;
            case "T-Shirt":
                invoice.setUnitPrice(t_shirtRepository.findById(invoice.getItemId()).get().getPrice());
                break;
        }

        //to set the subtotal, we're multiplying the unit price by the Quantity
        //we have some funky type changes here.
        BigDecimal tempSubTotal = new BigDecimal(
                new Float(invoice.getQuantity())*
                        new Float(String.valueOf(invoice.getUnitPrice())));
        invoice.setSubtotal(tempSubTotal.setScale(2, RoundingMode.HALF_UP)); // while big decimal we scale it down to two decimal places and then do the rounding to half up which is the normal rounding rules

        //To set the Tax we're looking up the tax rate in the database by the state name, then we're setting the subtotal to a local vairble to make the multiplication easier
        // then we multiply the two together and turn it into the Big Decimal
        float taxRate =salesTaxRatRepository.findByState(invoice.getState()).getRate();
        float subtotal = new Float(String.valueOf(invoice.getSubtotal()));
        BigDecimal tempTax =new BigDecimal(taxRate*subtotal);
        invoice.setTax(tempTax.setScale(2, RoundingMode.HALF_UP));// while big decimal we scale it down to two decimal places and then do the rounding to half up which is the normal rounding rules

        //Here we set the initial processingFee, the low number, and then we check how many items the order has,
        invoice.setProcessingFee(processingFeeRepository.findById(invoice.getItemType()).get().getFee());
        if (invoice.getQuantity()>10){ //if over ten, the additional processingfee is called and added to the original fee
            BigDecimal tempProcessingFee=invoice.getProcessingFee();
            BigDecimal newProcessingFee = tempProcessingFee.add(new BigDecimal("15.49"));
            invoice.setProcessingFee(newProcessingFee);
        }

        //here we're finally finding the total and its essentially we're adding everything up, the subtotal, tax and ProcessingFee.
        float tax= new Float(String.valueOf(invoice.getTax()));
        float processingFee = new Float(String.valueOf(invoice.getProcessingFee()));
        BigDecimal tempTotal = new BigDecimal(subtotal+tax+processingFee);
        invoice.setTotal(tempTotal.setScale(2,RoundingMode.HALF_UP));// while big decimal we scale it down to two decimal places and then do the rounding to half up which is the normal rounding rules


        invoiceRepository.save(invoice);
        return invoice;
    }



}
