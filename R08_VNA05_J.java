// compliant code user example
// https://wiki.sei.cmu.edu/confluence/display/java/VNA05-J.+Ensure+atomicity+when+reading+and+writing+64-bit+values

class LongContainer {
  private volatile long i = 0;
 
  void assignValue(long j) {
    i = j;
  }
 
  void printLong() {
    System.out.println("i = " + i);
  }
}
