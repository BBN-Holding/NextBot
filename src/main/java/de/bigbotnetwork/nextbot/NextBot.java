package de.bigbotnetwork.nextbot;

import de.bigbotnetwork.nextbot.commands.testCommand;
import de.bigbotnetwork.nextbot.entities.Bot;
import de.bigbotnetwork.nextbot.entities.Config;
import de.bigbotnetwork.nextbot.entities.commands.CommandListener;
import de.bigbotnetwork.nextbot.listener.telegram.test;

import java.nio.file.Paths;

public class NextBot {

    public static void main(String[] args) {
        Bot bot = new Bot(new Config(Paths.get("config.json")));
        bot.registerTelegramListener(new test(), new CommandListener(bot));
        bot.registerDiscordListener(new test(), new CommandListener(bot));
        bot.start();
        bot.getCommands().put("test", new testCommand());
    }

}