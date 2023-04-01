package com.duo.costsharingscheduler.Repository;

import com.duo.costsharingscheduler.Model.GroupsOfCells;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupsOfCellsRepository extends CrudRepository<GroupsOfCells,Long> {}
