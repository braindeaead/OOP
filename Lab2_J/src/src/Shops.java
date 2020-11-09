package src;

import java.io.IOException;
import java.util.ArrayList;

public class Shops {

    static class Shop {
        final int code;
        String name;
        String address;

        static ArrayList<Products> catalog;

        Shop(int code, String name, String address) {
            this.code = code;
            this.name = name;
            this.address = address;
            catalog = new ArrayList<>();
        }

        static Shops.Products findProds(Shops.Product prod) {
            Shops.Products requested = null;
            for (int i = 0; i < Shop.catalog.size(); i++) {
                if (Shop.catalog.get(i).prod.code == prod.code) {
                    requested = Shop.catalog.get(i);
                }
            }
            return requested;
        }
    }

    static class Products {
        Product prod;
        int amount;
        int price;

        Products(Product prod, int amount, int price) {
            this.prod = prod;
            this.amount = amount;
            this.price = price;
        }
    }

    static class Product {
        final int code;
        String name;

        Product(int code, String name) {
            this.code = code;
            this.name = name;
        }
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Shop> shops = new ArrayList<>();
        ArrayList<Product> prods = new ArrayList<>();
        Menu.menu(shops, prods);
    }
}