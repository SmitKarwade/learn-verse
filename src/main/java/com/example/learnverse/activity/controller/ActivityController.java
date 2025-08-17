package com.example.learnverse.activity.controller;

import com.example.learnverse.activity.model.Activity;
import com.example.learnverse.activity.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping("/create")
    public ResponseEntity<?> createActivity(@RequestBody Activity activity, Authentication auth) {
        System.out.println("🎯 Controller: Method reached!");
        String tutorId = auth.getName();
        System.out.println("👤 Controller: tutorId = " + tutorId);

        boolean isTutor = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_TUTOR"));
        boolean isUser = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_USER"));

        if (isTutor) {
            Activity saved = activityService.createActivityByTutor(activity, tutorId);
            return ResponseEntity.ok(saved);
        } else if (isUser) {
            return ResponseEntity.status(403).body("Only tutors can create activities.");
        } else {
            return ResponseEntity.status(401).body("Authentication required.");
        }
    }

    @GetMapping("/fetch")
    public ResponseEntity<?> getAllActivities(Authentication auth) {
        System.out.println("🔍 Controller: Fetch activities method reached!");
        String userId = auth.getName();
        System.out.println("👤 Controller: userId = " + userId);

        boolean isUser = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_USER"));
        boolean isTutor = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_TUTOR"));

        if (isUser) {
            List<Activity> activities = activityService.getAllActivitiesForUsers();
            return ResponseEntity.ok(activities);
        } else if (isTutor) {
            return ResponseEntity.status(403).body("Only users (students) can fetch activities.");
        } else {
            return ResponseEntity.status(401).body("Authentication required.");
        }
    }
}