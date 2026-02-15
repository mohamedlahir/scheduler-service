package com.laby.scheduling.scheduling_service.controller;

import com.laby.scheduling.scheduling_service.entity.TimetableEntry;
import com.laby.scheduling.scheduling_service.repository.TimetableEntryRepository;
import com.laby.scheduling.scheduling_service.security.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/scheduler/api/tutor/timetable")
@RequiredArgsConstructor
public class TutorTimetableController {

    private final TimetableEntryRepository timetableEntryRepository;
    private final JWTService jwtService;

    @GetMapping
    public ResponseEntity<List<TimetableEntry>> getTutorTimetable(
            @RequestParam Long weeklyTimetableId,
            HttpServletRequest request
    ) {
        String token = extractToken(request);
        if (token == null) {
            return ResponseEntity.status(401).build();
        }

        String tutorId = jwtService.extractProfileId(token);
        Long schoolId = jwtService.extractSchoolId(token);

        if (tutorId == null || schoolId == null) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(
                timetableEntryRepository
                        .findByWeeklyTimetableIdAndTutorIdAndSchoolId(
                                weeklyTimetableId,
                                tutorId,
                                schoolId
                        )
        );
    }

    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}
