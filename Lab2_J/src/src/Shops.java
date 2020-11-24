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
            for (Goods goods : catalog) {
                if (goods.prod.code == prod.code) {
                    requested = goods;
                }
            }
            return requested;
        }

        void consignment(Shops.Product prod, int amount, int price) {
            catalog.add(new Goods(prod, amount, price));
        }

        ArrayList<Goods> whatAble(int money) {
            ArrayList<Goods> prods = new ArrayList<>();

            for (Goods goods : catalog) {
                if (goods.price < money) {
                    int k = 0;
                    while (goods.price * k <= money && k <= goods.amount) k++;
                    prods.add(new Goods(new Product(goods.prod.code, goods.prod.name), k - 1, goods.price));
                }
            }
            return prods;
        }

        int buy(Shops.Product prod, int amount) {
            int price;
            if (findProds(prod).amount >= amount) {
                price = findProds(prod).price * amount;
            } else {
                price = -1;
            }
            return price;
        }

        Methods.Result cheapestConsignment(ArrayList<Shop> shops, Product prod, int amount, int price) {
            Shop requested = null;
            if (catalog.contains(findProds(prod))) {
                if (catalog.get(catalog.indexOf(findProds(prod))).amount >= amount && catalog.get(catalog.indexOf(findProds(prod))).price < price) {
                    requested = Manager.getShop(shops, this.code);
                    price = catalog.get(catalog.indexOf(findProds(prod))).price;
                }
            }

            return new Methods.Result(requested, price);
        }

        Methods.Result findCheapestProduct(ArrayList<Shop> shops, Product prod, int price) {
            Shop coolest = null;
            for (Shops.Goods good : catalog) {
                if (good.price < price && good.prod == prod) {
                    price = good.price;
                    coolest = Manager.getShop(shops, this.code);
                }
            }

            return new Methods.Result(coolest, price);
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
