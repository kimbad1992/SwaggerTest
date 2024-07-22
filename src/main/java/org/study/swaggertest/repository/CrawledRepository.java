package org.study.swaggertest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.study.swaggertest.entity.CrawledData;

public interface CrawledRepository extends JpaRepository<CrawledData, Long> {
}
