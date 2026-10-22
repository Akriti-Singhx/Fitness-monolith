package com.project.fitness.Repository;

import com.project.fitness.Model.Activity;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, String>{
    List<Activity> findByUserId(String userId);
}
