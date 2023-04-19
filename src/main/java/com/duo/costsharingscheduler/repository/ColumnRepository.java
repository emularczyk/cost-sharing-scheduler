package com.duo.costsharingscheduler.repository;

import com.duo.costsharingscheduler.model.Column;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnRepository extends CrudRepository<Column, Long> {
}
