package de.hb.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import de.hb.model.Contact;
import de.hb.model.Inventory;
import de.hb.model.Invoice;
import de.hb.model.Reservation;
import de.hb.model.Tenant;
import de.hb.repositories.TenantRepository;

@Configuration
public class DataRestConfiguration extends RepositoryRestMvcConfiguration {

	@Override
	public RepositoryRestConfiguration config() {
		RepositoryRestConfiguration config = super.config();
		config.exposeIdsFor(Tenant.class, Inventory.class, Reservation.class, Invoice.class, Contact.class)
			.setBasePath("/api/1");
		return config;
	}
}
