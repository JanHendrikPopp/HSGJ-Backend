package de.hb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import de.hb.model.Invoice;

@RepositoryRestResource(path = "invoices", collectionResourceRel = "invoices")
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

}
