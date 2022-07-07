package org.example.tdo;

import com.google.gson.annotations.SerializedName;

public record DriveTdo(int id, String name, @SerializedName(value = "truck") TruckWithOutDriver truck) {
}
