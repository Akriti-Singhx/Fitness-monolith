package com.project.fitness.Service;

import com.project.fitness.DTO.ActivityRequest;
import com.project.fitness.DTO.ActivityResponse;
import com.project.fitness.Model.Activity;
import com.project.fitness.Model.User;
import com.project.fitness.Repository.ActivityRepository;
import com.project.fitness.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public ActivityResponse trackActivity(ActivityRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("invalid user id" + request.getUserId()));

        Activity activity = Activity.builder()
                .user(user)
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startTime(request.getStartTime())
                .additionalMatrices(request.getAdditionalMatrices())
                .build();


        Activity savedActivity = activityRepository.save(activity);
        return mapToResponse(savedActivity);
    }

    private ActivityResponse mapToResponse(Activity Activity) {
        ActivityResponse response = new ActivityResponse();
        response.setId(Activity.getId());
        response.setUserId(Activity.getUser().getId());
        response.setType(Activity.getType());
        response.setAdditionalMatrices(Activity.getAdditionalMatrices());
        response.setDuration(Activity.getDuration());
        response.setCaloriesBurned(Activity.getCaloriesBurned());
        response.setStartTime(Activity.getStartTime());
        response.setCreatedAt(Activity.getCreatedAt());
        response.setUpdatedAt(Activity.getUpdatedAt());

        return response;

    }
    public List<ActivityResponse> getUserActivities(String userId){
        List<Activity> activityList = activityRepository.findByUserId(userId);
        return activityList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
