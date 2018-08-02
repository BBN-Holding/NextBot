package de.bigbotnetwork.nextbot.commands;

import de.bigbotnetwork.nextbot.entities.Bot;
import de.bigbotnetwork.nextbot.entities.commands.Command;
import de.bigbotnetwork.nextbot.entities.commands.Executor;
import net.dv8tion.jda.core.EmbedBuilder;

import java.util.Arrays;
import java.util.Collection;

import static de.bigbotnetwork.nextbot.util.Messages.sendMessage;

public class HelpCommand implements Command {

    Bot bot;

    public HelpCommand(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void onCommand(String[] args, Executor executor) {

        StringBuilder commands = new StringBuilder();
        for (Command command: bot.getCommands()) {
            commands.append(Arrays.toString(command.labels()));
            commands.append(" - ");
            commands.append(command.description());
            commands.append("\n");
        }

        sendMessage(bot, new EmbedBuilder().setTitle("Help").setDescription(commands.toString()), "Help\n"+commands.toString(), executor);
    }

    @Override
    public String description() {
        return "Gives you help about the bot";
    }

    @Override
    public String[] labels() {
        return new String[]{"help", "helpme"};
    }
}
