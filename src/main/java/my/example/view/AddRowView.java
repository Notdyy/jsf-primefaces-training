package my.example.view;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

import my.example.model.InventoryStatus;
import my.example.model.Product;
import my.example.service.ProductService;

@Named("dtAddRowView")
@ViewScoped
public class AddRowView implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Product> products1;

    @Inject
    private ProductService service;

    @PostConstruct
    public void init() {
        products1 = service.getClonedProducts(15);
    }

    public List<Product> getProducts1() {
        return products1;
    }

    public void setService(ProductService service) {
        this.service = service;
    }

    public void onRowEdit(RowEditEvent<Product> event) {
        FacesMessage msg = new FacesMessage("Product Edited", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Product> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onAddNew() {
        // Add one new product to the table:
        Product newProduct = new Product((int) (Math.random() * 10000), "f230fh0g3", "New Bamboo Watch",
                "Product Description", "bamboo-watch.jpg", 100, "Accessories", 24, InventoryStatus.INSTOCK, 5);
        products1.add(newProduct);

        FacesMessage msg = new FacesMessage("New Product added", String.valueOf(newProduct.getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}