package my.example.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import lombok.Getter;
import lombok.Setter;
import my.example.model.Product;
import my.example.service.ProductServiceable;

@Named("dtSelectionView")
@ViewScoped
@Getter
@Setter
public class SelectionView implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Product> products1;
    private List<Product> products2;
    private List<Product> products3;
    private List<Product> products4;
    private List<Product> products5;
    private List<Product> products6;
    private Product selectedProduct;
    private List<Product> selectedProducts;

    @Inject
    private ProductServiceable service;

    @PostConstruct
    public void init() {
        products1 = service.getProducts(10);
        products2 = service.getProducts(10);
        products3 = service.getProducts(10);
        products4 = service.getProducts(10);
        products5 = service.getProducts(10);
        products6 = service.getProducts(10);
    }

    public void onRowSelect(SelectEvent<Product> event) {
        FacesMessage msg = new FacesMessage("Product Selected", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent<Product> event) {
        FacesMessage msg = new FacesMessage("Product Unselected", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}