package com.company;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class Main {
    static class MyThread extends Thread{
        private String date;
        MyThread(String date) {this.date = date;}
    @Override
    public void run(){
        LogsServiceImpl service = new LogsServiceImpl();
        try {
            service.getNumberOfLogsByDay(date);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
static class MyPeriodThread extends Thread{
        private String firstDate;
        private String lastDate;
        private int numberOfLogs;
        MyPeriodThread(String f, String l){
            this.firstDate = f;
            this.lastDate = l;
        }
        @Override
    public void run(){
            LogsServiceImpl service = new LogsServiceImpl();
            try {
               int n =  service.getNumberOfLogsByPeriod(firstDate,lastDate);
               this.numberOfLogs = n;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public int getNumberOfLogs(){
            return this.numberOfLogs;
        }
}
    public static void main(String[] args) throws IOException, InterruptedException {
        LocalDate date = LocalDate.of(2020,01,01);
        LocalDate last = LocalDate.of(2020,01,04);
        LogsServiceImpl service = new LogsServiceImpl();
        LocalDateTime start;

//        System.out.println("-----------------Пошук за днем-----------------");
//        start = LocalDateTime.now();
//        System.out.println(service.getNumberOfLogsByDay("2020-01-01"));
//        System.out.println(service.getNumberOfLogsByDay("2020-01-02"));
//        System.out.println(service.getNumberOfLogsByDay("2020-01-03"));
//        System.out.println(service.getNumberOfLogsByDay("2020-01-04"));
//        System.out.println("Загальна тривалість пошуку: " + ChronoUnit.MILLIS.between(start,LocalDateTime.now()) + " мс.");
//
//        System.out.println("----------------Пошук з заданням періоду----------------");
//        start = LocalDateTime.now();
//        service.getNumberOfLogsByPeriod("2020-01-01","2020-01-04");
//        System.out.println("Загальна тривалість пошуку: " + ChronoUnit.MILLIS.between(start,LocalDateTime.now()) + " мс.");
//
//        System.out.println("-----------------Потоки по 1 дню-----------------");
//        start = LocalDateTime.now();
//        new MyThread("2020-01-01").start();
//        new MyThread("2020-01-02").start();
//        new MyThread("2020-01-03").start();
//        new MyThread("2020-01-04").start();
//        System.out.println("Запуск потоків: " + ChronoUnit.MILLIS.between(start,LocalDateTime.now()) + " мс.");

        System.out.println("-----------------------Період на багато днів-----------------------");
        start = LocalDateTime.now();
        service.getNumberOfLogsByPeriod("2020-01-01", "2020-02-03");
        System.out.println("Загальна тривалість пошуку: " + ChronoUnit.MILLIS.between(start,LocalDateTime.now()) + " мс.");


        System.out.println("-----------------------Потоки на період 5+ днів-----------------------");
        start = LocalDateTime.now();
        System.out.println("Загальна кількість логів: " + service.getNumberOfLogsByThread("2020-01-01", "2020-02-03"));
        System.out.println("Загальна тривалість пошуку: " + ChronoUnit.MILLIS.between(start,LocalDateTime.now()) + " мс.");
    }
}