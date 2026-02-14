package com.laby.scheduling.scheduling_service.controller;

import com.laby.scheduling.scheduling_service.DTO.PrincipalDashboardResponseDTO;
import com.laby.scheduling.scheduling_service.DTO.PrincipalDashboardSummaryDTO;
import com.laby.scheduling.scheduling_service.DTO.TeacherWorkloadDTO;
import com.laby.scheduling.scheduling_service.service.PrincipalDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/scheduler/api/principal/dashboard")
@RequiredArgsConstructor
public class PrincipalDashboardController {

    private final PrincipalDashboardService principalDashboardService;

    @GetMapping("/overview")
    public ResponseEntity<PrincipalDashboardResponseDTO> getOverview(
            @RequestParam Long schoolId,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate weekStartDate
    ) {
        return ResponseEntity.ok(
                principalDashboardService.getDashboard(schoolId, weekStartDate)
        );
    }

    @GetMapping("/summary")
    public ResponseEntity<PrincipalDashboardSummaryDTO> getSummary(
            @RequestParam Long schoolId,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate weekStartDate
    ) {
        return ResponseEntity.ok(
                principalDashboardService.getDashboard(schoolId, weekStartDate).getSummary()
        );
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<TeacherWorkloadDTO>> getTeachers(
            @RequestParam Long schoolId,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate weekStartDate
    ) {
        return ResponseEntity.ok(
                principalDashboardService.getDashboard(schoolId, weekStartDate).getTeachers()
        );
    }
}
