package com.laby.scheduling.scheduling_service.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TutorTimetableRequestDTO {

    private String tutorId;
    private Long weeklyTimetableId;
    private Long schoolId;
}
