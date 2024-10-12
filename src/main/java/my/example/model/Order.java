package my.example.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode // ใช้ @EqualsAndHashCode เพื่อสร้าง equals() และ hashCode() โดยอัตโนมัติ
@ToString
public class Order implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private final int number;
    private final String imagePath;

    public Order(int number, String imagePath) {
        this.number = number;
        this.imagePath = imagePath;
    }
}