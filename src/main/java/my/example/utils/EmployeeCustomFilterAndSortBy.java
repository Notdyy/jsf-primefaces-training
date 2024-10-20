package my.example.utils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import my.example.entity.EmployeeData;

public class EmployeeCustomFilterAndSortBy {

    private EmployeeCustomFilterAndSortBy() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Custom sorting logic based on a map of SortMeta and creates corresponding JPA Order objects.
     *
     * @param sortBy a map of SortMeta representing the sorting criteria
     * @param cb the CriteriaBuilder used to create the Order objects
     * @param adj the Root object representing the EmployeeData entity in the query
     * @return a list of Order objects representing the sorting order
     */
    public static List<Order> customSortBy(Map<String, SortMeta> sortBy, CriteriaBuilder cb, Root<EmployeeData> adj) {
        return sortBy.values().stream()
                .filter(e -> e.getField() != null && !e.getField().isEmpty())
                .map(e -> {
                    switch (e.getOrder()) {
                        case ASCENDING:
                            return cb.asc(adj.get(e.getField()));
                        case DESCENDING:
                            return cb.desc(adj.get(e.getField()));
                        case UNSORTED:
                            break;
                        default:
                            throw new AssertionError(e.getOrder().toString());
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Custom filter logic based on a map of FilterMeta and creates corresponding JPA Predicate objects.
     *
     * @param filterBy a map of FilterMeta representing the filtering criteria
     * @param cb the CriteriaBuilder used to create the Predicate objects
     * @param adj the Root object representing the EmployeeData entity in the query
     * @return a list of Predicate objects representing the filter conditions
     */
    public static List<Predicate> customFilterMatchMode(Map<String, FilterMeta> filterBy, CriteriaBuilder cb, Root<EmployeeData> adj) {
        return filterBy.values().stream()
                .filter(e -> StringUtils.isNotEmpty(e.getField()) && e.getFilterValue() != null)
                .map(e -> {
                    switch (e.getMatchMode()) {
                        case STARTS_WITH:
                            return cb.like(cb.lower(adj.get(e.getField())), ((String) e.getFilterValue()).toLowerCase() + "%");
                        case ENDS_WITH:
                            return cb.like(cb.lower(adj.get(e.getField())), "%" + ((String) e.getFilterValue()).toLowerCase());
                        case CONTAINS:
                            return cb.like(cb.lower(adj.get(e.getField())), "%" + ((String) e.getFilterValue()).toLowerCase() + "%");
                        default:
                            return cb.conjunction();
                    }
                }).filter(Objects::nonNull).collect(Collectors.toList());
    }
}