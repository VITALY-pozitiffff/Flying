package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FlightFilter {

    //Фильтрация всех рейсов ,начавшихся раньше текущего времени
     // flights Список исходных рейсов
     // @return Отфильтрованный список рейсов

    public static List<Flight> filterPastDepartures(List<Flight> flights) {
        LocalDateTime currentTime = LocalDateTime.now();
        return flights.stream()
                .filter(flight -> !flight.getSegments().stream()
                        .anyMatch(segment -> segment.getDepartureDate().isBefore(currentTime)))
                .collect(Collectors.toList());
    }


     //Фильтрация рейсов с некорректными сегментами,
     //где прибытие осуществляется раньше отправления.

     // flights Исходный список рейсов
     // Отфильтрованный список рейсов

    public static List<Flight> filterInvalidSegments(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .noneMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }


     // Фильтрация рейсов, где суммарное время ожидания на земле
     // превышает два часа.

     //  flights Исходный список рейсов
     // @return Отфильтрованный список рейсов

    public static List<Flight> filterLongGroundTimes(List<Flight> flights) {
        Duration maxGroundDuration = Duration.ofHours(2);
        return flights.stream()
                .filter(flight -> {
                    long totalGroundTime = 0L;
                    List<Segment> segments = flight.getSegments();

                    for(int i = 0; i < segments.size()-1; ++i){
                        Segment firstSeg = segments.get(i);
                        Segment secondSeg = segments.get(i+1);

                        totalGroundTime += Duration.between(firstSeg.getArrivalDate(), secondSeg.getDepartureDate()).toMinutes();
                    }
                    return totalGroundTime <= maxGroundDuration.toMinutes();
                })
                .collect(Collectors.toList());
    }
}