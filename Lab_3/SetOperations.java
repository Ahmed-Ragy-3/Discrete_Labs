import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SetOperations {
   
   private Set universe;
   private List<Set> subsets;
   private Map<String, Integer> indexes;
   // Union of two sets
   // Intersection of two sets
   // Complement of a set
   // Difference between two sets
   // Cardinality of a set
   // Print a set

   public SetOperations(Set u, List<Set> subs) {
      this.indexes = new HashMap<>();
      this.subsets = new ArrayList<>();

      this.universe = u;
      for (int i = 0; i < u.size(); i++) {
         indexes.put(u.getInIndex(i), i);
      }

      for(Set subset : subs) {
         // int representation = 1;
         subset.setBinaryRepresentation(indexes);
         this.subsets.add(subset);
      }

   }

   public Set fromBinary(int binary) {
      List<String> strs = new ArrayList<>();
      // 1 0 1 1
      // 11
      int n = universe.size();
      for (int i = 0; i < n; i++) {
         if(BitOperations.getBit(binary, i) == 1) {
            strs.add(0, universe.getInIndex(n - i - 1));
            // strs.add(universe.getInIndex(n - i - 1));
         }
      }
      return new Set(strs);
   }

   private Set getSet(String name) {
      return subsets.get(Integer.parseInt(name.replaceAll("S", "")) - 1);
   }

   public Set union(String s1, String s2) {
      return union(getSet(s1), getSet(s2));
   }

   public Set intersection(String s1, String s2) {
      return intersection(getSet(s1), getSet(s2));
   }

   public Set difference(String s1, String s2) {
      return difference(getSet(s1), getSet(s2));
   }

   public Set complement(String s) {
      return complement(getSet(s));
   }

   private Set union(Set s1, Set s2) {
      return fromBinary(s1.getBinaryRepresentation() | s2.getBinaryRepresentation());
   }

   private Set intersection(Set s1, Set s2) {
      return fromBinary(s1.getBinaryRepresentation() & s2.getBinaryRepresentation());
   }

   private Set complement(Set s1) {
      int mask = (1 << universe.size()) - 1;
      return fromBinary(~s1.getBinaryRepresentation() & mask);
   }
   

   // public Set difference(Set s1, Set s2) {
   //    return intersection(s1, complement(s2));
   // }

   public Set difference(Set s1, Set s2) {
      int resultBinary = s1.getBinaryRepresentation() & ~s2.getBinaryRepresentation();
      return fromBinary(resultBinary);
   }
  

   // public Set cardinality(Set s1, Set s2) {
   //    // return
   // }

   public void printSet(Set s) {
      System.out.println(s.toString());
   }

   public void printUniverse() {
      printSet(universe);
   }

   public static void main(String[] args) {
      // System.out.print("NIHAHAHA");
      Scanner input = new Scanner(System.in);
      
      // System.out.println();
      System.out.println("Enter Universe:");
      System.out.println("Enter \"End\" when you finish your entry. ");

      try {

         List<String> inputSet = new ArrayList<>();

         String str;

         while (true) {
            str = input.nextLine();
            if(str.toLowerCase().equals("end")) {
               break;
            
            }else {
               inputSet.add(str);
            }
         }
         Set u = new Set(inputSet, "U");

         
         System.out.println("Enter number of Sets: ");
         int n = input.nextInt();
         if(n <= 0) {
            input.close();
            return;
         }
         List<Set> inputSubsets = new ArrayList<>();

         input.nextLine();
         for (int i = 0; i < n; i++) {
            inputSet.clear();
            System.out.println("Enter elements of Subset " + (i + 1));
            while (true) {
               str = input.nextLine();
               if(str.toLowerCase().equals("end")) {
                  break;
               }else {
                  inputSet.add(str);
               }
            }

            inputSubsets.add(new Set(inputSet, "S" + (i + 1)));
         }

         
         SetOperations operations = new SetOperations(u, inputSubsets);
         operations.printUniverse();
         
         // for (int i = 0; i < n; i++) {
         //    operations.printSet(inputSubsets.get(i));
         //    System.out.println();
         // }

         System.out.println();

         while (true) {
            System.out.print("Enter Operation: ");
            str = input.nextLine().toLowerCase();
            
            if(str.equals("end")) {
               break;
            
            }else {
               // inputSet.add(str);
               String s1, s2;
               switch (str) {
                  
                  case "union":
                     if(n <= 1) {
                        System.out.println("Invalid Input");
                        break;
                     }
                     System.out.print("Enter 2 Sets to Union: ");
                     s1 = "S%s".formatted(input.nextInt());
                     s2 = "S%s".formatted(input.nextInt());

                     System.out.print("Union = ");
                     System.out.println(operations.union(s1, s2).getArrayString());
                     
                     break;
                  
                  case "intersection":
                     if(n <= 1) {
                        System.out.println("Invalid Input");
                        break;
                     }
                     System.out.print("Enter 2 Sets to Intersect: ");
                     
                     s1 = "S%s".formatted(input.nextInt());
                     s2 = "S%s".formatted(input.nextInt());
                     
                     System.out.print("Intersection = ");
                     System.out.println(operations.intersection(s1, s2).getArrayString());
                     break;
                  
                  case "complement":
                     System.out.print("Enter Set to Complement: ");
                     s1 = "S%s".formatted(input.nextInt());
                     
                     System.out.print("Complement = ");
                     System.out.println(operations.complement(s1).getArrayString());
                     break;
                  
                  case "difference":
                     if(n <= 1) {
                        System.out.println("Invalid Input");
                        break;
                     }
                     System.out.print("Enter 2 Sets to difference: ");
                     s1 = "S%s".formatted(input.nextInt());
                     s2 = "S%s".formatted(input.nextInt());
                     
                     System.out.print("Difference = ");
                     System.out.println(operations.difference(s1, s2).getArrayString());
                     break;
                  
                  case "cardinality":
                     System.out.print("Enter Set to get Cardinality: ");
                     s1 = "S%s".formatted(input.nextInt());
                     
                     System.out.print("Cardinality = ");
                     System.out.println(operations.getSet(s1).size());
                     break;
                  
                  case "print":
                     System.out.print("Enter Set to Print: ");
                     s1 = "S%s".formatted(input.nextInt());
                     
                     // System.out.print(s1 + " = ");
                     operations.printSet(operations.getSet(s1));
                     break;
               
                  default:
                     System.out.println("Invalid Input");
                     System.out.println("Enter from (union - intersection - complement - difference - cardinality - print)");
                     break;
               }

               input.nextLine();
               System.out.println();
            }

         }

         input.close();
   
      } catch (Exception e) {
         System.out.println("Invalid input");
      }
   }
}
