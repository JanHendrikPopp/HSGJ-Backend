package de.hb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import de.hb.model.Reservation;

@RepositoryRestResource(path = "reservations", collectionResourceRel = "reservations")
public interface ReservationRepository extends CrudRepository<Reservation, Long>{

}
