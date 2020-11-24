package src;
import java.util.ArrayList;

class Shops {

    static class Shop {
        final int code;
        String name;
        String address;

        ArrayList<Goods> catalog;

        Shop(int code, String name, String address) {
            this.code = code;
            this.name = name;
            this.address = address;
            catalog = new ArrayList<>();
        }

        static void createShop(int code, String name, String address, ArrayList<Shops.Shop> shops) {
            shops.add(new Shops.Shop(code, name, address));
        }

        Goods findProds(Shops.Product prod) {
            Goods requested = null;
            for (int i = 0; i < catalog.size(); i++) {
                if (catalog.get(i).prod.code == prod.code) {
                    requested = catalog.get(i);
                }
            }
            return requested;
        }

        void consignment(Shops.Product prod, int amount, int price) {
            catalog.add(new Goods(prod, amount, price));
        }

        ArrayList<Goods> whatAble(int money) {
            ArrayList<Goods> prods = new ArrayList<>();

            for (int i = 0; i < catalog.size(); i++) {
                if (catalog.get(i).price < money) {
                    int k = 0;
                    while (catalog.get(i).price * k <= money && k <= catalog.get(i).amount) k++;
                    prods.add(new Goods(new Shops.Product(catalog.get(i).prod.code, catalog.get(i).prod.name), k - 1, catalog.get(i).price));
                }
            }
            return prods;
        }

        String buy(Shops.Product prod, int amount) { // string is not the best solution
            String price;
            if (findProds(prod).amount >= amount) {
                price = String.valueOf(findProds(prod).price * amount);
            } else {
                price = "No enough products in shop";
            }
            return price;
        }

        Methods.Result cheapestConsignment(ArrayList<Shop> shops, Product prod, int amount, Shop requested, int price) {
            if (catalog.contains(findProds(prod))) {
                if (catalog.get(catalog.indexOf(findProds(prod))).amount >= amount && catalog.get(catalog.indexOf(findProds(prod))).price < price) {
                    requested = Manager.getShop(shops, this.code);
                    price = catalog.get(catalog.indexOf(findProds(prod))).price;
                }
            }
            return new Methods.Result(requested, price);
        }
    }

    static class Goods {
        Product prod;
        int amount;
        int price;

        Goods(Product prod, int amount, int price) {
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

    public static void main(String[] args) {
        ArrayList<Shop> shops = new ArrayList<>();
        ArrayList<Product> prods = new ArrayList<>();
        Menu.menu(shops, prods);
    }
}
