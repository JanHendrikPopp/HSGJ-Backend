package de.hb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hb.model.Inventory;
import de.hb.repositories.InventoryRepository;

@Service
public class InventoryService {

	@Autowired
	private InventoryRepository rep;
	
	public List<Inventory> getAllInventories() {
		return (List<Inventory>) rep.findAll();
	}
	
	public List<Inventory> getAllActiveInventories() {
		return rep.findByActive(true);
	}
	
	public Inventory saveInventory(Inventory inventory) {
		return rep.save(inventory);
	}
	
	public Inventory deactivateInventory(Inventory inventory) {
		inventory.setActive(false);
		return rep.save(inventory);
	}
	
	public Inventory activateInventory(Inventory inventory) {
		inventory.setActive(true);
		return rep.save(inventory);
	}
	
}
