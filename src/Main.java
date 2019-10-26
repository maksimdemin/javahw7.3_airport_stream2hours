import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("| dd.MM.yyyy | HH:mm");

    public static void main(String[] args) {

        printInfoSelectionTimeFlightsNew(2, Airport.getInstance());

    }


    private static void printInfoSelectionTimeFlightsNew(int nHours, Airport airport) {
        Date dateNow = new Date(System.currentTimeMillis()); // время текущее
        Date dateNowPlusTwo = new Date(dateNow.getTime() + 3600000 * nHours); // время текущее + n часов
        AtomicInteger i = new AtomicInteger(1); // переменная типа AtomicInteger для инкремента (количество вылетов)

        System.out.printf("\n Departure time and aircraft models flying in the next %d hours\n", nHours);
        airport.getTerminals().stream().flatMap(t -> t.getFlights().stream())
                .filter(f -> f.getType().equals(Flight.Type.DEPARTURE)).filter(f -> dateNow.getTime() < f.getDate()
                .getTime() && f.getDate().getTime() < dateNowPlusTwo.getTime())
                .forEach(f -> System.out.printf("%2d %s flight at %s | aircraft %s%n",
                        i.getAndIncrement(), f.getType(), DATE_FORMAT.format(f.getDate().getTime()), f.getAircraft().getModel()));
    }


//    private static void printInfoSelectionTimeFlights(int nHours, Airport airport) {
//        Date dateNow = new Date(System.currentTimeMillis());
//        Date dateNowPlusTwo = new Date(dateNow.getTime() + 3600000 * nHours);
//        AtomicInteger i = new AtomicInteger(1);
//
//        System.out.printf("   Departure time and aircraft models flying in the next %d hours\n\n", nHours);
//        airport.getTerminals().forEach(t -> t.getFlights().stream().filter(f -> dateNow.getTime() < f.getDate()
//                .getTime() && f.getDate().getTime() < dateNowPlusTwo.getTime())
//                .filter(f -> f.getType().equals(Flight.Type.DEPARTURE))
//                .forEach(f -> System.out.printf("%2d %s flight at %s | Terminal %s | aircraft %s%n",
//                        i.getAndIncrement(), f.getType(), DATE_FORMAT.format(f.getDate().getTime()), t.getName(), f.getAircraft().getModel())));
//    }


}

