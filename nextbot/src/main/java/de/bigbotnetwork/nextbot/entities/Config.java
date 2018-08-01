package de.bigbotnetwork.nextbot.entities;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {

    JSONObject jsonObject;

    public Config(Path path) {
        try {
            jsonObject = new JSONObject(new String(Files.readAllBytes(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDiscordToken() {
        return getJsonObject().getString("discordtoken");
    }

    public String getTelegramToken() {
        return getJsonObject().getString("telegramtoken");
    }

    public String getBotName() {
        return getJsonObject().getString("botname");
    }

    public int getShards() {
        return getJsonObject().getInt("shards");
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
