package com.project.fitness.Service;

import com.project.fitness.DTO.RecommendationRequest;
import com.project.fitness.Model.Activity;
import com.project.fitness.Model.Recommendations;
import com.project.fitness.Model.User;
import com.project.fitness.Repository.ActivityRepository;
import com.project.fitness.Repository.RecommendationRepository;
import com.project.fitness.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class RecommendationService {

    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private final RecommendationRepository recommendationRepository;

    public Recommendations generateRecommendation(RecommendationRequest request){
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("user not found " + request.getUserId()));

        Activity activity = activityRepository.findById(request.getActivityId())
                .orElseThrow(() -> new RuntimeException("Activity not found " + request.getActivityId()));

        Recommendations recommendations = Recommendations.builder()
                .user(user)
                .activity(activity)
                .improvement(request.getImprovements())
                .suggestion(request.getSuggestions())
                .safety(request.getSafety())
                .build();

        Recommendations savedRecommendation = recommendationRepository.save(recommendations);
        return savedRecommendation;
    }

    public List<Recommendations> getUserRecommendation(String userId) {
        return recommendationRepository.findByUserId(userId);
    }

    public List<Recommendations> getActivityRecommendation(String activityId) {
        return recommendationRepository.findByActivityId(activityId);
    }
}
