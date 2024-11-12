package com.project.api.report;

import com.project.api.report.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
    Optional<Report> findByReportId(Long reportId);

    void deleteByReportId(Long reportId);
}
