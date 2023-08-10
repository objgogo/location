package com.api.location.repository;

import com.api.location.entity.LocationLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationLogRepository extends JpaRepository<LocationLogEntity, Long> {
}
