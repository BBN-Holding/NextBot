package de.bigbotnetwork.nextbot.entities.commands;

import de.bigbotnetwork.nextbot.entities.Bot;
import de.bigbotnetwork.nextbot.entities.listener.TelegramListener;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;

public class CommandListener extends ListenerAdapter implements TelegramListener {

    Bot bot;

    public CommandListener(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void onUpdate(Update update) {
        if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().startsWith("/")) {
            handle(update.getMessage().getText().replaceFirst("/", ""), update);
        }
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (!event.getAuthor().isBot() && event.getMessage().getContentRaw().startsWith("next!")) {
            handle(event.getMessage().getContentRaw().replaceFirst("next!", ""), event);
        }
    }

    public void handle(String message, Object event) {
        String requestedlabel = message.split(" ")[0];
        message = message.replaceFirst(requestedlabel, "");
        if (message.startsWith(" ")) message = message.replaceFirst(" ", "");
        String[] args = message.split(" ");
        for (Command command : bot.getCommands()) {
            for (String label: command.labels()) {
                if (label.equalsIgnoreCase(requestedlabel)) {
                    Executor executor = null;
                    if (event instanceof GuildMessageReceivedEvent) {
                        executor = new Executor(Bot.Type.DISCORD, (GuildMessageReceivedEvent) event);
                    } else if (event instanceof Update) {
                        executor = new Executor(Bot.Type.TELEGRAM, (Update) event);
                    }
                    command.onCommand(args, executor);
                    break;
                }
            }
        }
    }
}
