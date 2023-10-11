package jeju.oneroom.domain.area.repository;

import jeju.oneroom.domain.area.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AreaRepository extends JpaRepository<Area, Long> {
    Optional<Area> findByAreaName(String areaName);
}
