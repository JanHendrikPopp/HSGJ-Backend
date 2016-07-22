package de.hb.initializers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import de.hb.model.Contact;
import de.hb.model.ContactType;
import de.hb.model.Gender;
import de.hb.model.Inventory;
import de.hb.model.InventoryType;
import de.hb.model.Reservation;
import de.hb.model.Tenant;
import de.hb.repositories.InventoryRepository;
import de.hb.service.ContactService;
import de.hb.service.InventoryService;
import de.hb.service.ReservationService;
import de.hb.service.TenantService;

@Component
public class DatabaseInitializer implements CommandLineRunner {

	private final ReservationService reservationService;
	
	private final TenantService tenantService;
	
	private final InventoryService inventoryService;
	
	private final ContactService contactService;
	
	@Autowired
	public DatabaseInitializer(TenantService service1, InventoryService service2, ReservationService service3, ContactService service4) {
		this.tenantService = service1;
		this.inventoryService = service2;
		this.reservationService = service3;
		this.contactService = service4;
	}
	
	@Override
	public void run(String... args) throws Exception {
		generateTestInventories();
		generateTestTenants();
		generateTestReservations();
	}
	
	public void generateTestReservations() {
		List<Tenant> tenants = tenantService.getAllActiveTenants();
		List<Inventory> inventories = inventoryService.getAllActiveInventories();
		
		Reservation reservation1 = generateTestInitReservation(new Date(2016,1,10), new Date(2016,1,12), tenants.get(0));
		Reservation reservation2 = generateTestInitReservation(new Date(2016,2,10), new Date(2016,2,12), tenants.get(0));
		Reservation reservation3 = generateTestInitReservation(new Date(2016,3,10), new Date(2016,3,12), tenants.get(0));
		Reservation reservation4 = generateTestInitReservation(new Date(2016,4,10), new Date(2016,4,12), tenants.get(0));
		
		reservation1 = reservationService.initReservation(reservation1);
		reservation2 = reservationService.initReservation(reservation2);
		reservation3 = reservationService.initReservation(reservation3);
		reservation4 = reservationService.initReservation(reservation4);
		
		reservationService.addInventory(reservation2, inventories.get(0));
		reservationService.addInventory(reservation2, inventories.get(1));
		
		reservationService.endReservation(reservation2);
		
		reservationService.cancelReservation(reservation4);
	}
	
	public Reservation generateTestInitReservation(
			Date start,
			Date end,
			Tenant tenant
			) {
		Reservation reservation = new Reservation();
		reservation.setStart(start);
		reservation.setEnd(end);
		reservation.setTenant(tenant);
		return reservation;
	}
	
	private void generateTestTenants() {
		tenantService.saveTenant(generateTestTenant("Name 1","Straße 1", "11111", "Deutschland"));
		tenantService.saveTenant(generateTestTenant("Name 2", "Straße 2", "22222", "Deutschland"));
		tenantService.saveTenant(generateTestTenant("Name 3", "Straße 3", "33333", "Deutschland"));
		tenantService.saveTenant(generateTestTenant("Name 5", "Straße 4", "44444", "Deutschland"));
		tenantService.saveTenant(generateTestTenant("Name 6", "Straße 5", "55555", "Deutschland"));
		tenantService.saveTenant(generateTestTenant("Name 7", "Straße 6", "66666", "Deutschland"));
		Tenant tenant = tenantService.saveTenant(generateTestTenant("Name 8", "Straße 7", "77777", "Deutschland"));
		tenantService.deactivateTenant(tenant);
	}
	
	private Tenant generateTestTenant(
			String name,
			String street,
			String postcode,
			String country) {
		Tenant tenant = new Tenant();
		tenant.setName(name);
		tenant.setStreet(street);
		tenant.setPostcode(postcode);
		tenant.setCountry(country);
		return tenant;
	}
	
	private void generateTestInventories() {
		inventoryService.saveInventory(generateTestInventory(InventoryType.BOAT, "Commander Finnchen"));
		inventoryService.saveInventory(generateTestInventory(InventoryType.BOAT, "Prof. Hastig"));
		inventoryService.saveInventory(generateTestInventory(InventoryType.BUS, "HSGJ-Bus"));
	}
	
	private Inventory generateTestInventory(
			InventoryType type,
			String name) {
		Inventory inventory = new Inventory();
		inventory.setType(type);
		inventory.setName(name);
		return inventory;
	}
	
	private void generateTestContacts() {
		contactService.saveContact(generateTestContact(ContactType.HSGJ, "Vorname1", "Nachname1", "Phone1", "Mail1"));
		contactService.saveContact(generateTestContact(ContactType.HSGJ, "Vorname2", "Nachname2", "Phone2", "Mail2"));
		contactService.saveContact(generateTestContact(ContactType.HSGJ, "Vorname3", "Nachname3", "Phone3", "Mail3"));
	}
	
	private Contact generateTestContact(
			ContactType type,
			String firstname,
			String lastname,
			String phone,
			String mail) {
		Contact contact = new Contact();
		contact.setFirstname(firstname);
		contact.setLastname(lastname);
		contact.setPhone(phone);
		contact.setMail(mail);
		return contact;
	}
}
