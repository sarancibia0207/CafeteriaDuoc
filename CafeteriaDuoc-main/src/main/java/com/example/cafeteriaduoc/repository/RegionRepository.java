package com.example.cafeteriaduoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cafeteriaduoc.model.Region;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

}
