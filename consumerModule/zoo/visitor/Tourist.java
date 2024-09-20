package zoo.visitor;

import java.util.*;
import zoo.tours.api.*;
import zoo.tours.reservations.*;

public class Tourist {
    public static void main(String[] args) {
        Tour tour =  TourFinder.findSingleTour();
        System.out.println("Single Tour:"+tour);

        List<Tour> tours = TourFinder.findAllTours();
        System.out.println("# Available tours:"+tours.size());
    }
}
