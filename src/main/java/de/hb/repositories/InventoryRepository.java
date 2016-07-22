package de.hb.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import de.hb.model.Inventory;

@RepositoryRestResource(path = "inventories", collectionResourceRel = "inventories")
public interface InventoryRepository extends CrudRepository<Inventory, Long> {

	List<Inventory> findByActive(@Param("active") Boolean active);
	
}
