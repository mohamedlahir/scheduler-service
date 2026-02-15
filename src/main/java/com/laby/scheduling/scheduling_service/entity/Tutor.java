package com.laby.scheduling.scheduling_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tutors")
@Getter @Setter @NoArgsConstructor
public class Tutor {

    @Id
    @Column(name = "auth_user_id")
    private String authUserId; // from auth-service (PK)

    private String email;

    private String tutorId; // unique identifier for the tutor

    private Long schoolId;

    private String password;

    private String role;

    private int maxClassesPerDay;

    private String name;

    private String firstName;

    private String lastName;

    private boolean active = true;
}
