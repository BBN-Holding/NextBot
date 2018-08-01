package de.bigbotnetwork.nextbot.entities.commands;

public interface Command {

    public void onCommand(String[] args, Executor executor);

}
