package my.example.view;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
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

    private static final long serialVersionUID = 1L;

    private List<Product> products1;

    @Inject
    private ProductService service;

    private Random random = new Random(); 

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
        
    	Product newProduct = Product.builder()
                					.id(random.nextInt(10000))
                					.code("f230fh0g3")
                					.name("New Bamboo Watch")
                					.description("Product Description")
                					.image("bamboo-watch.jpg")
                					.price(100)
                					.category("Accessories")
                					.quantity(24)
                					.inventoryStatus(InventoryStatus.INSTOCK)
                					.rating(5)
                					.build();
        products1.add(newProduct);

        FacesMessage msg = new FacesMessage("New Product added", String.valueOf(newProduct.getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
