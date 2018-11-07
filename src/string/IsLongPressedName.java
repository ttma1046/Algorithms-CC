package string;

public class IsLongPressedName {
    public static boolean isLongPressedName(String name, String typed) {
        if (name == null || typed == null)  {
            return true;
        }
        int nameLen = name.length();
        int typedLen = typed.length();
        if (name.length() == 0 || typed.length() == 0) {
            return true;
        }

        if (typed.length() < name.length()) {
            return false;
        }

        if (name.charAt(0) != typed.charAt(0)) {
            return false;
        }

        int i = 1;
        int j = 1;

        while (i < nameLen || j < typedLen) {
            if (name.charAt(i) == typed.charAt(j)) {
                if (j < typedLen - 1) {
                    j++;
                } else {
                    break;
                }

                if (i < nameLen - 1) {
                    i++;
                }
            } else {
                if (typed.charAt(j) == typed.charAt(j - 1)) {
                    if (j < typedLen - 1) {
                        j++;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // System.out.println(isLongPressedName("alex", "aaleex"));
        // System.out.println(isLongPressedName("saeed", "ssaaedd"));
        // System.out.println(isLongPressedName("leelee", "lleeelee"));
        // System.out.println(isLongPressedName("laiden", "laiden"));
        System.out.println(isLongPressedName("kikcxmvzi", "kiikcxxmmvvzz"));

    }
}
