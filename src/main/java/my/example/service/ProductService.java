package my.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import my.example.model.InventoryStatus;
import my.example.model.Product;

@ApplicationScoped
public class ProductService {
	
	private List<Product> products;
    private static String proddescName = "Product Description";
    private Random rand = new Random();

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1000, "f230fh0g3", "Logitech Gpro", proddescName, "gpro1.jpg", 65,
                "Accessories", 24, InventoryStatus.INSTOCK, 5));
        products.add(new Product(1001, "nvklal433", "Logitech Gpro x Superlight", proddescName, "black-watch.jpg", 72,
                "Accessories", 61, InventoryStatus.INSTOCK, 4));
        products.add(new Product(1002, "zz21cz3c1", "Logitech Gpro x superlight 2", proddescName, "blue-band.jpg", 79,
                "Fitness", 2, InventoryStatus.LOWSTOCK, 3));
        products.add(new Product(1003, "244wgerg2", "Logitech Gpro Keyboard TKL", proddescName, "blue-t-shirt.jpg", 29,
                "Clothing", 25, InventoryStatus.INSTOCK, 5));
        products.add(new Product(1004, "h456wer53", "Logitech Gpro Keyboard 60%", proddescName, "bracelet.jpg", 15,
                "Accessories", 73, InventoryStatus.INSTOCK, 4));
        products.add(new Product(1005, "av2231fwg", "Logitech Gpro Keyboard Lightspeed", proddescName, "brown-purse.jpg", 120,
                "Accessories", 0, InventoryStatus.OUTOFSTOCK, 4));
        products.add(new Product(1006, "bib36pfvm", "Artisan Zero Fx Mid", proddescName, "chakra-bracelet.jpg", 32,
                "Accessories", 5, InventoryStatus.LOWSTOCK, 3));
        products.add(new Product(1007, "mbvjkgip5", "Artisan Zero Fx Soft", proddescName, "galaxy-earrings.jpg", 34,
                "Accessories", 23, InventoryStatus.INSTOCK, 5));
        products.add(new Product(1008, "vbb124btr", "Artisan Zero Fx XSoft", proddescName, "zero1.jpg", 99,
                "Electronics", 2, InventoryStatus.LOWSTOCK, 4));
        products.add(new Product(1009, "cm230f032", "Artisan Hayate otsu Mid", proddescName, "gaming-set.jpg", 299,
                "Electronics", 63, InventoryStatus.INSTOCK, 3));
        products.add(new Product(1010, "plb34234v", "Gold Phone Case", proddescName, "gold-phone-case.jpg", 24,
                "Accessories", 0, InventoryStatus.OUTOFSTOCK, 4));
        products.add(new Product(1011, "4920nnc2d", "Green Earbuds", proddescName, "green-earbuds.jpg", 89,
                "Electronics", 23, InventoryStatus.INSTOCK, 4));
        products.add(new Product(1012, "250vm23cc", "Green T-Shirt", proddescName, "green-t-shirt.jpg", 49,
                "Clothing", 74, InventoryStatus.INSTOCK, 5));
        products.add(new Product(1013, "fldsmn31b", "Grey T-Shirt", proddescName, "grey-t-shirt.jpg", 48,
                "Clothing", 0, InventoryStatus.OUTOFSTOCK, 3));
        products.add(new Product(1014, "waas1x2as", "Headphones", proddescName, "headphones.jpg", 175,
                "Electronics", 8, InventoryStatus.LOWSTOCK, 5));
        products.add(new Product(1015, "vb34btbg5", "Light Green T-Shirt", proddescName, "light-green-t-shirt.jpg", 49,
                "Clothing", 34, InventoryStatus.INSTOCK, 4));
        products.add(new Product(1016, "k8l6j58jl", "Lime Band", proddescName, "lime-band.jpg", 79,
                "Fitness", 12, InventoryStatus.INSTOCK, 3));
        products.add(new Product(1017, "v435nn85n", "Mini Speakers", proddescName, "mini-speakers.jpg", 85,
                "Clothing", 42, InventoryStatus.INSTOCK, 4));
        products.add(new Product(1018, "09zx9c0zc", "Painted Phone Case", proddescName, "painted-phone-case.jpg", 56,
                "Accessories", 41, InventoryStatus.INSTOCK, 5));
        products.add(new Product(1019, "mnb5mb2m5", "Pink Band", proddescName, "pink-band.jpg", 79,
                "Fitness", 63, InventoryStatus.INSTOCK, 4));
        products.add(new Product(1020, "r23fwf2w3", "Pink Purse", proddescName, "pink-purse.jpg", 110,
                "Accessories", 0, InventoryStatus.OUTOFSTOCK, 4));
        products.add(new Product(1021, "pxpzczo23", "Purple Band", proddescName, "purple-band.jpg", 79,
                "Fitness", 6, InventoryStatus.LOWSTOCK, 3));
        products.add(new Product(1022, "2c42cb5cb", "Purple Gemstone Necklace", proddescName, "purple-gemstone-necklace.jpg", 45,
                "Accessories", 62, InventoryStatus.INSTOCK, 4));
        products.add(new Product(1023, "5k43kkk23", "Purple T-Shirt", proddescName, "purple-t-shirt.jpg", 49,
                "Clothing", 2, InventoryStatus.LOWSTOCK, 5));
        products.add(new Product(1024, "lm2tny2k4", "Shoes", proddescName, "shoes.jpg", 64,
                "Clothing", 0, InventoryStatus.INSTOCK, 4));
        products.add(new Product(1025, "nbm5mv45n", "Sneakers", proddescName, "sneakers.jpg", 78,
                "Clothing", 52, InventoryStatus.INSTOCK, 4));
        products.add(new Product(1026, "zx23zc42c", "Teal T-Shirt", proddescName, "teal-t-shirt.jpg", 49,
                "Clothing", 3, InventoryStatus.LOWSTOCK, 3));
        products.add(new Product(1027, "acvx872gc", "Yellow Earbuds", proddescName, "yellow-earbuds.jpg", 89,
                "Electronics", 35, InventoryStatus.INSTOCK, 3));
        products.add(new Product(1028, "tx125ck42", "Yoga Mat", proddescName, "yoga-mat.jpg", 20,
                "Fitness", 15, InventoryStatus.INSTOCK, 5));
        products.add(new Product(1029, "gwuby345v", "Yoga Set", proddescName, "yoga-set.jpg", 20,
                "Fitness", 25, InventoryStatus.INSTOCK, 8));

    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

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

    public List<Product> getClonedProducts(int size) {
        List<Product> results = new ArrayList<>();
        List<Product> originals = getProducts(size);
        for (Product original : originals) {
            results.add(original.clone());
        }

        // make sure to have unique codes
        for (Product product : results) {
            product.setCode(UUID.randomUUID().toString().replace("-", "").substring(0, 8));
        }

        return results;
    }
}
