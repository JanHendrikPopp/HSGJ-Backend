package de.hb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

import de.hb.model.Tenant;

@CrossOrigin(origins = "http://localhost:8080/")
@RepositoryRestResource(path = "tenants", collectionResourceRel = "tenants")
public interface TenantRepository extends CrudRepository<Tenant, Long> {
	
	List<Tenant> findByActive(@Param("active") Boolean active);
	
}