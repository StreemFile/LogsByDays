package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by IntelliJ IDEA.
 * LogsByDays.LogsServiceImpl
 *
 * @Autor: vovamv
 * @DateTime: 11/17/20|12:18 пп
 * @Version LogsServiceImpl: 1.0
 */

public class LogsServiceImpl {
    public int getNumberOfLogsByDay(String date) throws IOException {
        Path path = Paths.get("/Users/vovamv/Desktop/Учоба/Java/LogsByDays/src/com/company/logs.txt");
        LocalDateTime start = LocalDateTime.now();
        System.out.println("Пошук за датою " + date + " розпочато о " + start);
        int numberOfLogs = (int) Files.lines(path)
                .filter(log -> log.contains(date))
                .count();
        System.out.println("Пошук закінчено о " + LocalDateTime.now()
                + ". Тривалість пошуку: " + ChronoUnit.MILLIS.between(start, LocalDateTime.now()) + " мс.");
        System.out.println("Кількість логів за датою " + date + "=" + numberOfLogs);
        return numberOfLogs;
    }

    public int getNumberOfLogsByDays(String date) throws IOException {
        Path path = Paths.get("/Users/vovamv/Desktop/Учоба/Java/LogsByDays/src/com/company/logs.txt");
        int numberOfLogs = (int) Files.lines(path)
                .filter(log -> log.contains(date))
                .count();
        return numberOfLogs;
    }

    public int getNumberOfLogsByPeriod(String firstDate, String lastDate) throws IOException {
        LocalDateTime start = LocalDateTime.now();
        System.out.println("Пошук за період з " + firstDate + " до " + lastDate + " розпочато о " + start + "\n");
        LocalDate date = LocalDate.parse(firstDate);
        LocalDate last = LocalDate.parse(lastDate).plusDays(1);
        int numberOfLogs = 0;
        while (date.isBefore(last)) {
            numberOfLogs += getNumberOfLogsByDays(date.toString());
            date = date.plusDays(1);
        }
        System.out.println("Пошук закінчено о " + LocalDateTime.now()
                + ". Тривалість пошуку: " + ChronoUnit.MILLIS.between(start, LocalDateTime.now()) + " мс.");
        System.out.println("Кількість логів за період з  " + firstDate + " до " + lastDate + "=" + numberOfLogs + "\n");
        return numberOfLogs;
    }

    public int getNumberOfLogsNotDividedBy5(String firstDate, String lastDate) throws InterruptedException {
        LocalDate date = LocalDate.parse(firstDate);
        LocalDate last = LocalDate.parse(lastDate);
        int numberOfDays = (int) ChronoUnit.DAYS.between(date, last);
        int daysForEach = numberOfDays / 5;

        LocalDate previousDate = date;
        LocalDate nextDate = previousDate.plusDays(daysForEach).minusDays(1);
        Main.MyPeriodThread first = new Main.MyPeriodThread(previousDate.toString(), nextDate.toString());
        first.start();

        previousDate = nextDate.plusDays(1);
        nextDate = previousDate.plusDays(daysForEach).minusDays(1);
        Main.MyPeriodThread second = new Main.MyPeriodThread(previousDate.toString(), nextDate.toString());
        second.start();

        previousDate = nextDate.plusDays(1);
        nextDate = previousDate.plusDays(daysForEach).minusDays(1);
        Main.MyPeriodThread third = new Main.MyPeriodThread(previousDate.toString(), nextDate.toString());
        third.start();

        previousDate = nextDate.plusDays(1);
        nextDate = previousDate.plusDays(daysForEach).minusDays(1);
        Main.MyPeriodThread fourth = new Main.MyPeriodThread(previousDate.toString(), nextDate.toString());
        fourth.start();


        previousDate = nextDate.plusDays(1);
        nextDate = previousDate.plusDays(daysForEach).minusDays(1);
        Main.MyPeriodThread fifth = new Main.MyPeriodThread(previousDate.toString(), nextDate.toString());
        fifth.start();


        int for6Thread = numberOfDays - daysForEach * 5;
        previousDate = nextDate.plusDays(1);
        nextDate = previousDate.plusDays(for6Thread);
        Main.MyPeriodThread sixth = new Main.MyPeriodThread(previousDate.toString(), nextDate.toString());
        sixth.start();
        first.join();
        second.join();
        third.join();
        fourth.join();
        fifth.join();
        sixth.join();
        return first.getNumberOfLogs() + second.getNumberOfLogs() + third.getNumberOfLogs()
                + fourth.getNumberOfLogs() + fifth.getNumberOfLogs() + sixth.getNumberOfLogs();
    }

    public int getNumberOfLogsDividedBy5(String firstDate, String lastDate) throws InterruptedException {
        LocalDate date = LocalDate.parse(firstDate);
        LocalDate last = LocalDate.parse(lastDate);
        int numberOfDays = (int) ChronoUnit.DAYS.between(date, last);
        int daysForEach = numberOfDays / 5;

        LocalDate previousDate = date;
        LocalDate nextDate = previousDate.plusDays(daysForEach).minusDays(1);
        Main.MyPeriodThread first = new Main.MyPeriodThread(previousDate.toString(), nextDate.toString());
        first.start();

        previousDate = nextDate.plusDays(1);
        nextDate = previousDate.plusDays(daysForEach).minusDays(1);
        Main.MyPeriodThread second = new Main.MyPeriodThread(previousDate.toString(), nextDate.toString());
        second.start();

        previousDate = nextDate.plusDays(1);
        nextDate = previousDate.plusDays(daysForEach).minusDays(1);
        Main.MyPeriodThread third = new Main.MyPeriodThread(previousDate.toString(), nextDate.toString());
        third.start();

        previousDate = nextDate.plusDays(1);
        nextDate = previousDate.plusDays(daysForEach).minusDays(1);
        Main.MyPeriodThread fourth = new Main.MyPeriodThread(previousDate.toString(), nextDate.toString());
        fourth.start();


        previousDate = nextDate.plusDays(1);
        nextDate = previousDate.plusDays(daysForEach);
        Main.MyPeriodThread fifth = new Main.MyPeriodThread(previousDate.toString(), nextDate.toString());
        fifth.start();

        first.join();
        second.join();
        third.join();
        fourth.join();
        fifth.join();
        return first.getNumberOfLogs() + second.getNumberOfLogs() + third.getNumberOfLogs()
                + fourth.getNumberOfLogs() + fifth.getNumberOfLogs();

    }

    public int getNumberOfLogsByThread(String firstDate, String lastDate) throws InterruptedException {
        LocalDate date = LocalDate.parse(firstDate);
        LocalDate last = LocalDate.parse(lastDate);
        int numberOfDays = (int) ChronoUnit.DAYS.between(date, last);
        if (numberOfDays < 5) {
            System.out.println("Даний метод використовується для пошуку періоду 5+ днів!");
            return 0;
        } else {
            if (numberOfDays % 5 == 0) {
               return getNumberOfLogsDividedBy5(firstDate, lastDate);
            } else {
              return  getNumberOfLogsNotDividedBy5(firstDate, lastDate);
            }
        }
    }
}
