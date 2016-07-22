package de.hb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hb.model.Tenant;
import de.hb.repositories.TenantRepository;

@Service
public class TenantService {

	@Autowired
	private TenantRepository rep;
	
	public List<Tenant> getAllTenants() {
		return (List<Tenant>) rep.findAll();
	}
	
	public List<Tenant> getAllActiveTenants() {
		return rep.findByActive(true);
	}
	
	public Tenant saveTenant(Tenant tenant) {
		return rep.save(tenant);
	}
	
	public Tenant deactivateTenant(Tenant tenant) {
		tenant.setActive(false);
		return rep.save(tenant);
	}
	
	public Tenant activateTenant(Tenant tenant) {
		tenant.setActive(true);
		return rep.save(tenant);
	}
	
}
