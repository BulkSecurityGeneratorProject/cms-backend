package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Library;
import com.synectiks.cms.utils.JPASearchRepository;
/**
 * Spring Data Elasticsearch repository for the Library entity.
 */
public interface LibrarySearchRepository extends JPASearchRepository<Library, Long> {
}
