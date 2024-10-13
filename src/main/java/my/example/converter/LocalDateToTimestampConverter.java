package my.example.converter;

import java.sql.Timestamp;
import java.time.LocalDate;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateToTimestampConverter implements AttributeConverter<LocalDate, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        // แปลง LocalDate เป็น Timestamp
        return Timestamp.valueOf(localDate.atStartOfDay());
    }

    @Override
    public LocalDate convertToEntityAttribute(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        // แปลง Timestamp เป็น LocalDate
        return timestamp.toLocalDateTime().toLocalDate();
    }
}