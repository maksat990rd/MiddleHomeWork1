package org.example.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.model.Driver;
import org.example.model.Truck;
import org.example.repository.DaoDriver;
import org.example.repository.DaoTruck;
import org.example.state.State;
import org.example.tdo.DriverTdo;
import org.example.tdo.DriverWithOutTruck;
import org.example.tdo.TruckDto;
import org.example.tdo.TruckWithOutDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Service {
    public static final GsonBuilder BUILDER = new GsonBuilder();
    public static final Gson GSON = BUILDER.setPrettyPrinting().create();
    public static final Path PATH = Path.of("./trucks");
    public static final Path PATH1 = Path.of("./driver");

    Scanner scanner = new Scanner(System.in);
    private DaoTruck daoTruck = new DaoTruck();
    private DaoDriver daoDriver = new DaoDriver();
    public void putTruck(Truck truck){
        daoTruck.add(truck);
    }
    public void putDriver (Driver driver){
        daoDriver.add(driver);
    }
    public void showIdTruck(int id){
        daoTruck.showIdTruck(id);
    }
    public void changeDriver(int truck){
        for (Map.Entry<Integer,Truck>e:daoTruck.getTruckMap().entrySet()){
            if(e.getValue().getState().equals(State.BASE)){
                daoDriver.show();
                System.out.print("Driver change driver: ");
                int driver = scanner.nextInt();
                changeDriver(truck, driver);
                break;
            } else if (e.getValue().getState().equals(State.ROUTE)) {
                System.err.printf(" You can't change  driver\n",
                        e.getValue().getName());
                break;
            } else {
                System.err.printf("You can't change the driver\n",
                        e.getValue().getName());
                break;
            }
        }
    }

    public void changeDriver(int idTruck, int idDriver) {
        Truck truck = daoTruck.findDrier(idTruck);
        Driver driver = daoDriver.fintTruck(idDriver);
        truck.setDriver(driver);
        driver.setTruck(truck);
    }
    public void route(int id){
        daoTruck.route(id);
    }
    public void repairing( int id){
        daoTruck.repairing(id);
    }
    public void base(int  id){
        daoTruck.base(id);
    }


    private void writeTruck(String write) {
        try {
            Path path = Paths.get(String.valueOf(PATH));
            Files.writeString(path, write, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeDriver(String write) {
        try {
            Path path = Paths.get(String.valueOf(PATH1));
            Files.writeString(path, write, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeJson() {
        String json = GSON.toJson(truckMap());
        String json1 = GSON.toJson(driverMap());
        writeTruck(json);
        writeDriver(json1);
    }

    private TruckWithOutDriver convert(Truck truck) {
        return new TruckWithOutDriver(truck.getId(), truck.getName(), truck.getState());
    }

    private DriverWithOutTruck convert(Driver driver) {
        return new DriverWithOutTruck(driver.getId(), driver.getName());
    }

    private Map<Integer, TruckDto> truckMap() {
        Map<Integer, TruckDto> truckMap1 = new HashMap<>();
        daoTruck.getTruckMap().forEach((key, value) -> {
            truckMap1.put(key, new TruckDto(value.getId(),
                    value.getName(),
                    value.hasDrive() ? convert(value.getDriver()) : null,
                    value.getState()));
        });
        return truckMap1;
    }

    private Map<Integer, DriverTdo> driverMap() {
        Map<Integer, DriverTdo> driverMap1 = new HashMap<>();
        daoDriver.getDriverMap().forEach((key, value) -> {
            driverMap1.put(key, new DriverTdo(value.getId(),
                    value.getName(),
                    value.hasTruck() ? convert(value.getTruck()) : null));
        });
        return driverMap1;
    }

    public void showTruck() {
        daoTruck.show();
    }

    public void showDriver() {
        daoDriver.show();
    }


}
