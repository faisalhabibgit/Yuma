package com.yuma.app.repository;

import com.yuma.app.document.CombinationReport;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CombinationReportRepository extends MongoRepository<CombinationReport, String> {
	
}
