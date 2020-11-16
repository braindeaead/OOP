package src;
import java.util.*;

public class IniParser {
    static class Category {
        String name;
        ArrayList<HashMap<String, String>> pairs = new ArrayList<>();

        Category(String name) {
            this.name = name;
        }

    }

    public static void main(String[] args) throws Exception {
        if (!args[0].contains(".ini")) throw new Exception("Error: wrong file format.");
        Scanner file = Methods.scan(args[0]);
        ArrayList<Category> categories = Methods.parser(file);
        System.out.println("Name category and type which value you want to know. And a type of the value too.");
        Scanner in = new Scanner(System.in);
        String categ = in.nextLine();
        String type = in.nextLine();
        switch (in.nextLine()) {
            case "string" :
                System.out.println(Methods.getString(categories, categ, type));
                break;
            case "double" :
                System.out.println(Methods.getDouble(categories, categ, type));
                break;
            case "integer" :
                System.out.println(Methods.getInteger(categories, categ, type));
                break;
            default:
                System.out.println("u didnt choose a type of the value.");
                break;
        }
    }
}
