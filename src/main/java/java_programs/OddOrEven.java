package java_programs;

import java.util.Scanner;


    public class OddOrEven {

        public void check(int a) {
            if (a % 2 == 0) {
                System.out.println("The number is even");
            } else {
                System.out.println("The number is odd");
            }

        }

        public static void main(String[] args) {
            OddOrEven oddOrEven = new OddOrEven();
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the number");
            int a = sc.nextInt();
            oddOrEven.check(a);

        }
    }
