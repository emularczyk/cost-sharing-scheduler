package com.duo.costsharingscheduler.repository;

import com.duo.costsharingscheduler.model.Row;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RowRepository extends CrudRepository<Row, Long> {
}
