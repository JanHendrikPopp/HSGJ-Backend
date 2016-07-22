package de.hb.helper;

import java.util.Date;

import de.hb.model.Gender;
import de.hb.model.Inventory;
import de.hb.model.InventoryType;
import de.hb.model.Reservation;
import de.hb.model.Tenant;

public class TestHelper {

	// Test Tenant
	public static final Gender GENDER = Gender.MALE;
	public static final String FIRSTNAME = "Vorname";
	public static final String LASTNAME = "Nachname";
	public static final int BIRTHDAY = 100;
	public static final String STREET = "Straße";
	public static final String POSTCODE = "21129";
	public static final String COUNTRY = "Deutschland";
	
	// Test Inventory
	public static final InventoryType INV_TYPE = InventoryType.BOAT;
	public static final String INV_NAME = "InvName";
	public static final String INV_COLOR = "Blue";
	public static final String INV_DESCRIPTION = "Kinderfahrrad";
	public static final int INV_PRICE = 10;
	
	// Test Reservation
	@SuppressWarnings("deprecation")
	public static final Date RES_START = new Date(2016, 1, 10);
	@SuppressWarnings("deprecation")
	public static final Date RES_END = new Date(2016, 1, 12);
	
	public Tenant generateTestTenant() {
		return generateTestTenant(GENDER, FIRSTNAME, LASTNAME, BIRTHDAY, STREET, POSTCODE, COUNTRY);
	}
	
	public Inventory generateTestInventory() {
		return generateTestInventory(INV_TYPE, INV_NAME);
	}
	
	public Reservation generateInitReservation() {
		return generateTestInitReservation(RES_START, RES_END);
	}
	
	public Tenant generateTestTenant(
			Gender gender,
			String firstname,
			String lastname,
			int birthday,
			String street,
			String postcode,
			String country) {
		Tenant tenant = new Tenant();
		tenant.setStreet(street);
		tenant.setPostcode(postcode);
		tenant.setCountry(country);
		return tenant;
	}
	
	public Inventory generateTestInventory(
			InventoryType type,
			String name) {
		Inventory inventory = new Inventory();
		inventory.setType(type);
		inventory.setName(name);
		return inventory;
	}
	
	public Reservation generateTestInitReservation(
			Date start,
			Date end
			) {
		Reservation reservation = new Reservation();
		reservation.setStart(start);
		reservation.setEnd(end);
		return reservation;
	}
}
