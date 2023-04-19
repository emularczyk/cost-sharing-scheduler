package com.duo.costsharingscheduler.repository;

import com.duo.costsharingscheduler.model.ValueField;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValueFieldRepository extends CrudRepository<ValueField, Long> {
}
