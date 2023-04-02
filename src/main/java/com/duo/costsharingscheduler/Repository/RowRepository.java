package com.duo.costsharingscheduler.Repository;

import com.duo.costsharingscheduler.Model.Row;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RowRepository extends CrudRepository<Row,Long> {}
