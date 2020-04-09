package stack;

class Backspace_String_Compare_daynine {
    public boolean backspaceCompare(String S, String T) {
        char[] sc = new char[S.length()];
        char[] tc = new char[T.length()];

        int i = 0;

        for(char c : S.toCharArray()) {
            if (c != '#') {
                sc[i] = c;
                i++;
            } else {
                if (i > 0) {
                    i--;
                }
            }
        }


        int j = 0;

        for(char c : T.toCharArray()) {
            if (c != '#') {
                tc[j] = c;
                j++;
            } else {
                if (j > 0) {
                    j--;
                }
            }
        }

        if (i == 0 && j == 0) {
            return true;
        } else if (j != i) {
            return false;
        } else {
            for (int s = 0; s < i; s++) {
                if(sc[s] != tc[s]) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        while (true) {
            for (int back = 0; i >= 0 && (back > 0 || S.charAt(i) == '#'); --i)
                back += S.charAt(i) == '#' ? 1 : -1;
            for (int back = 0; j >= 0 && (back > 0 || T.charAt(j) == '#'); --j)
                back += T.charAt(j) == '#' ? 1 : -1;
            if (i >= 0 && j >= 0 && S.charAt(i) == T.charAt(j)) {
                i--;
                j--;
            } else
                return i == -1 && j == -1;
        }
    }
}