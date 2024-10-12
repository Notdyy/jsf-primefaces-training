package my.example.jpa;

import java.io.Serializable;

import javax.persistence.EntityManager;

public abstract class JpaService implements Serializable, DatabaseServiceable {

	private static final long serialVersionUID = 1L;
	
	public abstract EntityManager getEm();

	public abstract void setEm(EntityManager em);

	protected JpaService(){
	}
	
	protected JpaService(EntityManager em){
		this.setEm(em);
	}
	
	public boolean isTransactionActive() {
        return this.getEm() != null && this.getEm().isOpen() && this.getEm().getTransaction().isActive();
    }
	
	public void rollback() {
		if (this.getEm() != null && this.getEm().isOpen() && this.getEm().getTransaction().isActive()) {
			this.getEm().getTransaction().rollback();
		}
	}

	public void begin() {
		this.getEm().getTransaction().begin();
	}

	public void commit() {
		this.getEm().getTransaction().commit();
	}

	public void flush() {
		this.getEm().flush();
	}

	
	/**
     * ทำสิ่งต่าง ๆ ในรูปแบบเก่า
     *
     * @deprecated ใช้ {@link #new()} แทน
     * อย่าลืมลบโค้ดที่เลิกใช้นี้ออกในวันใดวันหนึ่ง
     */
	@Deprecated
	@Override
	public void closeConnection() {
		
	}
	
	/**
     * ทำสิ่งต่าง ๆ ในรูปแบบเก่า
     *
     * @deprecated ใช้ {@link #new()} แทน
     * อย่าลืมลบโค้ดที่เลิกใช้นี้ออกในวันใดวันหนึ่ง
     */
	@Deprecated
	@Override
	public void createConnection(Object... params) {
		
	}
}
