//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Main {

    //-------------------------------------------------------


    public static boolean primceChecker(int n){
        if(n<2) return false;

        boolean[] isComposite = new boolean[n+1];
        Arrays.fill(isComposite,false);
        isComposite[0] = true;
        isComposite[1] = true;

        for(int i=2; i*i<=n; i++)
            if(!isComposite[i]){
                for(int j=i*i; j<=n; j+=i){
                    isComposite[j] = true;
                }
            }
        return !isComposite[n];
    }


    //-------------------------------------------------------


    public static Map<Integer, Integer> primeFactors(int n) {
        Map<Integer, Integer> factors = new HashMap<>();

        while (n % 2 == 0) {
            factors.put(2, factors.getOrDefault(2, 0) + 1);
            n /= 2;
        }

        for (int i = 3; i * i <= n; i += 2) {
            while (n % 3 == 0) {
                factors.put(i, factors.getOrDefault(i, 0) + 1);
                n /= i;
            }
        }

        if (n > 2) {
            factors.put(n, factors.getOrDefault(n, 0) + 1);
        }

        return factors;
    }

    //-------------------------------------------------------


    public static int gcdPrime(int a, int b) {
        Map<Integer, Integer> factorsA = primeFactors(a);
        Map<Integer, Integer> factorsB = primeFactors(b);
        int gcd = 1;

        // Find the common prime factors with the minimum power
        for (int prime : factorsA.keySet()) {
            if (factorsB.containsKey(prime)) {
                gcd *= Math.pow(prime, Math.min(factorsA.get(prime), factorsB.get(prime)));
            }
        }
        return gcd;
    }


    //-------------------------------------------------------


    public static int lcmPrime(int a, int b) {
        Map<Integer, Integer> factorsA = primeFactors(a);
        Map<Integer, Integer> factorsB = primeFactors(b);
        int lcm = 1;

        // Find the common prime factors with the minimum power
        for (int prime : factorsA.keySet()) {
            lcm *= Math.pow(prime, factorsA.getOrDefault(prime, 0));
        }

        for (int prime : factorsB.keySet()) {
            if (!factorsA.containsKey(prime)) {
                lcm *= Math.pow(prime, factorsB.get(prime));
            } else {
                lcm *= Math.pow(prime, Math.max(factorsA.get(prime), factorsB.get(prime))) / Math.pow(prime, factorsA.get(prime));
            }
        }

        return lcm;
    }


    //-------------------------------------------------------


    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return (gcd(b, a % b));
    }

    public static int lcm(int a, int b) {
        int lcm = Math.abs(a * b) / gcd(a, b);
        return lcm;
    }





    public static void main(String[] args) {

        System.out.println(gcd(72,120));
        System.out.println(lcm(72,120));

        System.out.println(gcdPrime(72,120));
        System.out.println(lcmPrime(72,120));

        System.out.println(primceChecker(7));

        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

//img.display();

    }
}
