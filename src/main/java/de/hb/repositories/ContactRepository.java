package de.hb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import de.hb.model.Contact;

@RepositoryRestResource(path = "contacts", collectionResourceRel = "contacts")
public interface ContactRepository extends CrudRepository<Contact, Long> {

}
