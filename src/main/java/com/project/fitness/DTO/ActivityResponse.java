package com.project.fitness.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.fitness.Model.ActivityType;
import com.project.fitness.Model.Recommendations;
import com.project.fitness.Model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class ActivityResponse {

    private String id;

    private String userId;

    private ActivityType type;
    private Map<String, Object> additionalMatrices;

    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

   }
