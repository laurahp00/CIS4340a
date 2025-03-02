// compliant code user example
// https://wiki.sei.cmu.edu/confluence/display/java/OBJ02-J.+Preserve+dependencies+in+subclasses+when+changing+superclasses

// The CalendarImplementation object is a concrete implementation
// of the abstract Calendar class
// Class ForwardingCalendar
public class ForwardingCalendar {
  private final CalendarImplementation c;
 
  public ForwardingCalendar(CalendarImplementation c) {
    this.c = c;
  }
 
  CalendarImplementation getCalendarImplementation() {
    return c;
  }
 
  public boolean after(Object when) {
    return c.after(when);
  }
 
  public int compareTo(Calendar anotherCalendar) {
    // CalendarImplementation.compareTo() will be called
    return c.compareTo(anotherCalendar);
  }
}
 
class CompositeCalendar extends ForwardingCalendar {
  public CompositeCalendar(CalendarImplementation ci) {
    super(ci);
  }
 
  @Override public boolean after(Object when) {
    // This will call the overridden version, i.e.
    // CompositeClass.compareTo();
    if (when instanceof Calendar &&
        super.compareTo((Calendar)when) == 0) {
      // Return true if it is the first day of week
      return true;
    }
    // No longer compares with first day of week;
    // uses default comparison with epoch
    return super.after(when);
  }
 
  @Override public int compareTo(Calendar anotherCalendar) {
    return compareDays(
             super.getCalendarImplementation().getFirstDayOfWeek(),
             anotherCalendar.getFirstDayOfWeek());
  }
 
  private int compareDays(int currentFirstDayOfWeek,
                          int anotherFirstDayOfWeek) {
    return (currentFirstDayOfWeek > anotherFirstDayOfWeek) ? 1
           : (currentFirstDayOfWeek == anotherFirstDayOfWeek) ? 0 : -1;
  }
 
  public static void main(String[] args) {
    CalendarImplementation ci1 = new CalendarImplementation();
    ci1.setTime(new Date());
    // Date of last Sunday (before now)
    ci1.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
 
    CalendarImplementation ci2 = new CalendarImplementation();
    CompositeCalendar c = new CompositeCalendar(ci1);
    // Expected to print true
    System.out.println(c.after(ci2));
  }
}
