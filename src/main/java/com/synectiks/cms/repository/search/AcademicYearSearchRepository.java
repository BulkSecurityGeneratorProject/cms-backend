package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the AcademicYear entity.
 */
public interface AcademicYearSearchRepository extends JPASearchRepository<AcademicYear, Long> {
}
