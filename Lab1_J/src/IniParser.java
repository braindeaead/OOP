import javafx.animation.ScaleTransition;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class IniParser {
    public static class Category {
        public String name;
        public ArrayList<HashMap<String, String>> pairs = new ArrayList<>();

        public Category(String name) {
            this.name = name;
        }
    }

    public static Scanner scan(String name) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(name));
        return scan;
    }

    public static ArrayList<Category> parser(Scanner file) {
        ArrayList<Category> categories = new ArrayList<>();
        String current = file.nextLine();
        while (file.hasNextLine()) {
            char[] characters = current.toCharArray();
                if (characters[0] == '[') {
                    String name = new String(characters);
                    name = name.replace("[", "");
                    name = name.replace("]", "");
                    Category currentCategory = new Category(name);
                    current = file.nextLine();
                    while (current.toCharArray()[0] != '[') {
                        String[] now = current.split(" ");
                        currentCategory.pairs.add(new HashMap<>());
                        HashMap<String, String> map = new HashMap<>();
                        map.put(now[0], now[2]);
                        currentCategory.pairs.add(map);
                        if (file.hasNextLine()) {
                            current = file.nextLine();
                        }
                        else break;
                    }
                    categories.add(currentCategory);
                }

            }
        return categories;
    }

    public static Category get(String categ, String type) {
        
      return section;
    };

    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = scan(args[0]);
        ArrayList<Category> categories = parser(file);
        PrintWriter out = new PrintWriter(new FileOutputStream("out.txt"));
        for (int i = 0; i < categories.size(); i++) {
            Category current = categories.get(i);
            out.println(current.name);
            for (int k = 0; k <  current.pairs.size(); k++) {
                String what = String.valueOf(current.pairs.get(k));
                what = what.replace("{", "");
                what = what.replace("}", "");
                out.println(what);
            }
        }

        out.close();
    }
}
