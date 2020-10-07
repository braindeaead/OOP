package src;
import java.util.*;

public class IniParser {
    public static class Category {
        public String name;
        public ArrayList<HashMap<String, String>> pairs = new ArrayList<>();

        public Category(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) throws Exception {
        if (!args[0].contains(".ini")) throw new Exception("Error: wrong file format.");
        Scanner file = Methods.scan(args[0]);
        ArrayList<Category> categories = Methods.parser(file);
        System.out.println("Name category and type which value you want to know.");
        Scanner in = new Scanner(System.in);
        String categ = in.nextLine();
        String type = in.nextLine();
        System.out.println(Methods.get(categories, categ, type));
    }
}
