package com.duo.costsharingscheduler.Repository;

import com.duo.costsharingscheduler.Model.Scheduler;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulerRepository extends CrudRepository<Scheduler, Long> {
}
