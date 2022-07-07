package org.example.tdo;

import com.google.gson.annotations.SerializedName;
import org.example.state.State;

public record TrruckDto(int id, String name, @SerializedName(value = "driver") DriverWithOutTruck driver, State state) {
}
