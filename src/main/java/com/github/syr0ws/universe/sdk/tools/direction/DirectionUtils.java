package com.github.syr0ws.universe.sdk.tools.direction;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class DirectionUtils {

    public static Direction getDirectionToGoTo(Player player, Location location) {

        // The vector between the position of the player and the location.
        Vector vector = getVectorBetween(player.getLocation(), location);

        // The target location with a yaw to the player location.
        Location targetLocation = location.clone().setDirection(vector);

        float yaw = player.getLocation().getYaw();

        // Angle = playerYaw - targetYaw
        // Using modulo to set the angle between 0 and 360.
        double angle = ((yaw - targetLocation.getYaw()) % 360);

        return DirectionUtils.getDirection(angle);
    }

    private static Vector getVectorBetween(Location loc1, Location loc2) {

        // The vector between two locations can be found with the following formula : (x2 - x1 ; y2 - y1)
        // Normalizing the vector to get it with a norm of 1.
        return loc1.subtract(loc2).toVector().normalize();
    }

    private static Direction getDirection(double angle) {

        // If the angle is negative, multiplying it by -1 to get a positive number.
        // This doesn't change the angle.
        if(angle < 0) angle += 360;

        // Here is the range of the angle of each direction:
        // South (-22.5 <= 0 < 22.5)
        // South east (22.5 <= 45 < 67.5)
        // East (67.5 <= 90 < 112.5)
        // North east (112.5 <= 145 < 157.5)
        // North (157.5 <= 180 < 202.5)
        // North west (202.5 <= 225 < 247.5)
        // West (247.5 <= 270 < 292.5)
        // South west (292.5 <= 315 < 337.5)

        if (0 <= angle && angle < 22.5) {
            return Direction.SOUTH;
        } else if (angle < 67.5) {
            return Direction.SOUTH_EAST;
        } else if (angle < 112.5) {
            return Direction.EAST;
        } else if (angle < 157.5) {
            return Direction.NORTH_EAST;
        } else if (angle < 202.5) {
            return Direction.NORTH;
        } else if (angle < 247.5) {
            return Direction.NORTH_WEST;
        } else if (angle < 292.5) {
            return Direction.WEST;
        } else if (angle < 337.5) {
            return Direction.SOUTH_WEST;
        } else if (angle < 360.0) {
            return Direction.SOUTH;
        } else {
            return Direction.NORTH;
        }
    }
}
