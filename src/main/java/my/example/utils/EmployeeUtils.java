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
        throw new IllegalStateException("Utility class");
    }

    /**
     * Creates a mock Employee object with a randomly generated age between the specified min and max age.
     *
     * @param minAge the minimum age for the mock employee
     * @param maxAge the maximum age for the mock employee
     * @return a mock Employee object
     */
    public static Employee createMockEmployee(int minAge, int maxAge) {
        int randomAge = faker.number().numberBetween(minAge, maxAge);
        LocalDate hireDate = LocalDate.now().minusYears(randomAge).withDayOfYear(faker.number().numberBetween(1, 365));
        return Employee.builder()
                       .firstName(faker.name().firstName())
                       .lastName(faker.name().lastName())
                       .birthDate(hireDate)
                       .build();
    }

    /**
     * Creates a mock EmployeeCriteria object with a randomly generated age between the specified min and max age.
     *
     * @param minAge the minimum age for the mock employee criteria
     * @param maxAge the maximum age for the mock employee criteria
     * @return a mock EmployeeCriteria object
     */
    public static EmployeeCriteria createMockEmployeeCriteria(int minAge, int maxAge) {
        int randomAge = faker.number().numberBetween(minAge, maxAge);
        LocalDate hireDate = LocalDate.now().minusYears(randomAge).withDayOfYear(faker.number().numberBetween(1, 365));
        return EmployeeCriteria.builder()
                               .firstName(faker.name().firstName())
                               .lastName(faker.name().lastName())
                               .birthDate(hireDate)
                               .build();
    }

    /**
     * Filters a collection of objects based on the provided filter metadata.
     *
     * @param context the FacesContext used in the filtering process
     * @param filterBy the collection of FilterMeta used to apply constraints
     * @param o the object being filtered
     * @return true if the object matches the filter criteria, false otherwise
     */
    public static boolean filter(FacesContext context, Collection<FilterMeta> filterBy, Object o) {
        boolean matching = true;

        for (FilterMeta filter : filterBy) {
            FilterConstraint constraint = filter.getConstraint();
            Object filterValue = filter.getFilterValue();

            try {
                Object columnValue = String.valueOf(getPropertyValueViaReflection(o, filter.getField()));
                matching = constraint.isMatching(context, columnValue, filterValue, LocaleUtils.getCurrentLocale());
            } catch (ReflectiveOperationException | IntrospectionException e) {
                matching = false;
            }

            if (!matching) {
                break;
            }
        }

        return matching;
    }

    /**
     * Retrieves the value of a property from an object using reflection.
     *
     * @param o the object from which to retrieve the property value
     * @param field the name of the field whose value is to be retrieved
     * @return the value of the specified property
     * @throws ReflectiveOperationException if there is an issue with reflection
     * @throws IllegalArgumentException if the arguments are invalid
     * @throws IntrospectionException if an issue occurs during introspection
     */
    public static final Object getPropertyValueViaReflection(Object o, String field)
            throws ReflectiveOperationException, IllegalArgumentException, IntrospectionException {
        return new PropertyDescriptor(field, o.getClass()).getReadMethod().invoke(o);
    }
}