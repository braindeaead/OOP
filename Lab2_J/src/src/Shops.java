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

        static void createShop(int code, String name, String address, ArrayList<Shops.Shop> shops) {
            shops.add(new Shops.Shop(code, name, address));
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

        static ArrayList<Shops.Products> whatAble(int money) {
            ArrayList<Shops.Products> prods = new ArrayList<>();

            for (int i = 0; i < catalog.size(); i++) {
                if (catalog.get(i).price < money) {
                    int k = 0;
                    while (catalog.get(i).price * k <= money && k <= catalog.get(i).amount) k++;
                    prods.add(new Shops.Products(new Shops.Product(catalog.get(i).prod.code, catalog.get(i).prod.name), k - 1, catalog.get(i).price));
                }
            }
            return prods;
        }

        static String buy(Shops.Product prod, int amount) {
            String price;
            if (findProds(prod).amount >= amount) {
                price = String.valueOf(findProds(prod).price * amount);
            } else {
                price = "No enough products in shop";
            }
            return price;
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

        static void createProd(int code, String name, ArrayList<Shops.Product> prods) {
            prods.add(new Shops.Product(code, name));
        }
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Shop> shops = new ArrayList<>();
        ArrayList<Product> prods = new ArrayList<>();
        Menu.menu(shops, prods);
    }
}
