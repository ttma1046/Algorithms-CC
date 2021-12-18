package leetcode;


class Dec_To_Binary {
    // Function that convert Decimal to binary
    public String decToBinary(int n) {
        StringBuilder sb = new StringBuilder();
        // Size of an integer is assumed to be 32 bits
        for (int i = 31; i >= 0; i--) {
            int p = n >> i;
            System.out.println("p:" + p);

            int temp = n;
            for (int j = i; j > 0; j--) temp /= 2;
            int k = temp;
            System.out.println("k:" + k);

            System.out.println();
            
            if ((k % 2) > 0)
                sb.append("1");
            else
                sb.append("0");
            
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        /*
        int k = 32 >> 1;

        System.out.println(k);
        System.out.println(k & 1);

        k = 33 >> 1;

        System.out.println(k);
        System.out.println(k & 1);

        System.out.println("&");

        System.out.println(0 & 1);
        System.out.println(1 & 1);
        System.out.println(2 & 1);
        System.out.println(3 & 1);

        System.out.println("&");

        k = 32 >> 3;

        int t = 32 / 2;
        t /= 2;
        t /= 2;

        System.out.println(t);
        System.out.println(k);
        System.out.println(k & 1);

        k = 33 >> 0;

        System.out.println(k);
        System.out.println(k & 1);
        */
        Dec_To_Binary obj = new Dec_To_Binary();

        System.out.println(obj.decToBinary(33));
    }
}