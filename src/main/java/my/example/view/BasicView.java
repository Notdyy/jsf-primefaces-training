package my.example.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import my.example.model.Product;
import my.example.service.ProductServiceable;

@Named("dtBasicView")
@ViewScoped
public class BasicView implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Product> products;

    @Inject
    private ProductServiceable service;

    @PostConstruct
    public void init() {
        products = service.getProducts(10);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setService(ProductServiceable service) {
        this.service = service;
    }

}