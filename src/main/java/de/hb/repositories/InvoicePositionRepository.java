package de.hb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import de.hb.model.InvoicePosition;

@RepositoryRestResource(path = "invoicePositions", collectionResourceRel = "invoicePositions")
public interface InvoicePositionRepository extends CrudRepository<InvoicePosition, Long> {

}
