package my.example.converter;

import java.time.Period;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value="customDateTimeConverter", managed = true)
public class CustomDateTimeConverter extends DateTimeConverter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        // แปลงจาก ISO 8601 (เช่น P35Y6M16D) เป็น Period
        if (value == null || value.isEmpty()) {
            return null;
        }
        return Period.parse(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        // แปลง Period เป็นข้อความที่ต้องการ
        if (value == null) {
            return "";
        }
        Period period = (Period) value;
        return period.getYears() + " years " + period.getMonths() + " months and " + period.getDays() + " days.";
    }
}