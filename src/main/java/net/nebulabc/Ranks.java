package net.nebulabc;

import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ranks extends Plugin
{
    public Ranks(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start()
    {
        try
        {
            File folder = new File("ranks");
            if (!folder.exists()) folder.mkdirs();
            File file = new File("ranks/ranks.db");
            if (!file.exists()) file.createNewFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void stop()
    {
        // Plugin shutdown logic.
    }
}
