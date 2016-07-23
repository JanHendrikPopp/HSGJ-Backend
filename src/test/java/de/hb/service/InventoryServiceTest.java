package de.hb.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import de.hb.HbApplication;
import de.hb.helper.TestHelper;
import de.hb.model.Inventory;
import de.hb.repositories.InventoryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HbApplication.class)
@WebAppConfiguration
public class InventoryServiceTest {

	@Autowired InventoryService service;
	
	@Autowired InventoryRepository rep;
	
	private TestHelper helper = new TestHelper();
	
	@Before
	public void setUp() {
		Iterable<Inventory> inventories = rep.findAll();
		
		//rep.delete(inventories);
	}
	
	@After
	public void cleanUp() {
		Iterable<Inventory> inventories = rep.findAll();
		//rep.delete(inventories);
	}
	
	@Test
	public void testCreateInventory() {
		Inventory inventory = helper.generateTestInventory();
		Assert.assertTrue(inventory.getId() == null);
		
		inventory = service.saveInventory(inventory);
		
		Assert.assertTrue(inventory.getId() != null);
		Assert.assertTrue(inventory.getName().equals(TestHelper.INV_NAME));
		Assert.assertTrue(inventory.getType().equals(TestHelper.INV_TYPE));
	}
	
	@Test(expected= ConstraintViolationException.class) 
	public void testSaveInventoryExceptionType() {
		Inventory inventory = helper.generateTestInventory();
		inventory.setType(null);
		inventory = service.saveInventory(inventory);
	}
	
	@Test(expected= ConstraintViolationException.class) 
	public void testSaveInventoryExceptionName() {
		Inventory inventory = helper.generateTestInventory();
		inventory.setName(null);
		inventory = service.saveInventory(inventory);
	}
	
	public void testSaveInventoryExceptionColor() {
		Inventory inventory = helper.generateTestInventory();
		inventory = service.saveInventory(inventory);
	}
	
	public void testSaveInventoryExceptionDescription() {
		Inventory inventory = helper.generateTestInventory();
		inventory = service.saveInventory(inventory);
	}
	
	@Test
	public void testDeactivateInventory() {
		Inventory inventory = helper.generateTestInventory();
		inventory = service.saveInventory(inventory);
		inventory = service.deactivateInventory(inventory);
	}
	
	@Test
	public void testActivateInventory() {
		Inventory inventory = helper.generateTestInventory();
		inventory = service.saveInventory(inventory);
		inventory = service.deactivateInventory(inventory);
		inventory = service.activateInventory(inventory);
	}
	
	public void testFindAllInventories() {
		Inventory inventory1 = helper.generateTestInventory();
		Inventory inventory2 = helper.generateTestInventory();
		Inventory inventory3 = helper.generateTestInventory();
		service.saveInventory(inventory1);
		service.saveInventory(inventory2);
		service.saveInventory(inventory3);
		
		List<Inventory> inventories = service.getAllInventories();
		Assert.assertTrue(inventories.size() == 10);
	}
	
	public void testFindAllActiveInventories() {
		Inventory inventory1 = helper.generateTestInventory();
		Inventory inventory2 = helper.generateTestInventory();
		Inventory inventory3 = helper.generateTestInventory();
		service.saveInventory(inventory1);
		inventory2 = service.saveInventory(inventory2);
		service.saveInventory(inventory3);
		
		service.deactivateInventory(inventory2);
		
		List<Inventory> inventories = service.getAllActiveInventories();
		Assert.assertTrue(inventories.size() == 13);
	}
	
}
