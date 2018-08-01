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
        if (update.hasMessage() && update.getMessage().hasText()) {
            handle(update.getMessage().getText(), update);
        }
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            handle(event.getMessage().getContentRaw(), event);
        }
    }

    public void handle(String message, Object event) {
        message = message.replaceFirst("next!", "");
        if (bot.getCommands().containsKey(message.split(" ")[0])) {
            Command command = bot.getCommands().get(message.split(" ")[0]);
            message = message.replaceFirst(message.split(" ")[0]+" ", "");
            String[] args = message.split(" ");
            Executor executor = null;
            if (event instanceof GuildMessageReceivedEvent) {
                executor = new Executor(Bot.Type.DISCORD, (GuildMessageReceivedEvent) event);
            } else if (event instanceof Update) {
                executor = new Executor(Bot.Type.TELEGRAM, (Update) event);
            }
            command.onCommand(args, executor);
        }
    }
}
