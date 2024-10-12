package my.example.jpa;

public interface DatabaseServiceable {

    /**
     * ทำสิ่งต่าง ๆ ในรูปแบบเก่า
     *
     * @deprecated ใช้ {@link #new()} แทน
     * อย่าลืมลบโค้ดที่เลิกใช้นี้ออกในวันใดวันหนึ่ง
     */
    @Deprecated
    public void createConnection(Object... params);

    /**
     * ทำสิ่งต่าง ๆ ในรูปแบบเก่า
     *
     * @deprecated ใช้ {@link #new()} แทน
     * อย่าลืมลบโค้ดที่เลิกใช้นี้ออกในวันใดวันหนึ่ง
     */
    @Deprecated
    public void closeConnection();

    public void rollback();
    
    public void begin();
    
    public void flush();
    
    public void commit();
}