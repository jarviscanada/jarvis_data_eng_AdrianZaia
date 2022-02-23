class Fibonacci {
    
    public int fib(int n) {
        // list for numbers
        int f[] = new int[n+2];
        
        // 0 and 1 are the first numbers
        f[0] = 0;
        f[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            //add the previous 2 numbers and store it
            f[i] = f[i-1] + f[i-2];
        }
        return f[n];
    }
}