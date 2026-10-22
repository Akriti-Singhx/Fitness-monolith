package com.project.fitness.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity   // activity ek database entity hai.
@Getter
@Setter
//@Data // or can use @Getter and @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)  // isme hum sirf object banayenge aur hibernate khud ek unique id bana k database me store kr dega
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)     // ek hi user ki kayi sari activity ho sakti hai.
    @JoinColumn(name= "user_id" , nullable = false, foreignKey =  @ForeignKey(name = "fk_activity_user"))
    @JsonIgnore
    private User user;

    @Enumerated(EnumType.STRING) // enum k sath database me string store hogi na ki uska sirf number
    private ActivityType type;


    @JdbcTypeCode(SqlTypes.JSON)  // hibernate ko batata hai ki ye field JSON type me
                                   // store hogi aur wapas padhte waqt map me convert karo
    @Column(columnDefinition = "json") // column definition mtlb column ka datatype .jo ki json hoga.
    private Map<String, Object> additionalMatrices;

    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Recommendations> recommendations = new ArrayList<>();
}

// @JoinColumn(name= "user_id" , nullable = false, foreignKey =  @ForeignKey(name = "fk_activity_user"))
// do tables hai ek user-table aur dusra activity-table . activity table me user ki id aur activity hogi butactivity kon se user ki hai ye pata krne k liye activity-table me ek aur column banega jo ki user_id nam ka hoga .
// nullable= false--> activity bina user k nahi ban sakti.

// @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Recommendations> recommendations = new ArrayList<>();
//ek activity ki bahut sari recommendation.
// cascade = CascadeType.ALL,--> activity save ya dlete hogi toh recommendation bhi save ya dlete hogi .
// orphanRemoval = true --> agar list se recommendation hategi toh database se bhi delete hogi.
