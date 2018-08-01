package de.bigbotnetwork.nextbot.entities;

import de.bigbotnetwork.nextbot.entities.commands.Command;
import de.bigbotnetwork.nextbot.entities.listener.TelegramListener;
import de.bigbotnetwork.nextbot.listener.discord.test;
import net.dv8tion.jda.bot.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.bot.sharding.ShardManager;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.security.auth.login.LoginException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Bot {

    private HashMap<String, Command> commands = new HashMap<>();
    private Config config;
    private List<TelegramListener> telegramListeners = new ArrayList<>();
    private ShardManager shardManager;
    private TelegramBotsApi botsApi = new TelegramBotsApi();
    private DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();

    public Bot(Config config) {
        this.config = config;
    }

    public void start() {
        // Configure Discord Bot
        builder.setGame(Game.playing("stating... "));
        builder.setShardsTotal(config.getShards());
        builder.setToken(config.getDiscordToken());
        // Configure Telegram Bot
        ApiContextInitializer.init();
        try {
            botsApi.registerBot(new TelegramBot(this));
            this.shardManager = builder.build();
        } catch (TelegramApiException | LoginException e) {
            e.printStackTrace();
        }
    }

    public void registerDiscordListener(Object... listener) {
        builder.addEventListeners(listener);
    }

    public void registerTelegramListener(TelegramListener listener) {
        telegramListeners.add(listener);
    }

    public void registerTelegramListener(TelegramListener... listeners) {
        telegramListeners.addAll(Arrays.asList(listeners));
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public Config getConfig() {
        return config;
    }

    public List<TelegramListener> getTelegramListeners() {
        return telegramListeners;
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }

    public enum Type {
        TELEGRAM,
        DISCORD
    }

}
