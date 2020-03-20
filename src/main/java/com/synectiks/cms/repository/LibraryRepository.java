package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.Library;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the Library entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LibraryRepository extends JPASearchRepository<Library, Long> {

}
