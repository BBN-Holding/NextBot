package de.bigbotnetwork.nextbot.entities.commands;

import org.telegram.telegrambots.bots.DefaultAbsSender;

public interface Command {

    public void onCommand(String[] args, Executor executor);
    public String description();
    public String[] labels();
}
