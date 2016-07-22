package de.hb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hb.model.Inventory;
import de.hb.model.Invoice;
import de.hb.model.InvoicePosition;
import de.hb.repositories.InvoicePositionRepository;
import de.hb.repositories.InvoiceRepository;

@Service
public class InvoiceService {

	@Autowired
	private InvoicePositionRepository invoicePositionRep;
	@Autowired
	private InvoiceRepository invoiceRep;
	
	public Invoice createInvoice(List<Inventory> inventories) {
		List<InvoicePosition> positions = new ArrayList<InvoicePosition>();
		
		for (int i = 0; i < inventories.size(); i++) {
			InvoicePosition position = new InvoicePosition(inventories.get(i));
			position = invoicePositionRep.save(position);
			positions.add(position);
		}
		
		Invoice invoice = new Invoice(positions);
		return invoiceRep.save(invoice);
	}
}
