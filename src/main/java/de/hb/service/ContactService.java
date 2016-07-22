package de.hb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hb.model.Contact;
import de.hb.repositories.ContactRepository;

@Service
public class ContactService {

	@Autowired
	private ContactRepository rep;
	
	public List<Contact> getAllContacts() {
		return (List<Contact>) rep.findAll();
	}
	
	public Contact saveContact(Contact contact) {
		return rep.save(contact);
	}
}
