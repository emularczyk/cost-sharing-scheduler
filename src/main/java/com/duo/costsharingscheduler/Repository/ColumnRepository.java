package com.duo.costsharingscheduler.Repository;

import com.duo.costsharingscheduler.Model.Column;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnRepository extends CrudRepository<Column,Long> {}
