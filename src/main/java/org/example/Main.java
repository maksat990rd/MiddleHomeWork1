package org.example;

import org.example.exception.InvalidChangeAttemptException;
import org.example.model.Driver;
import org.example.model.Truck;
import org.example.service.Service;
import org.example.state.State;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Truck truck1 = new Truck(1, "Kamaz", State.BASE);
        Truck truck2 = new Truck(2, "Bilaz", State.BASE);
        Truck truck3 = new Truck(3, "Volvo", State.BASE);

        Driver driver1 = new Driver(1, "Azamat");
        Driver driver2 = new Driver(2, "Nursultan");
        Driver driver3 = new Driver(3, "Bayel");

        Service service = new Service();

        service.putDriver(driver1);
        service.putDriver(driver2);
        service.putDriver(driver3);

        service.putTruck(truck1);
        service.putTruck(truck2);
        service.putTruck(truck3);

        Scanner scanner = new Scanner(System.in);

        service.showTruck();
        while (true) {
            try {
                System.out.println();
                System.out.println("ENTER ID OF TRUCK TO SEE ALL INFORMATION ABOUT IT: ");
                int truckId = scanner.nextInt();
                System.out.println();
                service.showIdTruck(truckId);
                chooseAnAction();
                int state = scanner.nextInt();
                switch (state) {
                    case 1 -> service.changeDriver(truckId);
                    case 2 -> service.route(truckId);
                    case 3 -> service.repairing(truckId);
                    case 4 -> service.base(truckId);
                }
            } catch (InvalidChangeAttemptException e) {
                System.err.println("Not found number!");
            }
            System.out.println();
            service.showTruck();
            System.out.println();
            service.showDriver();
            service.writeJson();
        }
    }
    public static void chooseAnAction() {
        System.out.println();
        System.out.println("choose an Action");
        System.out.println(" * 1: choose the driver");
        System.out.println(" * 2: start driving");
        System.out.println(" * 3: start repairing");
        System.out.println(" * 4: send to the Base");
    }

    public static void chooseAnActionTruck() {}
}