package dk.easv.ticketmanager.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil
{
  private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

  public static String formatDate(LocalDateTime date){
      return date.format(dateFormatter);
  }

  public static String formatTime(LocalDateTime time){
    return time.format(timeFormatter);
  }
}
