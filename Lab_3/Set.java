import java.util.Arrays;
import java.util.List;
import java.util.Map;
// import

public class Set {

   private String name;
   private String[] set;


   private int binaryRepresentation = -1;

   
   public Set(String[] strs) {
      set = new String[strs.length];
      
      for(int i=0; i < strs.length; i++) {
         set[i] = strs[i];
      }
   }
   
   public Set(String[] strs, String name) {
      this(strs);
      setName(name);
   }
   
   public Set(List<String> strs) {
      set = new String[strs.size()];
      
      for(int i=0; i < set.length; i++) {
         set[i] = strs.get(i);
      }
   }
   
   public Set(List<String> strs, String name) {
      this(strs);
      setName(name);
   }
   
   
   public String getName() {
      return name;
   }
   
   public String getInIndex(int index) {
      if(index >= set.length) {
         throw new IndexOutOfBoundsException("index is bigger than set length");
      }
      return set[index];
   }
   
   public void setName(String name) {
      this.name = name;
   }

   @Override
   public String toString() {
      return name + " = " + Arrays.toString(set) + stringifyBinaryRepresentation();
   }

   public int getBinaryRepresentation() {
      return binaryRepresentation;
   }

   public String stringifyBinaryRepresentation() {
      return (this.binaryRepresentation == -1) ? "" :  "\nBinary Representation = " + Integer.toBinaryString(binaryRepresentation);
   }
   
   public void setBinaryRepresentation(Map<String, Integer> indexes) {
      this.binaryRepresentation = 0;
      for (String element : set) {
         this.binaryRepresentation = BitOperations.setBit(binaryRepresentation, indexes.size() - 1 - indexes.get(element));
      }
   }

   public int size() {
      return set.length;
   }

   public String getArrayString() {
      return Arrays.toString(set);
   }

}
