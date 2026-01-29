package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.*;

public class FlightBuilder {
    public static List<Flight> createFlights() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime future = now.plusDays(3);

        return Arrays.asList(
                // Нормальный двухчасовой рейс
                new Flight(Collections.singletonList(new Segment(future, future.plusHours(2)))),

                // Многосегментный нормальный рейс
                new Flight(Arrays.asList(
                        new Segment(future, future.plusHours(2)),
                        new Segment(future.plusHours(3), future.plusHours(5))
                )),

                // Рейс, начинающийся в прошлом
                new Flight(Collections.singletonList(new Segment(now.minusDays(6), now))),

                // Неправильный рейс с сегментом, прилетающим раньше вылета
                new Flight(Collections.singletonList(new Segment(future, future.minusHours(6)))),

                // Перелет с временем нахождения на земле больше двух часов
                new Flight(Arrays.asList(
                        new Segment(future, future.plusHours(2)),
                        new Segment(future.plusHours(5), future.plusHours(6))
                ))
        );
    }
}
