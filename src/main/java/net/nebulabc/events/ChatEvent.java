package net.nebulabc.events;

import com.bta.events.PlayerChatEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.Packet3Chat;
import net.minecraft.src.command.ChatColor;
import org.pf4j.Extension;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Logger;

@Extension
public class ChatEvent implements PlayerChatEvent
{
    @Override
    public void onEvent(EntityPlayerMP entityPlayerMP, Packet3Chat packet3Chat, MinecraftServer minecraftServer)
    {
        Logger logger = Logger.getLogger("Minecraft");
        String s = packet3Chat.message;
        String a;
        Properties rankDB = new Properties();
        Properties rank = new Properties();
        File file = new File("ranks/ranks.db");
        try
        {
            rankDB.load(Files.newInputStream(Paths.get(file.getPath())));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        String prefix;
        if (rankDB.containsKey(entityPlayerMP.username))
        {
            File rankFile = new File("ranks/" + rankDB.getProperty(entityPlayerMP.username) + ".rank");
            try
            {
                rank.load(Files.newInputStream(Paths.get(rankFile.getPath())));
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
            prefix = rank.getProperty("prefix");
        }
        else
        {
            if (!(new File("ranks/default.rank").exists()))
            {
                prefix = ChatColor.lightBlue + "[/rank create default]";
            }
            else
            {
                File rankFile = new File("ranks/default.rank");
                try
                {
                    rank.load(Files.newInputStream(Paths.get(rankFile.getPath())));
                }
                catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
                prefix = rank.getProperty("prefix");
            }
        }
        a = prefix + " " + ChatColor.white + entityPlayerMP.getDisplayName() + ChatColor.white + ": " + s;
        logger.info(a);
        minecraftServer.configManager.sendPacketToAllPlayers(new Packet3Chat(a));
    }

    @Override
    public boolean isCancelled()
    {
        return true;
    }
}
