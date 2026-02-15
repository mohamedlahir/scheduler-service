package com.laby.scheduling.scheduling_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laby.scheduling.scheduling_service.DTO.AuthCommanDataModel;
import com.laby.scheduling.scheduling_service.entity.Tutor;
import com.laby.scheduling.scheduling_service.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TutorCRUDService {

   private final TutorRepository tutorRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }


        public ResponseEntity<String> createTutor(Tutor tutor) {
            String profileIDStr = UUID.randomUUID().toString();
            tutor.setAuthUserId(profileIDStr); // set authUserId from request
            tutor.setPassword(encoder().encode(tutor.getPassword())); // set default password (should be changed by tutor later)
            tutorRepository.save(tutor); // save tutor to DB

            AuthCommanDataModel authData = new AuthCommanDataModel();

            authData.setEmail(tutor.getEmail());
            authData.setPassword(tutor.getPassword()); // send raw password for authentication service to hash
            authData.setRole(tutor.getRole());
            authData.setProfileID(tutor.getAuthUserId());
            authData.setSchoolId(tutor.getSchoolId());


            // Serialize Users to JSON and send as String to avoid ClassCastException with StringSerializer

            try {
                ObjectMapper om = new ObjectMapper();
                String payload = om.writeValueAsString(authData);
                kafkaTemplate.send("tutor-profile-creation", payload);

            } catch (JsonProcessingException e) {
                return ResponseEntity.status(500).body("Failed to serialize user data for Kafka: " + e.getMessage());
            }
            return ResponseEntity.ok("Tutor created successfully with ID: " + profileIDStr);
        }

}
