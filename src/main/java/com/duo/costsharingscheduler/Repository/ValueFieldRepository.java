package com.duo.costsharingscheduler.Repository;

import com.duo.costsharingscheduler.Model.ValueField;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValueFieldRepository extends CrudRepository<ValueField, Long> {
}
