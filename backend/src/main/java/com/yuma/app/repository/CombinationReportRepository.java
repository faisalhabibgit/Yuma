package com.yuma.app.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yuma.app.document.CombinationReport;

public interface CombinationReportRepository extends MongoRepository<CombinationReport, String> {
	Optional<CombinationReport> findTopByOrderByCreatedOnDesc();
	Optional<CombinationReport> findCombinationReportByCreatedOnBetween(Date start, Date end);
	List<CombinationReport> findAllByCreatedOnBetween(Date start, Date end);
}
