package com.project.api.report;

import com.project.api.report.report.Report;
import com.project.entity.restraunt.Restaurant;
import com.project.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findByReportId(Long reportId);

    void deleteByReportId(Long reportId);

    Optional<List<Report>> findByUser(User user);

    Optional<List<Report>> findByRestaurant(Restaurant restaurant);
}
