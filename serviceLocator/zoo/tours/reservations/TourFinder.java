package zoo.tours.reservations;

import zoo.tours.api.*;
import java.util.*;
import zoo.tours.reservations.*;

public class TourFinder {

    public static Tour findSingleTour(){
        ServiceLoader<Tour> loader = ServiceLoader.load(Tour.class);
        for(Tour tour:loader)
            return tour;

        return null;
    }

    public static List<Tour> findAllTours(){
        ServiceLoader<Tour> loader = ServiceLoader.load(Tour.class);
        List<Tour> tours = new ArrayList<Tour>();
        for(Tour tour:loader)
            tours.add(tour);

        return tours;
    }
}
