package my.example.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import lombok.Getter;
import lombok.Setter;
import my.example.dao.OrmDao;

@Getter
@Setter
public abstract class AbstractJpa<T extends Serializable> implements OrmDao<T> {

	private Class<T> clazz;

	public abstract EntityManager getEm();
	
	/**
     * ทำสิ่งต่าง ๆ ในรูปแบบเก่า
     *
     * @deprecated ใช้ {@link #new()} แทน
     * อย่าลืมลบโค้ดที่เลิกใช้นี้ออกในวันใดวันหนึ่ง
     */
	@Deprecated
	public void setDelegate(Object delegate) {
	}
	
	public T find(Object id){
		return this.getEm().find(this.clazz, id);
	}
	
	public T findOne(long id ){
		return this.getEm().find(this.clazz, id );
	}

	public T findOne(Long id ){
		return this.getEm().find(this.clazz, id );
	}
	
	public T findOne(String id ){
		return this.getEm().find(this.clazz, id );
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findAll() {
		CriteriaQuery cq = this.getEm().getCriteriaBuilder().createQuery();
        cq.select(cq.from(clazz));
        return this.getEm().createQuery(cq).getResultList();
	}

	public void create(T entity) {
		this.getEm().persist(entity);
	}

	public void update(T entity) {
		this.getEm().merge(entity);
	}

	public void delete(T entity) {
		this.getEm().remove(entity);
	}

	public void deleteById(long entityId) {
		T entity = findOne(entityId);
		delete(entity);
	}
	public void deleteById(Long entityId) {
		T entity = findOne(entityId);
		delete(entity);
	}
	public void deleteById(String entityId) {
		T entity = findOne(entityId);
		delete(entity);
	}
	
	public void refresh(T entity){
		this.getEm().refresh(entity);
	}
}
