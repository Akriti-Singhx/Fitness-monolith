package com.project.fitness.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
//@Data // or can use @Getter and @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "my_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private String id;

    @Column(unique= true)
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserRole role= UserRole.USER;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime UpdatedAt;

    //--------------------  MAPPING --------------------------------------------------------
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Activity> activities = new ArrayList<>();
    // cascade = CascadeType.ALL user me koi operation hoga toh activity me bhi hoga
   // orphanRemoval = true -> user me koi chiz deleet hui toh activity me bhi hogi
    //@JsonIgnore  --> json type me nhi aayega

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Recommendations> recommendations = new ArrayList<>();
//---------------------------------------------------------------------------------------
}