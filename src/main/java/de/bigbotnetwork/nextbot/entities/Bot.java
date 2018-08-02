package de.bigbotnetwork.nextbot.entities;

import de.bigbotnetwork.nextbot.entities.commands.Command;
import de.bigbotnetwork.nextbot.entities.listener.TelegramListener;
import net.dv8tion.jda.bot.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.bot.sharding.ShardManager;
import net.dv8tion.jda.core.entities.Game;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bot {

    private ArrayList<Command> commands = new ArrayList<>();
    private Config config;
    private List<TelegramListener> telegramListeners = new ArrayList<>();
    private ShardManager shardManager;
    private TelegramBotsApi botsApi;
    private DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();
    private TelegramBot telegramBot;

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
        botsApi = new TelegramBotsApi();
        telegramBot = new TelegramBot(this);
        try {
            botsApi.registerBot(telegramBot);
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

    public void registerListener(Object listener) {
        registerTelegramListener((TelegramListener) listener);
        registerDiscordListener(listener);
    }

    public void registerListener(Object... listeners) {
        for (Object listener : listeners) {
            registerListener(listener);
        }
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

    public TelegramBot getTelegramBot() {
        return telegramBot;
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public enum Type {
        TELEGRAM,
        DISCORD
    }

}
