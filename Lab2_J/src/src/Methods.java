package src;
import java.util.ArrayList;
import java.util.Scanner;

class Methods {

    static String[] consoleSplitter(Scanner in, int count) {
        String[] splitted = new String[count];

        for (int i = 0; i < count; i++) {
            splitted[i] = in.next();

        }
        return splitted;
    }

    static void createShop(int code, String name, String address, ArrayList<Shops.Shop> shops) {
        shops.add(new Shops.Shop(code, name, address));
    }

    static void createProd(int code, String name, ArrayList<Shops.Product> prods) {
        prods.add(new Shops.Product(code, name));
    }

    static void consignment(ArrayList<Shops.Shop> shops, Shops.Product prod, Shops.Shop shop, int amount, int price) {
        shops.get(shops.indexOf(shop)).catalog.add(new Shops.Products(prod, amount, price));
    }

    private static Shops.Products findProds(Shops.Shop shop, Shops.Product prod) {
        Shops.Products requested = null;
        for (int i = 0; i < shop.catalog.size(); i++) {
            if (shop.catalog.get(i).prod.code == prod.code) {
                requested = shop.catalog.get(i);
            }
        }
        return requested;
    }

    static Shops.Shop getShop(ArrayList<Shops.Shop> shops, int code) {
        Shops.Shop requested = null;

        for (Shops.Shop shop : shops) {
            if (shop.code == code) {
                requested = shop;
                break;
            }
        }
        return requested;
    }

    static Shops.Product getProd (ArrayList<Shops.Product> prods, int code) {
        Shops.Product requested = null;

        for (Shops.Product prod : prods) {
            if (prod.code == code) {
                requested = prod;
                break;
            }
        }
        return requested;
    }

    static Shops.Shop findCheapest(Shops.Product prod, ArrayList<Shops.Shop> shops) {
        Shops.Shop coolest = null;
        int price = Integer.MAX_VALUE;

        for (int i = 0; i < shops.size(); i++) {
            for (int k = 0; k < shops.get(i).catalog.size(); k++) {
                if (shops.get(i).catalog.get(k).price < price) {
                    price = shops.get(i).catalog.get(k).price;
                    coolest = shops.get(i);
                }
            }
        }
        return coolest;
    }

    static ArrayList<Shops.Products> whatAble(int money, Shops.Shop shop) {
        ArrayList<Shops.Products> prods = new ArrayList<>();

        for (int i = 0; i < shop.catalog.size(); i++) {
            if (shop.catalog.get(i).price < money) {
                int k = 0;
                while (shop.catalog.get(i).price * k <= money && k <= shop.catalog.get(i).amount) k++;
                prods.add(new Shops.Products(new Shops.Product(shop.catalog.get(i).prod.code, shop.catalog.get(i).prod.name), k - 1, shop.catalog.get(i).price));
            }
        }
        return prods;
    }

    static String buy(Shops.Shop shop, Shops.Product prod, int amount) {
        String price;
        if (findProds(shop, prod).amount >= amount) {
            price = String.valueOf(findProds(shop, prod).price * amount);
        } else {
            price = "No enough products in shop";
        }
        return price;
    }

    static Shops.Shop findConsignment(ArrayList<Shops.Shop> shops, Shops.Product prod, int amount) {
        Shops.Shop requested = null;
        int price = Integer.MAX_VALUE;

        for (Shops.Shop shop : shops) {
            if (shop.catalog.contains(findProds(shop, prod))) {
                if (shop.catalog.get(shop.catalog.indexOf(findProds(shop, prod))).amount >= amount && shop.catalog.get(shop.catalog.indexOf(findProds(shop, prod))).price < price) {
                    requested = shop;
                    price = shop.catalog.get(shop.catalog.indexOf(findProds(shop, prod))).price;
                }
            }
        }
        return requested;
    }
}
