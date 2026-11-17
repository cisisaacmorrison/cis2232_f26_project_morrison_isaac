
package ca.hccis.squash.repositories;

import ca.hccis.squash.jpa.entity.PaniniOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaniniOrderRepository extends JpaRepository<PaniniOrder, Integer> {
}
