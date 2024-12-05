package com.project.api.report;

import com.project.api.report.dto.ReportCreateDTO;
import com.project.api.report.dto.ReportUpdateDTO;
import com.project.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/report")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;
    
    // TODO: Category 반환하는 API도 있어야 할듯

    // 신고 생성
    @PostMapping
    public ResponseEntity create(@RequestBody ReportCreateDTO reportCreateDTO) {
        var report = reportService.create(reportCreateDTO);
        var response = CommonResponse.builder().code(200).message("신고 생성 성공").data(report).build();
        return ResponseEntity.ok(response);
    }

    // 신고 수정
    @PatchMapping
    public ResponseEntity update(@RequestBody ReportUpdateDTO reportUpdateDTO) {
        var report = reportService.update(reportUpdateDTO);
        var response = CommonResponse.builder().code(200).message("신고 수정 성공").data(report).build();
        return ResponseEntity.ok(response);
    }

    // 신고 삭제
    @DeleteMapping("/{reportId}")
    public ResponseEntity delete(@PathVariable Long reportId) {
        var report = reportService.delete(reportId);
        var response = CommonResponse.builder().code(200).message("신고 삭제 성공").data(report).build();
        return ResponseEntity.ok(response);
    }

    // 신고 조회
    @GetMapping("/{reportId}")
    public ResponseEntity get(@PathVariable Long reportId) {
        var report = reportService.getReport(reportId);
        var response = CommonResponse.builder().code(200).message("신고 조회 성공").data(report).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all/user/{userId}")
    public ResponseEntity getAllByUser(@PathVariable Long userId) {
        var reports = reportService.getAllReportByUser(userId);
        var response = CommonResponse.builder().code(200).message("신고 조회 성공").data(reports).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity getAll() {
        var reports = reportService.getAll();
        var response = CommonResponse.builder().code(200).message("신고 조회 성공").data(reports).build();
        return ResponseEntity.ok(response);
    }
}
