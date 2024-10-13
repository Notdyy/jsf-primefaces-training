package my.example.comparator;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import my.example.model.Employee;
import my.example.utils.EmployeeUtils;

public class LazySorter implements Comparator<Employee> {

    private String sortField;
    private SortOrder sortOrder;

    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @SuppressWarnings("unchecked")
	@Override
    public int compare(Employee customer1, Employee customer2) {
        try {
            Object value1 = EmployeeUtils.getPropertyValueViaReflection(customer1, sortField);
            Object value2 = EmployeeUtils.getPropertyValueViaReflection(customer2, sortField);

            int value = ((Comparable<Object>) value1).compareTo(value2);

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}
