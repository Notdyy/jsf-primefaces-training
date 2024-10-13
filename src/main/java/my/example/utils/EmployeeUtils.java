package my.example.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.time.LocalDate;
import java.util.Collection;

import javax.faces.context.FacesContext;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.filter.FilterConstraint;
import org.primefaces.util.LocaleUtils;

import com.github.javafaker.Faker;

import my.example.model.Employee;
import my.example.model.EmployeeCriteria;

public class EmployeeUtils {
	
	private static final Faker faker = new Faker();
	
	private EmployeeUtils() {
		throw new IllegalStateException("utils class");
	}
	
	public static Employee createMockEmployee(int minAge, int maxAge) {
	    int randomAge = faker.number().numberBetween(minAge, maxAge);
	    LocalDate hireDate = LocalDate.now().minusYears(randomAge).withDayOfYear(faker.number().numberBetween(1, 365));
	    return Employee.builder()
	                   .firstName(faker.name().firstName())
	                   .lastName(faker.name().lastName())
	                   .birthDate(hireDate)
	                   .build();
	}
	
	public static EmployeeCriteria createMockEmployeeCriteria(int minAge, int maxAge) {
	    int randomAge = faker.number().numberBetween(minAge, maxAge);
	    LocalDate hireDate = LocalDate.now().minusYears(randomAge).withDayOfYear(faker.number().numberBetween(1, 365));
	    return EmployeeCriteria.builder()
	                   .firstName(faker.name().firstName())
	                   .lastName(faker.name().lastName())
	                   .birthDate(hireDate)
	                   .build();
	}
	
	
	public static boolean filter(FacesContext context, Collection<FilterMeta> filterBy, Object o) {
        boolean matching = true;

        for (FilterMeta filter : filterBy) {
            FilterConstraint constraint = filter.getConstraint();
            Object filterValue = filter.getFilterValue();

            try {
                Object columnValue = String.valueOf(getPropertyValueViaReflection(o, filter.getField()));
                matching = constraint.isMatching(context, columnValue, filterValue, LocaleUtils.getCurrentLocale());
            }
            catch (ReflectiveOperationException | IntrospectionException e) {
                matching = false;
            }

            if (!matching) {
                break;
            }
        }

        return matching;
    }
	
	public static final Object getPropertyValueViaReflection(Object o, String field)
			throws ReflectiveOperationException, IllegalArgumentException, IntrospectionException {
		return new PropertyDescriptor(field, o.getClass()).getReadMethod().invoke(o);
	}

}
