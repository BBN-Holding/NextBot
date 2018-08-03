package de.bigbotnetwork.nextbot;

import de.bigbotnetwork.nextbot.commands.BuilderCommand;
import de.bigbotnetwork.nextbot.commands.HelpCommand;
import de.bigbotnetwork.nextbot.commands.testCommand;
import de.bigbotnetwork.nextbot.entities.Bot;
import de.bigbotnetwork.nextbot.entities.Config;
import de.bigbotnetwork.nextbot.entities.commands.CommandListener;
import de.bigbotnetwork.nextbot.listener.test;

import java.nio.file.Paths;
import java.util.Arrays;

public class NextBot {

    public static void main(String[] args) {
        Bot bot = new Bot(new Config(Paths.get("config.json")));
        bot.registerListener(new CommandListener(bot), new test());
        bot.start();
        bot.getCommands().addAll(Arrays.asList(
                new testCommand(bot),
                new HelpCommand(bot),
                new BuilderCommand(bot)
        ));
    }
}