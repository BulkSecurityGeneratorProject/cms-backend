package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.State;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the State entity.
 */
public interface StateSearchRepository extends JPASearchRepository<State, Long> {
}
