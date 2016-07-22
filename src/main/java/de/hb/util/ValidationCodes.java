package de.hb.util;

public interface ValidationCodes {

	public static final String NOT_EMPTY = ".not.empty";
	public static final String NOT_VALID = ".not.valid";
	
	public static final String TENANT = "validation.tenant";
	public static final String TENANT_NAME = TENANT + ".name";
	public static final String TENANT_NAME_NOTEMPTY = TENANT_NAME + NOT_EMPTY;
	public static final String TENANT_STREET = TENANT + ".street";
	public static final String TENANT_STREET_NOTEMPTY = TENANT_STREET + NOT_EMPTY;
	public static final String TENANT_POSTCODE = TENANT + ".postcode";
	public static final String TENANT_POSTCODE_NOTEMPTY = TENANT_POSTCODE + NOT_EMPTY;
	public static final String TENANT_POSTCODE_NOTVALID = TENANT_POSTCODE + NOT_VALID;
	public static final String TENANT_COUNTRY = TENANT + ".country";
	public static final String TENANT_COUNTRY_NOTEMPTY = TENANT_COUNTRY + NOT_EMPTY;
	
	public static final String INVENTORY = "validation.inventory";
	public static final String INVENTORY_NAME = INVENTORY + ".name";
	public static final String INVENTORY_NAME_NOTEMPTY = INVENTORY_NAME + NOT_EMPTY;
	public static final String INVENTORY_TYPE = INVENTORY + ".type";
	public static final String INVENTORY_TYPE_NOTEMPTY = INVENTORY_TYPE + NOT_EMPTY;

	public static final String CONTACT = "validation.contact";
	public static final String CONTACT_FIRSTNAME = CONTACT + ".firstname";
	public static final String CONTACT_FIRSTNAME_NOTEMPTY = CONTACT_FIRSTNAME + NOT_EMPTY;
	public static final String CONTACT_LASTNAME = CONTACT + ".lastname";
	public static final String CONTACT_LASTNAME_NOTEMPTY = CONTACT_LASTNAME + NOT_EMPTY;
	public static final String CONTACT_PHONE = CONTACT + ".phone";
	public static final String CONTACT_PHONE_NOTEMPTY = CONTACT_PHONE + NOT_EMPTY;
	public static final String CONTACT_MAIL = CONTACT + ".mail";
	public static final String CONTACT_MAIL_NOTEMPTY = CONTACT_MAIL + NOT_EMPTY;
	public static final String CONTACT_TYPE = CONTACT + ".type";
	public static final String CONTACT_TYPE_NOTEMPTY = CONTACT_TYPE + NOT_EMPTY;

}
