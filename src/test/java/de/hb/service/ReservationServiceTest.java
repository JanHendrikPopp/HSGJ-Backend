package de.hb.service;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.junit.After;
import org.junit.Assert;
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
import de.hb.model.Reservation;
import de.hb.model.Tenant;
import de.hb.repositories.InventoryRepository;
import de.hb.repositories.ReservationRepository;
import de.hb.repositories.TenantRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HbApplication.class)
@WebAppConfiguration
public class ReservationServiceTest {

	@Autowired InventoryService invService;
	@Autowired InventoryRepository invRep;
	
	@Autowired TenantService tenService;
	@Autowired TenantRepository tenRep;
	
	@Autowired ReservationService service;
	@Autowired ReservationRepository rep;
	
	private TestHelper helper = new TestHelper();
	
	@Before
	public void setUp() {
		Iterable<Inventory> inventories = invRep.findAll();
		Iterable<Tenant> tenants = tenRep.findAll();
		Iterable<Reservation> reservations = rep.findAll();
		rep.delete(reservations);
		invRep.delete(inventories);
		tenRep.delete(tenants);
	}
	
	@After
	public void cleanUp() {
		Iterable<Inventory> inventories = invRep.findAll();
		Iterable<Tenant> tenants = tenRep.findAll();
		Iterable<Reservation> reservations = rep.findAll();
		rep.delete(reservations);
		invRep.delete(inventories);
		tenRep.delete(tenants);
	}
	
	@Test
	public void testInitReservation() {
		Tenant tenant = helper.generateTestTenant();
		tenant = tenService.saveTenant(tenant);
		
		Reservation reservation = helper.generateInitReservation();
		reservation.setTenant(tenant);
		
		Assert.assertTrue(reservation.getId() == null);
		
		reservation = service.initReservation(reservation);
		
		Assert.assertTrue(reservation.getId() != null);
		Assert.assertTrue(reservation.getStart().equals(TestHelper.RES_START));
		Assert.assertTrue(reservation.getEnd().equals(TestHelper.RES_END));
	}
	
	@Test(expected= ConstraintViolationException.class) 
	public void testInitReservationWithoutStartDate() {
		Tenant tenant = helper.generateTestTenant();
		tenant = tenService.saveTenant(tenant);
		
		Reservation reservation = helper.generateInitReservation();
		reservation.setTenant(tenant);
		reservation.setStart(null);
		
		reservation = service.initReservation(reservation);
	}
	
	@Test(expected= ConstraintViolationException.class) 
	public void testInitReservationWithoutEndDate() {
		Tenant tenant = helper.generateTestTenant();
		tenant = tenService.saveTenant(tenant);
		
		Reservation reservation = helper.generateInitReservation();
		reservation.setTenant(tenant);
		reservation.setEnd(null);
		
		reservation = service.initReservation(reservation);
	}
	
	@Test(expected= ConstraintViolationException.class) 
	public void testInitReservationWithoutTenant() {
		Tenant tenant = helper.generateTestTenant();
		tenant = tenService.saveTenant(tenant);
		
		Reservation reservation = helper.generateInitReservation();
		
		reservation = service.initReservation(reservation);
	}
	
	@Test 
	public void testAddInventory() {
		Tenant tenant = helper.generateTestTenant();
		tenant = tenService.saveTenant(tenant);
		Inventory inventory1 = helper.generateTestInventory();
		inventory1 = invService.saveInventory(inventory1);
		Inventory inventory2 = helper.generateTestInventory();
		inventory2 = invService.saveInventory(inventory2);
		
		Reservation reservation = helper.generateInitReservation();
		reservation.setTenant(tenant);
		reservation = service.initReservation(reservation);
		
		Assert.assertTrue(reservation.getInventories().size() == 0);
		reservation = service.addInventory(reservation, inventory1);
		reservation = service.addInventory(reservation, inventory2);
		Assert.assertTrue(reservation.getInventories().size() == 2);
	}
	
	@Test 
	public void testRemoveInventory() {
		Tenant tenant = helper.generateTestTenant();
		tenant = tenService.saveTenant(tenant);
		Inventory inventory1 = helper.generateTestInventory();
		inventory1 = invService.saveInventory(inventory1);
		Inventory inventory2 = helper.generateTestInventory();
		inventory2 = invService.saveInventory(inventory2);
		
		Reservation reservation = helper.generateInitReservation();
		reservation.setTenant(tenant);
		reservation = service.initReservation(reservation);
		
		Assert.assertTrue(reservation.getInventories().size() == 0);
		reservation = service.addInventory(reservation, inventory1);
		reservation = service.addInventory(reservation, inventory2);
		Assert.assertTrue(reservation.getInventories().size() == 2);
		reservation = service.removeInventory(reservation, inventory2);
		Assert.assertTrue(reservation.getInventories().size() == 1);
	}
	
	@Test
	public void testCancelReservation() {
		Tenant tenant = helper.generateTestTenant();
		tenant = tenService.saveTenant(tenant);
		Inventory inventory = helper.generateTestInventory();
		inventory = invRep.save(inventory);
		
		Reservation reservation = helper.generateInitReservation();
		reservation.setTenant(tenant);
		reservation = service.initReservation(reservation);
		reservation = service.addInventory(reservation, inventory);
		
		reservation = service.cancelReservation(reservation);
	}
	
}
