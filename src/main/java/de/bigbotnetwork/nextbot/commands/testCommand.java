package de.bigbotnetwork.nextbot.commands;

import de.bigbotnetwork.nextbot.entities.Bot;
import de.bigbotnetwork.nextbot.entities.commands.Command;
import de.bigbotnetwork.nextbot.entities.commands.Executor;

import static de.bigbotnetwork.nextbot.util.Messages.sendMessage;

public class testCommand implements Command {

    Bot bot;

    public testCommand(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void onCommand(String[] args, Executor executor) {
        sendMessage(bot, "test", executor);
    }

    @Override
    public String description() {
        return "A test command to test the bot :)";
    }
}
