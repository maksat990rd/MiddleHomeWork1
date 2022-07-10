package org.example.repository;

import org.example.model.Truck;
import org.example.state.State;

import java.util.HashMap;
import java.util.Map;

public class DaoTruck {
    private Map<Integer, Truck> truckMap = new HashMap<>();

    public DaoTruck() {
    }

    public Map<Integer, Truck> getTruckMap() {
        return truckMap;
    }

    public void setTruckMap(Map<Integer, Truck> truckMap) {
        this.truckMap = truckMap;
    }

    public void add(Truck truck) {
        truckMap.put(truck.getId(), truck);
    }

    public Truck findDrier(int id) {
        return truckMap.get(id);
    }

    public void showIdTruck(int id) {
        for (Map.Entry<Integer, Truck> t : truckMap.entrySet()) {
            if (id == t.getKey()) {
                try {
                    System.out.println(" Id          : " + t.getValue().getId());
                    System.out.println(" Truck       : " + t.getValue().getName());
                    System.out.println(" Driver      : " + t.getValue().getDriver().getName());
                    System.out.println(" Truck State : " + t.getValue().getState());
                } catch (NullPointerException e) {
                    System.out.println(" Driver      : " + " ");
                    System.out.println(" Truck State : " + t.getValue().getState() + "\n");
                }
            }
        }
    }

    public void route(int id) {
        for (Map.Entry<Integer, Truck> t : truckMap.entrySet()) {
            if (t.getKey() == id) {
                if (t.getValue().getState().equals(State.ROUTE)) {
                    System.err.println("The truck is on the way");
                } else {
                    if (t.getValue().getDriver() == null) {
                        System.err.println("NO DRIVER");
                    } else {
                        t.getValue().setState(State.ROUTE);
                        System.out.println("=================================================");
                        System.out.println("You have successfully entered the route");
                        System.out.println();
                    }
                }
            }
        }
    }

    public void repairing(int id) {
        truckMap.entrySet().stream().filter(x -> x.getKey() == id).forEach(x -> x.getValue().setState(State.REPAIR));
        System.out.println("=================================================");
        System.out.println("you have successfully sent for repair");
    }
    public void base(int id) {
        truckMap.entrySet().stream().filter(x -> x.getKey() == id).forEach(x -> x.getValue().setState(State.BASE));
        System.out.println("=================================================");
        System.out.println("you have successfully sent for base");
    }

    public void show() {
        System.out.println("""
                 #     | Truck            | Driver   | State
                -------+------------------+----------+--------------""");
        for (Map.Entry<Integer, Truck> entry : truckMap.entrySet()) {
            Integer key = entry.getKey();
            Truck value = entry.getValue();
            try {
                System.out.printf("""
                                -%d-     |  %s         | %s        | %s            """, key, value.getName(),
                        value.getDriver().getName(), value.getState() + "\n");
            } catch (NullPointerException e) {
                System.out.printf("""
                                -%d-    |  %s         | %s        | %s            """, key, value.getName(),
                        " ", value.getState() + "\n");
            }
        }
    }
}
