package ArrayListDemo;

import java.util.ArrayList;

public class ArrayListDemo {
    ArrayList<String> merge(String[] words, String[] more) {
        ArrayList<String> sentence = new ArrayList<String>();
         for (String w : words) sentence.add(w);
         for (String w : more) sentence.add(w);
         return sentence;
    }
}
