package net.airymc.core.misc;

import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import java.util.Optional;
import java.util.UUID;

public class PlayerUtils {

    /**
     * Adds dashes to a uuid string without dashes.
     * Will return the inputted string if it already contains dashes
     * @param uuidString UUID string without dashes
     * @return UUID with dashes
     */
    public static UUID fixUuidString(String uuidString) {
        if (uuidString.contains("-"))
            return  UUID.fromString(uuidString);

        StringBuilder builder = new StringBuilder(uuidString);
        builder.insert(8, "-");
        builder = new StringBuilder(builder.toString());
        builder.insert(13, "-");
        builder = new StringBuilder(builder.toString());
        builder.insert(18, "-");
        builder = new StringBuilder(builder.toString());
        builder.insert(23, "-");
        return UUID.fromString(builder.toString());
    }

    /**
     * Sends a get request to the mojang api and then returns the uuid if it succeeds
     * else it returns an empty optional.
     * @param username Username of the player you want the uuid from
     * @return An optional that contains the uuid
     */
    public static Optional<UUID> getUuidByName(String username) {
        JSONObject body = HTTPUtils.sendGetRequest("https://api.mojang.com/users/profiles/minecraft/" + username);
        if (body.has("errorMessage"))
            return Optional.empty();
        return Optional.of(fixUuidString(body.getString("id")));
    }
}
