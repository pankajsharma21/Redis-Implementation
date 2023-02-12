package com.Redisimpl.Redis.Implementation.Service;

import java.util.List;

import com.Redisimpl.Redis.Implementation.CustomException.InvoiceNotFoundException;
import com.Redisimpl.Redis.Implementation.Entity.Invoice;
import com.Redisimpl.Redis.Implementation.Repo.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepo;

    @Override
    public Invoice saveInvoice(Invoice inv) {

        return invoiceRepo.save(inv);
    }

    @Override
    @CachePut(value="Invoice", key="#invId")
    public Invoice updateInvoice(Invoice inv, Integer invId) {
        Invoice invoice = invoiceRepo.findById(invId)
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice Not Found"));
        invoice.setInvAmount(inv.getInvAmount());
        invoice.setInvName(inv.getInvName());
        return invoiceRepo.save(invoice);
    }

    @Override
    @CacheEvict(value="Invoice", key="#invId")
    // @CacheEvict(value="Invoice", allEntries=true) //in case there are multiple records to delete
    public void deleteInvoice(Integer invId) {
        Invoice invoice = invoiceRepo.findById(invId)
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice Not Found"));
        invoiceRepo.delete(invoice);
    }

    @Override
    @Cacheable(value="Invoice", key="#invId")
    public Invoice getOneInvoice(Integer invId) {
        Invoice invoice = invoiceRepo.findById(invId)
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice Not Found"));
        return invoice;
    }

    @Override
    @Cacheable(value="Invoice")
    public List<Invoice> getAllInvoices() {
        return invoiceRepo.findAll();
    }
}