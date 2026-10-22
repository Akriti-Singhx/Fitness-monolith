package com.project.fitness.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.ConnectionBuilder;
import java.time.LocalDateTime;
import java.util.List;

@Entity
//@Data // or can use @Getter and @Setter
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recommendations {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY) // user ko access karenge toh recommendation nhi aayega .
    @JoinColumn(name= "user_id", nullable= false, foreignKey = @ForeignKey(name = "fk_recommendation"))
    @JsonIgnore
    private User user;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name= "activity_id", nullable= false, foreignKey = @ForeignKey(name = "fk_recommendation_activity"))
    @JsonIgnore
    private Activity activity;

    private String type;


    @Column(length = 2000)
    private String recommendation;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> suggestion;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> safety;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> improvement;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}
