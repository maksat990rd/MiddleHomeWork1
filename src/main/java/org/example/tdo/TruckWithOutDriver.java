package org.example.tdo;

import org.example.state.State;

public record TruckWithOutDriver(int id, String name, State state) {
}
