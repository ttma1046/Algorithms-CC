package string;

public class StringDemo {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("a");
        sb.append("b");
        sb.append("c");
        sb.append("d");
    }

    String joinWords(String[] words) {
        StringBuilder sentence = new StringBuilder();

        for (String w : words) {
            sentence.append(w);
        }

        return sentence.toString();
    }
}
