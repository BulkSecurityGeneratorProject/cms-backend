package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.MetaLecture;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the MetaLecture entity.
 */
public interface MetaLectureSearchRepository extends JPASearchRepository<MetaLecture, Long> {
}
