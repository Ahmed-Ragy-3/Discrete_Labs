import java.util.Scanner;

// 1 0 1 0
// 0 0 1 0
// 0 0 1 0

public class BitOperations {
   
   public static int getBit(int number, int position) {
      return ((1 << position) & number) == 0 ? 0 : 1;
   }

   public static int setBit(int number, int position) {
      return (1 << position | number);
   }

   public static int clearBit(int number, int position) {
      return ~(1 << position) & number;
   }

   public static int updateBit(int number, int position, boolean value) {
      return value ? setBit(number, position) : clearBit(number, position);
   }

   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);

      while (true) {
         // System.out.println((1 << 32));
         System.out.print("Number = ");
         int number = input.nextInt();

         System.out.print("Position = ");
         int position = input.nextInt();

         System.out.print("\nBit in position " + position + "\t\t => ");
         System.out.println(getBit(number, position));

         System.out.print("Setting position " + position + "\t\t => ");
         System.out.println(setBit(number, position));

         System.out.print("Clearing position " + position + "\t\t => ");
         System.out.println(clearBit(number, position));
         
         System.out.print("Update position " + position + " to 1 \t\t=> ");
         System.out.println(updateBit(number, position, true));
         
         System.out.print("Update position " + position + " to 0 \t\t=> ");
         System.out.println(updateBit(number, position, false));

         System.out.println("\n");
      }
      // input.close();
   }
}