package com.duo.costsharingscheduler.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SchedulerParameters {
    public List<BigDecimal> sumOfColumns = new ArrayList<>();
    public List<BigDecimal> meanOfColumns = new ArrayList<>();

    public SchedulerParameters(Scheduler scheduler) {
        BigDecimal sum;
        BigDecimal mean;
        for (int i = 0; i < scheduler.getColumns().size(); i++) {
            sum = BigDecimal.ZERO;
            mean = BigDecimal.ZERO;
            for (int j = 0; j < scheduler.getRows().size(); j++) {
                sum = sum.add(scheduler.getRows().get(j).getValueFieldList().get(i).getValueField());
                mean = mean.add(scheduler.getRows().get(j).getValueFieldList().get(i).getValueField());
            }
            sumOfColumns.add(sum);
            meanOfColumns.add(mean.divide(BigDecimal.valueOf(scheduler.getRows().size()), 2));
        }
    }
}
