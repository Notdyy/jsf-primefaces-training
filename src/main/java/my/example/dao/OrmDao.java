package my.example.dao;

import java.io.Serializable;
import java.util.List;


public interface OrmDao<T extends Serializable> {
	
	/**
     * ทำสิ่งต่าง ๆ ในรูปแบบเก่า
     *
     * @deprecated ใช้ {@link #new()} แทน
     * อย่าลืมลบโค้ดที่เลิกใช้นี้ออกในวันใดวันหนึ่ง
     */
	@Deprecated
	public void setDelegate(Object delegate);
	
	public T find(Object id);
	public T findOne(long id );
	public T findOne(Long id );
	public T findOne(String id );
	
	public List<T> findAll();

	public void create(T entity);
	
	public void update(T entity);

	public void delete(T entity);

	public void deleteById(long entityId);
	public void deleteById(Long entityId);
	public void deleteById(String entityId);
	
	public void refresh(T entity);
}
