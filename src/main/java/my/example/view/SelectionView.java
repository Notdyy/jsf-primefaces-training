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
import my.example.service.ProductService;

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
    private ProductService service;

    @PostConstruct
    public void init() {
        products1 = service.getProducts(10);
        products2 = service.getProducts(10);
        products3 = service.getProducts(10);
        products4 = service.getProducts(10);
        products5 = service.getProducts(10);
        products6 = service.getProducts(10);
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

    public List<Product> getProducts4() {
        return products4;
    }

    public List<Product> getProducts5() {
        return products5;
    }

    public List<Product> getProducts6() {
        return products6;
    }

    public void setService(ProductService service) {
        this.service = service;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<Product> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<Product> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public void onRowSelect(SelectEvent<Product> event) {
        FacesMessage msg = new FacesMessage("Product Selected", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent<Product> event) {
        FacesMessage msg = new FacesMessage("Product Unselected", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

	public ProductService getService() {
		return service;
	}

	public void setProducts1(List<Product> products1) {
		this.products1 = products1;
	}

	public void setProducts2(List<Product> products2) {
		this.products2 = products2;
	}

	public void setProducts3(List<Product> products3) {
		this.products3 = products3;
	}

	public void setProducts4(List<Product> products4) {
		this.products4 = products4;
	}

	public void setProducts5(List<Product> products5) {
		this.products5 = products5;
	}

	public void setProducts6(List<Product> products6) {
		this.products6 = products6;
	}
}