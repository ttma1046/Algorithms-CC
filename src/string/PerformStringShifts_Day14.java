package string; 

class PerformStringShifts_Day14 {
    public String stringShift(String s, int[][] shift) {
        if (s == null || shift == null || shift.length == 0) {
            return null;
        }

        int tilt = 0;
        for (int[] array: shift) {
            if (array[0] == 0) {
                tilt -= array[1];
            } else {
                tilt += array[1];
            }
        }
        int n = s.length();
        tilt %= n;
        if (tilt < 0) {
            tilt += n;
        }
        return s.substring(n - tilt) + s.substring(0, n - tilt);


        return rotate(s, tilt);
    }

    private String rotate(String s, int d) {
        if (d > 0) {
            return s.substring(s.length() - d) + s.substring(0, s.length() - d);
        } else if (d < 0) {
            return s.substring(s.length() + d + 1) + s.substring(0, s.length() + d + 1);
        }

        return s;
    }

    public static void main(String[] args) {
        System.out.println(new PerformStringShifts_Day14().stringShift("abcdefg", new int[][] {{1,1}, {1,1}, {0, 2}, {1, 3}}));
    }
}