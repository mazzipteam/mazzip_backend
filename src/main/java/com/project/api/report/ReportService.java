package com.project.api.report;

import com.project.api.report.dto.ReportCreateDTO;
import com.project.api.report.dto.ReportUpdateDTO;
import com.project.api.report.report.Category;
import com.project.api.report.report.Report;
import com.project.api.restaurant.RestaurantService;
import com.project.api.review.ReviewService;
import com.project.api.user.UserService;
import com.project.exception.ControlledException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.project.api.report.ReportErrorMessage.*;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReviewService reviewService;
    private final UserService userService;
    private final ReportRepository reportRepository;
    private final RestaurantService restaurantService;

    public Report create(ReportCreateDTO reportCreateDTO) {
        var review = reviewService.getReview(reportCreateDTO.getReviewId());
        var user = userService.getUser(reportCreateDTO.getUserId());

        var report = Report.builder()
                .review(review)
                .user(user)
                .category(reportCreateDTO.getCategory())
                .title(reportCreateDTO.getTitle())
                .description(reportCreateDTO.getDescription())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        reportRepository.save(report);
        return report;
    }

    public Report update(ReportUpdateDTO reportUpdateDTO) {
        var report = getReport(reportUpdateDTO.getReportId());

        if(reportUpdateDTO.getCategory() != null) {
            // 1. [예외처리] 잘못된 형식의 카테고리
            Category category;
            try {
                category = Category.valueOf(reportUpdateDTO.getCategory());
            } catch (IllegalArgumentException e) {
                throw new ControlledException(CATEGORY_OF_INCORRECT_FORMAT);
            }

            report.setCategory(category);
        }

        if(reportUpdateDTO.getTitle() != null)
            report.setTitle(reportUpdateDTO.getTitle());

        if(reportUpdateDTO.getDescription() != null)
            report.setDescription(reportUpdateDTO.getDescription());

        reportRepository.save(report);
        return report;
    }

    public Report delete(Long reportId) {
        var report = getReport(reportId);

        reportRepository.deleteByReportId(reportId);
        return report;
    }

    public Report getReport(Long reportId) {
        var report = reportRepository.findByReportId(reportId)
                .orElseThrow(()->new ControlledException(REPORT_NOT_FOUND));

        return report;
    }

    public List<Report> getAllReportByUser(Long userId) {
        var user = userService.getUser(userId);
        var reports = reportRepository.findByUser(user)
                .orElseThrow(()->new ControlledException(REPORT_NOT_FOUND_BY_USER));

        return reports;
    }

    public List<Report> getAllReportByRestaurant(Long restaurantId) {
        var restaurant = restaurantService.getRestaurant(restaurantId);
        var reports = reportRepository.findByRestaurant(restaurant)
                .orElseThrow(()->new ControlledException(REPORT_NOT_FOUND_BY_RESTAURANT));

        return reports;
    }
}
