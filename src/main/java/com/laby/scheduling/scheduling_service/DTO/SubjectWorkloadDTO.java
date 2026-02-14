package com.laby.scheduling.scheduling_service.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectWorkloadDTO {

    private Long subjectId;
    private String subjectName;
    private long periods;
    private double utilizationPercent;
}
