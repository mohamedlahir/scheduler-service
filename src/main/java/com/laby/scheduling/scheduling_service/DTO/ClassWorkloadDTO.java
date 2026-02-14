package com.laby.scheduling.scheduling_service.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassWorkloadDTO {

    private Long classRoomId;
    private long periods;
    private List<SubjectWorkloadDTO> subjects;
}
