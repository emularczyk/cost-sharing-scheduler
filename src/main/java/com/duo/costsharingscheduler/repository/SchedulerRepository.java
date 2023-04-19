package com.duo.costsharingscheduler.repository;

import com.duo.costsharingscheduler.model.Scheduler;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulerRepository extends CrudRepository<Scheduler, Long> {
}
