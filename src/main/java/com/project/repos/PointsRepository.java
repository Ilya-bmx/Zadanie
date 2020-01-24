package com.project.repos;


import com.project.entities.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointsRepository extends JpaRepository<Point, Integer> {
    List<Point> findAllByCity(String city);
}
