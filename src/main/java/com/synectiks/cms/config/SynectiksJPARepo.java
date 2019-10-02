/**
 * 
 */
package com.synectiks.cms.config;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.synectiks.cms.domain.ESEvent;
import com.synectiks.cms.domain.ESEvent.EventType;
import com.synectiks.cms.utils.IUtils;

/**
 * @author Rajesh Upadhyay
 */
@Repository
public class SynectiksJPARepo<T, ID extends Serializable>
		extends SimpleJpaRepository<T, ID> {

	private static final Logger logger = LoggerFactory.getLogger(SynectiksJPARepo.class);

	@Autowired
	private Environment env;
	@Autowired
	private RestTemplate rest;

	public SynectiksJPARepo(JpaEntityInformation<T, ?> entityInformation,
			EntityManager entityManager) {
		super(entityInformation, entityManager);
	}

	@Override
	public void deleteById(ID id) {
		super.deleteById(id);
		T entity = super.getOne(id);
		fireEvent(EventType.DELETE, entity);
	}

	@Override
	public void delete(T entity) {
		super.delete(entity);
		fireEvent(EventType.DELETE, entity);
	}

	@Override
	public void deleteAll(Iterable<? extends T> entities) {
		super.deleteAll(entities);
		if (!IUtils.isNull(entities)) {
			entities.forEach(entity -> {
				fireEvent(EventType.DELETE, entity);
			});
		}
	}

	@Override
	public void deleteInBatch(Iterable<T> entities) {
		super.deleteInBatch(entities);
		if (!IUtils.isNull(entities)) {
			entities.forEach(entity -> {
				fireEvent(EventType.DELETE, entity);
			});
		}
	}

	@Override
	public void deleteAll() {
		List<T> entities = super.findAll();
		super.deleteAll();
		if (!IUtils.isNull(entities)) {
			entities.forEach(entity -> {
				fireEvent(EventType.DELETE, entity);
			});
		}
	}

	@Override
	public void deleteAllInBatch() {
		List<T> entities = super.findAll();
		super.deleteAllInBatch();
		if (!IUtils.isNull(entities)) {
			entities.forEach(entity -> {
				fireEvent(EventType.DELETE, entity);
			});
		}
	}

	@Override
	public <S extends T> S save(S entity) {
		S ent = super.save(entity);
		fireEvent(EventType.CREATE, ent);
		return ent;
	}

	@Override
	public <S extends T> S saveAndFlush(S entity) {
		S ent = super.saveAndFlush(entity);
		fireEvent(EventType.CREATE, ent);
		return ent;
	}

	@Override
	public <S extends T> List<S> saveAll(Iterable<S> entities) {
		List<S> lst = super.saveAll(entities);
		if (!IUtils.isNull(lst)) {
			lst.forEach(entity -> {
				fireEvent(EventType.CREATE, entity);
			});
		}
		return lst;
	}

	private void fireEvent(EventType type, T entity) {
		logger.info(type + ": " + IUtils.getStringFromValue(entity));
		if (!IUtils.isNull(entity)) {
			ESEvent event = ESEvent.builder(type, entity).build();
			String res = null;
			try {
				res = IUtils.sendGetRestRequest(rest, IUtils.getValueByKey(
						env, IUtils.KEY_KAFKA_CONFIG, IUtils.URL_KAFKA_URL),
						IUtils.getRestParamMap(IUtils.PRM_TOPIC,
								IUtils.getValueByKey(env, IUtils.KEY_KAFKA_TOPIC, "cms"),
								IUtils.PRM_MSG,
								IUtils.getStringFromValue(event)), String.class);
			} catch(Exception ex) {
				logger.error(ex.getMessage(), ex);
				res = null;
			}
			if (IUtils.isNullOrEmpty(res)) {
				// TODO: We failed to record operation, store it for processing later.
			}
		} else {
			logger.error("Entity should not be null");
		}
	}

}
