package ca.hccis.panini.repositories;

import ca.hccis.panini.jpa.entity.PaniniOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaniniOrderRepository extends CrudRepository<PaniniOrder, Integer> {
}
