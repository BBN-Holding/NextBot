package de.bigbotnetwork.nextbot.entities.commands;

import de.bigbotnetwork.nextbot.entities.Bot;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.function.Consumer;

public class Executor {

     Bot.Type type;
     GuildMessageReceivedEvent discordevent;
     Update telegramevent;

    public Executor(Bot.Type type, Object object) {
        this.type = type;
        if (object instanceof GuildMessageReceivedEvent) {
            discordevent = (GuildMessageReceivedEvent) object;
        } else if (object instanceof Update) {
            telegramevent = (Update) object;
        }
    }

    public void execute(Consumer<GuildMessageReceivedEvent> discord, Consumer<Update> telegram) {
        if (type == Bot.Type.DISCORD && discordevent != null) {
            discord.accept(discordevent);
        } else if (type == Bot.Type.TELEGRAM && telegramevent != null) {
            telegram.accept(telegramevent);
        }
    }
}
