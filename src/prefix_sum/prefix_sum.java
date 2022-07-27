package prefix_sum;

import java.util.*;

public class Main {
    private static int N = 100010;  // 定义数组大小, 防止越界

    public static void main(String[] args) {
        // 初始化输入值
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] arr = new int[N];
        // 注意这里是从 1开始的
        for (int i = 1; i <= n; i++)
            arr[i] = in.nextInt();

        // s[i]代表 arr的前 i项和
        int s[] = new int[N];
        s[0] = 0;
        // 计算出 s[i]
        
        for (int i = 1; i <= n; i++)
            s[i] = s[i - 1] + arr[i];     // 注意运算方式

        // 循环输出
        while (m-- > 0) {
            int l = in.nextInt();
            int r = in.nextInt();
            System.out.println(s[r] - s[l - 1]);  // 关键
        }
    }
}