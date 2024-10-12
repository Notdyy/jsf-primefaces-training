package my.example.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import my.example.dto.ProductsDto;
import my.example.model.InventoryStatus;
import my.example.model.Product;
import my.example.service.ProductServiceable;

@ApplicationScoped
public class ProductServiceimpl implements ProductServiceable {
	
	private List<Product> products;
    private Random rand = new Random();
    
    private static final String PROD_DESC_NAME = "Product Description";
    private static final String ACCESSORIES_NAME = "Accessories";
    private static final String CLOTHING_NAME = "Clothing";
    private static final String ELECTRONICS_NAME = "Electronics";
    private static final String FITNESS_NAME = "Fitness";

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(ProductsDto.createProductMockService(Product.builder().id(1000).code("f230fh0g3").name("Logitech Gpro").description(PROD_DESC_NAME).image("gpro1.jpg").price(65).category(ACCESSORIES_NAME).quantity(24).inventoryStatus(InventoryStatus.INSTOCK).rating(5).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1001).code("nvklal433").name("Logitech Gpro x Superlight").description(PROD_DESC_NAME).image("black-watch.jpg").price(72).category(ACCESSORIES_NAME).quantity(61).inventoryStatus(InventoryStatus.INSTOCK).rating(4).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1002).code("zz21cz3c1").name("Logitech Gpro x Superlight 2").description(PROD_DESC_NAME).image("blue-band.jpg").price(79).category(FITNESS_NAME).quantity(2).inventoryStatus(InventoryStatus.LOWSTOCK).rating(3).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1003).code("244wgerg2").name("Logitech Gpro Keyboard TKL").description(PROD_DESC_NAME).image("blue-t-shirt.jpg").price(29).category(CLOTHING_NAME).quantity(25).inventoryStatus(InventoryStatus.INSTOCK).rating(5).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1004).code("h456wer53").name("Logitech Gpro Keyboard 60%").description(PROD_DESC_NAME).image("bracelet.jpg").price(15).category(ACCESSORIES_NAME).quantity(73).inventoryStatus(InventoryStatus.INSTOCK).rating(4).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1005).code("av2231fwg").name("Logitech Gpro Keyboard Lightspeed").description(PROD_DESC_NAME).image("brown-purse.jpg").price(120).category(ACCESSORIES_NAME).quantity(0).inventoryStatus(InventoryStatus.OUTOFSTOCK).rating(4).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1006).code("bib36pfvm").name("Artisan Zero Fx Mid").description(PROD_DESC_NAME).image("chakra-bracelet.jpg").price(32).category(ACCESSORIES_NAME).quantity(5).inventoryStatus(InventoryStatus.LOWSTOCK).rating(3).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1007).code("mbvjkgip5").name("Artisan Zero Fx Soft").description(PROD_DESC_NAME).image("galaxy-earrings.jpg").price(34).category(ACCESSORIES_NAME).quantity(23).inventoryStatus(InventoryStatus.INSTOCK).rating(5).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1008).code("vbb124btr").name("Artisan Zero Fx XSoft").description(PROD_DESC_NAME).image("zero1.jpg").price(99).category(ELECTRONICS_NAME).quantity(2).inventoryStatus(InventoryStatus.LOWSTOCK).rating(4).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1009).code("cm230f032").name("Artisan Hayate Otsu Mid").description(PROD_DESC_NAME).image("gaming-set.jpg").price(299).category(ELECTRONICS_NAME).quantity(63).inventoryStatus(InventoryStatus.INSTOCK).rating(3).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1010).code("plb34234v").name("Gold Phone Case").description(PROD_DESC_NAME).image("gold-phone-case.jpg").price(24).category(ACCESSORIES_NAME).quantity(0).inventoryStatus(InventoryStatus.OUTOFSTOCK).rating(4).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1011).code("4920nnc2d").name("Green Earbuds").description(PROD_DESC_NAME).image("green-earbuds.jpg").price(89).category(ELECTRONICS_NAME).quantity(23).inventoryStatus(InventoryStatus.INSTOCK).rating(4).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1012).code("250vm23cc").name("Green T-Shirt").description(PROD_DESC_NAME).image("green-t-shirt.jpg").price(49).category(CLOTHING_NAME).quantity(74).inventoryStatus(InventoryStatus.INSTOCK).rating(5).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1013).code("fldsmn31b").name("Grey T-Shirt").description(PROD_DESC_NAME).image("grey-t-shirt.jpg").price(48).category(CLOTHING_NAME).quantity(0).inventoryStatus(InventoryStatus.OUTOFSTOCK).rating(3).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1014).code("waas1x2as").name("Headphones").description(PROD_DESC_NAME).image("headphones.jpg").price(175).category(ELECTRONICS_NAME).quantity(8).inventoryStatus(InventoryStatus.LOWSTOCK).rating(5).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1015).code("vb34btbg5").name("Light Green T-Shirt").description(PROD_DESC_NAME).image("light-green-t-shirt.jpg").price(49).category(CLOTHING_NAME).quantity(34).inventoryStatus(InventoryStatus.INSTOCK).rating(4).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1016).code("k8l6j58jl").name("Lime Band").description(PROD_DESC_NAME).image("lime-band.jpg").price(79).category(FITNESS_NAME).quantity(12).inventoryStatus(InventoryStatus.INSTOCK).rating(3).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1017).code("v435nn85n").name("Mini Speakers").description(PROD_DESC_NAME).image("mini-speakers.jpg").price(85).category(CLOTHING_NAME).quantity(42).inventoryStatus(InventoryStatus.INSTOCK).rating(4).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1018).code("09zx9c0zc").name("Painted Phone Case").description(PROD_DESC_NAME).image("painted-phone-case.jpg").price(56).category(ACCESSORIES_NAME).quantity(41).inventoryStatus(InventoryStatus.INSTOCK).rating(5).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1019).code("mnb5mb2m5").name("Pink Band").description(PROD_DESC_NAME).image("pink-band.jpg").price(79).category(FITNESS_NAME).quantity(63).inventoryStatus(InventoryStatus.INSTOCK).rating(4).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1020).code("r23fwf2w3").name("Pink Purse").description(PROD_DESC_NAME).image("pink-purse.jpg").price(110).category(ACCESSORIES_NAME).quantity(0).inventoryStatus(InventoryStatus.OUTOFSTOCK).rating(4).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1021).code("pxpzczo23").name("Purple Band").description(PROD_DESC_NAME).image("purple-band.jpg").price(79).category(FITNESS_NAME).quantity(6).inventoryStatus(InventoryStatus.LOWSTOCK).rating(3).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1022).code("2c42cb5cb").name("Purple Gemstone Necklace").description(PROD_DESC_NAME).image("purple-gemstone-necklace.jpg").price(45).category(ACCESSORIES_NAME).quantity(62).inventoryStatus(InventoryStatus.INSTOCK).rating(4).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1023).code("5k43kkk23").name("Purple T-Shirt").description(PROD_DESC_NAME).image("purple-t-shirt.jpg").price(49).category(CLOTHING_NAME).quantity(2).inventoryStatus(InventoryStatus.LOWSTOCK).rating(5).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1024).code("lm2tny2k4").name("Shoes").description(PROD_DESC_NAME).image("shoes.jpg").price(64).category(CLOTHING_NAME).quantity(0).inventoryStatus(InventoryStatus.INSTOCK).rating(4).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1025).code("nbm5mv45n").name("Sneakers").description(PROD_DESC_NAME).image("sneakers.jpg").price(78).category(CLOTHING_NAME).quantity(52).inventoryStatus(InventoryStatus.INSTOCK).rating(4).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1026).code("zx23zc42c").name("Teal T-Shirt").description(PROD_DESC_NAME).image("teal-t-shirt.jpg").price(49).category(CLOTHING_NAME).quantity(3).inventoryStatus(InventoryStatus.LOWSTOCK).rating(3).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1027).code("acvx872gc").name("Yellow Earbuds").description(PROD_DESC_NAME).image("yellow-earbuds.jpg").price(89).category(ELECTRONICS_NAME).quantity(35).inventoryStatus(InventoryStatus.INSTOCK).rating(3).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1028).code("tx125ck42").name("Yoga Mat").description(PROD_DESC_NAME).image("yoga-mat.jpg").price(20).category(FITNESS_NAME).quantity(15).inventoryStatus(InventoryStatus.INSTOCK).rating(5).build()));
        products.add(ProductsDto.createProductMockService(Product.builder().id(1029).code("gwuby345v").name("Yoga Set").description(PROD_DESC_NAME).image("yoga-set.jpg").price(20).category(FITNESS_NAME).quantity(25).inventoryStatus(InventoryStatus.INSTOCK).rating(8).build()));


    }
    
    @Override
    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }
    
    
    @Override
    public List<Product> getProducts(int size) {

        if (size > products.size()) {

            List<Product> randomList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int randomIndex = this.rand.nextInt(products.size());
                randomList.add(products.get(randomIndex));
            }

            return randomList;
        }

        else {
            return new ArrayList<>(products.subList(0, size));
        }

    }
    
    @Override
    public List<Product> getClonedProducts(int size) {
        List<Product> results = new ArrayList<>();
        List<Product> originals = getProducts(size);
        for (Product original : originals) {
            results.add(Product.builder()
            					.id(original.getId())
            					.code(original.getCode())
            					.name(original.getName())
            					.description(original.getDescription())
            					.image(original.getImage())
            					.price(original.getPrice())
            					.category(original.getCategory())
            					.quantity(original.getQuantity())
            					.inventoryStatus(original.getInventoryStatus())
            					.rating(original.getRating())
            					.orders(original.getOrders())
            					.build());
        }

        // make sure to have unique codes
        for (Product product : results) {
            product.setCode(UUID.randomUUID().toString().replace("-", "").substring(0, 8));
        }

        return results;
    }
}
