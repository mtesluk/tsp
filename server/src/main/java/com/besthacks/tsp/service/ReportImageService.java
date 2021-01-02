package com.besthacks.tsp.service;

import com.besthacks.tsp.entity.ReportImage;
import com.besthacks.tsp.exception.TspException;
import com.besthacks.tsp.repository.ReportImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@AllArgsConstructor
@Transactional
public class ReportImageService {

    private ReportImageRepository repository;

    public void saveImage(Long reportId, MultipartFile file) {
        try {
            repository.save(ReportImage.builder()
                    .reportId(reportId)
                    .filename(file.getOriginalFilename() == null ? "EMPTY_FILE_NAME" : StringUtils.cleanPath(file.getOriginalFilename()))
                    .fileType(file.getContentType())
                    .data(file.getBytes())
                    .build());
        } catch (IOException ex) {
            throw new TspException("Failed to save image for report " + reportId, BAD_REQUEST);
        }
    }

    public ReportImage getImageByReportId(Long reportId) {
        return repository.findByReportId(reportId)
                .orElseThrow(() -> new TspException("No image found for report " + reportId, BAD_REQUEST));
    }
}
