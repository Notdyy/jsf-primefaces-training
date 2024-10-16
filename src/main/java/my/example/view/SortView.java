package my.example.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import my.example.model.Product;
import my.example.service.ProductServiceable;

@Named("dtSortView")
@ViewScoped
public class SortView implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Product> products1;
    private List<Product> products2;
    private List<Product> products3;
    private List<SortMeta> sortBy;

    @Inject
    private ProductServiceable service;

    @PostConstruct
    public void init() {
        products1 = service.getProducts(10);
        products2 = service.getProducts(10);
        products3 = service.getProducts(50);

        sortBy = new ArrayList<>();
        sortBy.add(SortMeta.builder()
                .field("name")
                .order(SortOrder.ASCENDING)
                .build());

        sortBy.add(SortMeta.builder()
                .field("category")
                .order(SortOrder.ASCENDING)
                .priority(1)
                .build());
    }

    public List<Product> getProducts1() {
        return products1;
    }

    public List<Product> getProducts2() {
        return products2;
    }

    public List<Product> getProducts3() {
        return products3;
    }

    public List<SortMeta> getSortBy() {
        return sortBy;
    }

    public void setService(ProductServiceable service) {
        this.service = service;
    }
}