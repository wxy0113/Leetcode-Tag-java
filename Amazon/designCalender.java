import java.io.*;
import java.util.*;

// 然后就是coding做题，给的是一道design题，让我设计一个日历系统。要我先想好建立一个data structure来做这个系统，
// 以及实现“创建事件（名称，日期，时间）”，“查看某个时间是否busy”，以及“查看某日的所有事件”三个function。他让我先和他说一下thought process，然后再写。
// 我先和他说了我想用dictionary。但是我key和value的选择不太对，他提示了一下我就改过来了。感觉后面三个function也比较简单。

// Date
// Arguments: year, month, day
// Method: consturctor, hashCode, equals
class Date {
  int year;
  int month;
  int day;
  public Date(int year, int month, int day) {
    if (month < 1 || month > 12 || year < 1900) {
      throw new RuntimeException("Invalid Date");
    }
    boolean isLeap = false;
    if ((year%4 == 0 && year%100 != 0) || year%400 == 0) {
      isLeap = true;
    }
    this.year = year;
    this.month = month;
    this.day = day;
  }
  public Date(Date myDate) {
    year = myDate.year;
    month = myDate.month;
    day = myDate.day;
  }
  
  @Override
  public int hashCode() {
    return (year*365+month*30+day);
  }
  
  @Override
  public boolean equals(Object obj) {
    return ((Date)obj).year == year &&
           ((Date)obj).month == month &&
           ((Date)obj).day == day;
  }
}

// Time
// Arguments: hour, minute
// Method: constructor, compareTo
class Time {
  int hour;
  int minute;
  public Time(int hour, int minute) {
    if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
      throw new RuntimeException("Invalid Time");
    }
    this.hour = hour;
    this.minute = minute;
  }
  public Time(Time myTime) {
    hour = myTime.hour;
    minute = myTime.minute;
  }
  public int compareTo(Time time) {
    int cur = hour*60+minute;
    int next = time.hour*60+time.minute;
    if (cur == next) {
      return 0;
    } else if (cur > next) {
      return 1;
    } else {
      return -1;
    }
  }
}

// Event
// Arguments: date, startTime, endTime
// Method: constructor
class Event {
  Date date;
  Time start;
  Time end;
  String description;
  public Event(Date myDate, Time myStart, Time myEnd, String des) {
    date = new Date(myDate);
    start = new Time(myStart);
    end = new Time(myEnd);
    description = des;
  }
}

// Calender
// Arguments: HashMap<Date, List<Event>>
// Method: constructor, addEvent, isBusy, checkDateEvent
class Calender {
  Map<Date, List<Event>> calender;
  public Calender() {
    calender = new HashMap<>();
  }
  // addEvent
  public void addEvent(Date date, Time start, Time end, String name) {
    
    if (!calender.containsKey(date)) {
      calender.put(date, new ArrayList<>());
    } else {
      if (isBusy(date, start) || isBusy(date, end)) {
        throw new RuntimeException("Time Busy");
      }
    }

    calender.get(date).add(new Event(date, start, end, name));
  }
  public void addEvent(Event eve) {
    
    if (!calender.containsKey(eve.date)) {
      calender.put(eve.date, new ArrayList<>());
    } else {
      if (isBusy(eve.date, eve.start) || isBusy(eve.date, eve.end)) {
        throw new RuntimeException("Time Busy");
      }
    }

    calender.get(eve.date).add(eve);
  }
  
  // isBusy
  public boolean isBusy(Date date, Time time) {
    if (!calender.containsKey(date)) return false;
    List<Event> events = calender.get(date);
    for (Event eve : events) {
      if (time.compareTo(eve.start) > 0 && time.compareTo(eve.end) < 0) {
        return true;
      }
    }
    return false;
  }
  
  // checDateEvent
  public List<Event> checkDateEvent(Date date) {
    if (!calender.containsKey(date)) return null;
    return calender.get(date);
  }
}

class Solution {
  public static void main(String[] args) {
    Calender calender = new Calender();
    
    Date date1 = new Date(2019, 3, 5);
    Event event1 = new Event(date1, new Time(15, 0), new Time(16, 0), "OA1");
    calender.addEvent(event1);
    
    System.out.println(calender.isBusy(date1, new Time(15,29)));
    
    Event event2 = new Event(date1, new Time(15, 30), new Time(16, 30), "Reading");
    // calender.addEvent(event2);
    System.out.print(calender.checkDateEvent(date1));
  }
}