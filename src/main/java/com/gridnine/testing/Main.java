package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Тестовые данные");
        List<Flight> allFlights = FlightBuilder.createFlights();
        allFlights.forEach(System.out::println);

        System.out.println("Фильтрация рейсов с вылетом до текущего момента:");
        FlightFilter.filterPastDepartures(allFlights).forEach(System.out::println);

        System.out.println("\nФильтрация рейсов  с датой прилёта раньше даты вылета");
        FlightFilter.filterInvalidSegments(allFlights).forEach(System.out::println);

        System.out.println("\nФильтрация рейсов с ожиданием более двух часов:");
        FlightFilter.filterLongGroundTimes(allFlights).forEach(System.out::println);
    }
}