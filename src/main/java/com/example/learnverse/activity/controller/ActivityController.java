package com.example.learnverse.activity.controller;

import com.example.learnverse.activity.model.Activity;
import com.example.learnverse.activity.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping
    public Activity createActivity(@RequestBody Activity activity, Authentication auth) {
        System.out.println("🎯 Controller: Method reached!");
        String tutorId = auth.getName();
        System.out.println("👤 Controller: tutorId = " + tutorId);
        return activityService.createActivityByTutor(activity, tutorId);
    }
}

