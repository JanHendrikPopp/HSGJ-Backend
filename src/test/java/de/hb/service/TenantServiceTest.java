package de.hb.service;

import java.util.List;

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
import de.hb.model.Tenant;
import de.hb.repositories.TenantRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HbApplication.class)
@WebAppConfiguration
public class TenantServiceTest {

	@Autowired TenantService service;
	
	@Autowired TenantRepository rep;
	
	private TestHelper helper = new TestHelper();
	
	@Before
	public void setUp() {
		Iterable<Tenant> tenants = rep.findAll();
		//rep.delete(tenants);
	}
	
	@After
	public void cleanUp() {
		Iterable<Tenant> tenants = rep.findAll();
		//rep.delete(tenants);
	}
	
	public void testCreateTenant() {
		
		Tenant tenant = helper.generateTestTenant();
		Assert.assertTrue(tenant.getId() == null);
		
		tenant = service.saveTenant(tenant);
		
		Assert.assertTrue(tenant.getId() != null);
		Assert.assertTrue(tenant.isActive());
		Assert.assertTrue(tenant.getStreet().equals(TestHelper.STREET));
		Assert.assertTrue(tenant.getPostcode().equals(TestHelper.POSTCODE));
		Assert.assertTrue(tenant.getCountry().equals(TestHelper.COUNTRY));
	}
	
	@Test(expected= ConstraintViolationException.class) 
	public void testSaveTenantExceptionGender() {
		Tenant tenant = helper.generateTestTenant();
		tenant = service.saveTenant(tenant);
	}
	
	@Test(expected= ConstraintViolationException.class) 
	public void testSaveTenantExceptionFirstname() {
		Tenant tenant = helper.generateTestTenant();
		tenant = service.saveTenant(tenant);
	}
	
	@Test(expected= ConstraintViolationException.class) 
	public void testSaveTenantExceptionLastname() {
		Tenant tenant = helper.generateTestTenant();
		tenant = service.saveTenant(tenant);
	}
	
	@Test(expected= ConstraintViolationException.class) 
	public void testSaveTenantExceptionStreet() {
		Tenant tenant = helper.generateTestTenant();
		tenant.setStreet(null);
		tenant = service.saveTenant(tenant);
	}
	
	@Test(expected= ConstraintViolationException.class) 
	public void testSaveTenantExceptionCountry() {
		Tenant tenant = helper.generateTestTenant();
		tenant.setCountry(null);
		tenant = service.saveTenant(tenant);
	}
	
	public void testEditTenant() {
		String editName = "newname";
		Tenant tenant = helper.generateTestTenant();
		tenant = service.saveTenant(tenant);
	}
	
	public void testDeactivateTenant() {
		Tenant tenant = helper.generateTestTenant();
		tenant = service.saveTenant(tenant);
		Assert.assertTrue(tenant.isActive());
		tenant = service.deactivateTenant(tenant);
		Assert.assertTrue(!tenant.isActive());
	}
	
	public void testActivateTenant() {
		Tenant tenant = helper.generateTestTenant();
		tenant = service.saveTenant(tenant);
		Assert.assertTrue(tenant.isActive());
		tenant = service.deactivateTenant(tenant);
		Assert.assertTrue(!tenant.isActive());
		tenant = service.activateTenant(tenant);
		Assert.assertTrue(tenant.isActive());
	}
	
	public void testFindAllTenants() {
		Tenant tenant1 = helper.generateTestTenant();
		Tenant tenant2 = helper.generateTestTenant();
		Tenant tenant3 = helper.generateTestTenant();
		service.saveTenant(tenant1);
		service.saveTenant(tenant2);
		service.saveTenant(tenant3);
		
		List<Tenant> tenants = service.getAllTenants();
		Assert.assertTrue(tenants.size() == 17);
	}
	
	public void testFindActiveTenants() {
		Tenant tenant1 = helper.generateTestTenant();
		Tenant tenant2 = helper.generateTestTenant();
		Tenant tenant3 = helper.generateTestTenant();
		service.saveTenant(tenant1);
		tenant2 = service.saveTenant(tenant2);
		service.saveTenant(tenant3);
		
		service.deactivateTenant(tenant2);
		
		List<Tenant> tenants = service.getAllActiveTenants();
		Assert.assertTrue(tenants.size() == 8);
	}
	
}
