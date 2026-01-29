

import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.FlightFilter;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightFilterTest {
    @Test
    void testFilterPastDepartures() {
        LocalDateTime now = LocalDateTime.now();
        List<Flight> flights = FlightBuilder.createFlights();
        List<Flight> filteredFlights = FlightFilter.filterPastDepartures(flights);
        assertEquals(4, filteredFlights.size()); // Должны остаться четыре рейса
    }

    @Test
    void testFilterInvalidSegments() {
        List<Flight> flights = FlightBuilder.createFlights();
        List<Flight> filteredFlights = FlightFilter.filterInvalidSegments(flights);
        assertEquals(4, filteredFlights.size()); // Ожидается 4 правильных рейса
    }

    @Test
    void testFilterLongGroundTimes() {
        List<Flight> flights = FlightBuilder.createFlights();
        List<Flight> filteredFlights = FlightFilter.filterLongGroundTimes(flights);
        assertEquals(3, filteredFlights.size()); // Ожидаются три рейса без длительных задержек
    }
}