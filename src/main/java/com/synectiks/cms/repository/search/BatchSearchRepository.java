package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Batch;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Batch entity.
 */
public interface BatchSearchRepository extends JPASearchRepository<Batch, Long> {
}
