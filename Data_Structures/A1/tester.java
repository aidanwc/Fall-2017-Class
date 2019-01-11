//package here;
import java.math.BigInteger;

public class tester {
 
 public static void main(String[] args) throws Exception {
      
  //  You can test the correctness of your NaturalNumber implementation 
  //  by using Java's BigInteger class.  
  
  //  Here is an example.  
  
  //String s1 = "10";
  //String s2 = "1";

  int base = 10;
  String s1 = "213541263135411132345485738475384573485734857392292929292929292929292929292929292992922992929929291191384358945782394758374597259384758923745892374582374589723458758697458967576895476897458674389568293458324759823768927358345982349086209689450869045860981904581290348128394358974873948690348534";
  String s2 = "2";
  
  BigInteger big1 = new BigInteger(s1,base);
  BigInteger big2 = new BigInteger(s2,base);
  
  NaturalNumber n1 = new NaturalNumber(s1, base);
  System.out.println("n1 is    " + n1);
  NaturalNumber n2 = new NaturalNumber(s2, base);
  System.out.println("n2 is    " + n2);
  System.out.println("");
        
  //  The BigInteger class uses an 'add' method for addition, but NaturalNumber 
  //  uses 'plus' instead, to avoid confusion with the LinkedList's 'add' method
  //  which inserts an element.
  
  System.out.print("sum: big1+big2 =        (");
  System.out.println(big1.add(big2).toString(base) + ")_" + base );  // BigInteger
  System.out.print("sum: n1+n2     =        ");
  System.out.println(n1.plus(n2));                     // NaturalNumber
  System.out.println();
  
  //  The BigInteger class uses a 'subtract' method for addition, but NaturalNumber 
  //  uses 'minus' instead.  This name was chosen because it was a better match 
  //  for 'plus'.

  System.out.print("diff: big1-big2 =       (");
  System.out.println(big1.subtract(big2).toString(base)  + ")_" + base );  // BigInteger
  System.out.print("diff: n1-n2     =       ");
  System.out.println(n1.minus(n2));                         // NaturalNumber

  //  The BigInteger class uses a 'multiply' method for addition. NaturalNumber 
  //  uses 'times' instead.  
  System.out.println();
  
  System.out.print("multiply: big1*big2   = ("  );              // BigInteger
  System.out.print(big1.multiply(big2).toString(base)  );
  System.out.println( ")_" + base);
  
  System.out.print("multiply: n1*n2       = ");               // NaturalNumber
  System.out.println(n1.times(n2));
  System.out.println();
  
  System.out.print("slow multiply: n1*n2  = ");               // NaturalNumber
  //System.out.println(n1.slowTimes(n2));
  System.out.println();
    
  System.out.print("divide: big1/big2     = (");              // BigInteger  
  System.out.println(big1.divide(big2).toString(base)  + ")_" + base);

  System.out.print("divide: n1/n2         = ");                 // NaturalNumber
  System.out.println(n1.divide(n2));
  System.out.println();
  
  System.out.print("slow divide: n1/n2    = ");               // NaturalNumber
 // System.out.println(n1.slowDivide(n2));

  /*   mod
  
  System.out.print("mod = ");
  System.out.println(big1.subtract(big1.divide(big2).multiply(big2)));
  System.out.println(big1.mod(big2));
  
  */
  /*System.out.println();
  NaturalNumber single = new NaturalNumber(2,5);
  System.out.println(n1.slowTimes(single));
  System.out.println("This is a test" +(n1.timesSingleDigit(2)));
  */
  
  
  }
    
 }
 


