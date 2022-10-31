package net.nebulabc.commands;

import net.minecraft.src.command.ChatColor;
import net.minecraft.src.command.Command;
import net.minecraft.src.command.CommandHandler;
import net.minecraft.src.command.CommandSender;
import org.pf4j.Extension;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

@Extension
public class Rank implements com.bta.util.CommandHandler
{
    @Override
    public Command command()
    {
        return new Command("rank")
        {
            @Override
            public boolean execute(CommandHandler commandHandler, CommandSender commandSender, String[] args)
            {
                Properties rank = new Properties();
                if (args[0].equals("give"))
                {
                    if (args.length < 3) {return false;}
                    if (!(new File("ranks/" + args[1] + ".rank").exists())) {commandSender.sendMessage(ChatColor.red + "Rank does not exist!"); return true;}
                    rank.setProperty(args[2], args[1]);
                    commandSender.sendMessage(ChatColor.green + "Gave rank to player.");

                    try
                    {
                        File file = new File("ranks/ranks.db");
                        if (!file.exists()) file.createNewFile();
                        rank.store(new FileOutputStream(file), null);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    return true;
                }
                if (args[0].equals("prefix"))
                {
                    if (args.length < 3) {return false;}
                    if (!(new File("ranks/" + args[1] + ".rank").exists())) {commandSender.sendMessage(ChatColor.red + "Rank does not exist!"); return true;}
                    rank.setProperty("prefix", args[2].replace("&", "§"));
                    commandSender.sendMessage(ChatColor.green + "Set prefix for " + args[1] + " to " + args[2] + ".");

                    try
                    {
                        File file = new File("ranks/" + args[1] + ".rank");
                        rank.store(new FileOutputStream(file), null);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    return true;
                }
                if (args[0].equals("create"))
                {
                    if (args.length < 2) {return false;}
                    File file = new File("ranks/" + args[1] + ".rank");
                    if (file.exists()) {commandSender.sendMessage(ChatColor.red + "Rank already exists!"); return true;}
                    try
                    {
                        file.createNewFile();
                        commandSender.sendMessage(ChatColor.green + "Rank created.");
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    return true;
                }
                if (args[0].equals("delete"))
                {
                    if (!(new File("ranks/" + args[1] + ".rank").exists())) {commandSender.sendMessage(ChatColor.red + "Rank does not exist!"); return true;}
                    File file = new File("ranks/" + args[1] + ".rank");
                    file.delete();
                    commandSender.sendMessage(ChatColor.green + "Rank deleted.");
                    return true;
                }
                return false;
            }

            @Override
            public boolean opRequired(String[] strings)
            {
                return true;
            }

            @Override
            public void sendCommandSyntax(CommandHandler commandHandler, CommandSender commandSender)
            {
                commandSender.sendMessage("/rank <give/prefix/create/delete> <rank> <username/prefix>");
            }
        };
    }
}