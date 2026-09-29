package ca.hccis.panini.repositories;

import java.util.List;

import ca.hccis.panini.jpa.entity.CodeValue;
import ca.hccis.panini.jpa.entity.CodeValueId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeValueRepository extends CrudRepository<CodeValue, CodeValueId> {
}