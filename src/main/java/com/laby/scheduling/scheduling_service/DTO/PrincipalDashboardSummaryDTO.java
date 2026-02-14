package com.laby.scheduling.scheduling_service.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrincipalDashboardSummaryDTO {

    private Long schoolId;
    private Long weeklyTimetableId;
    private LocalDate weekStartDate;
    private LocalDate weekEndDate;

    private int activeDays;

    private long totalPeriods;
    private long assignedPeriods;
    private long conflictPeriods;

    private int totalTutors;
    private long totalCapacity;

    private double averageUtilizationPercent;
}
