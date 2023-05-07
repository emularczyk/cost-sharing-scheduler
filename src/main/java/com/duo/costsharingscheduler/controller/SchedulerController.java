package com.duo.costsharingscheduler.controller;

import com.duo.costsharingscheduler.model.Scheduler;
import com.duo.costsharingscheduler.repository.ColumnRepository;
import com.duo.costsharingscheduler.repository.RowRepository;
import com.duo.costsharingscheduler.repository.SchedulerRepository;
import com.duo.costsharingscheduler.repository.ValueFieldRepository;
import com.duo.costsharingscheduler.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SchedulerController {

    @Autowired
    private SchedulerRepository schedulerRepository;
    @Autowired
    private RowRepository rowRepository;
    @Autowired
    private ColumnRepository columnRepository;
    @Autowired
    private ValueFieldRepository valueFieldRepository;
    @Autowired
    private SchedulerService schedulerService;


    @GetMapping("/generateScheduler")
    public void creteDefaultScheduler() {
        schedulerService.createDefaultScheduler();
    }

    @GetMapping("/schedulers")
    public String getMainPage(Model model) {
        List<Scheduler> schedulers = schedulerService.findAllSchedulers();
        model.addAttribute("schedulers", schedulers);

        return "schedulers";
    }

    @PostMapping("/scheduler/addNew")
    public String newScheduler(String schedulerTitle) {
        schedulerService.addScheduler(schedulerTitle);
        return "redirect:/schedulers";
    }

    @GetMapping("/scheduler/{schedulerId}")
    public String getScheduler(final Model model, @PathVariable("schedulerId") final Long schedulerId) {
        Scheduler scheduler = schedulerRepository.findById(schedulerId).orElse(null);
        model.addAttribute("scheduler", scheduler);
        return "scheduler";
    }

    @GetMapping("/scheduler/edit/{schedulerId}")
    public String getEditScheduler(final Model model, @PathVariable("schedulerId") final Long schedulerId) {
        Scheduler scheduler = schedulerRepository.findById(schedulerId).orElse(null);
        model.addAttribute("scheduler", scheduler);
        return "scheduler-edit";
    }

    @PostMapping("/scheduler/save")
    public String saveScheduler(@ModelAttribute Scheduler scheduler) {
        schedulerService.saveScheduler(scheduler);
        return "redirect:/scheduler/" + scheduler.getId();
    }

    @PostMapping("/scheduler/{schedulerId}/delete")
    public String deleteScheduler(@PathVariable("schedulerId") final Long schedulerId) {
        schedulerService.deleteScheduler(schedulerId);
        return "redirect:/schedulers";
    }

    @GetMapping("/scheduler/{schedulerId}/add/column")
    public String addColumn(@PathVariable("schedulerId") final Long schedulerId) {
        schedulerService.addColumn(schedulerId);
        return "redirect:/scheduler/" + schedulerId;
    }

    @GetMapping("/scheduler/{schedulerId}/remove/column")
    public String removeColumn(@PathVariable("schedulerId") final Long schedulerId) {
        schedulerService.removeColumn(schedulerId);
        return "redirect:/scheduler/" + schedulerId;
    }

    @GetMapping("/scheduler/{schedulerId}/add/row")
    public String addRow(@PathVariable("schedulerId") final Long schedulerId) {
        schedulerService.addRow(schedulerId);
        return "redirect:/scheduler/" + schedulerId;
    }

    @GetMapping("/scheduler/{schedulerId}/remove/row")
    public String removeRow(@PathVariable("schedulerId") final Long schedulerId) {
        schedulerService.removeRow(schedulerId);
        return "redirect:/scheduler/" + schedulerId;
    }
}
