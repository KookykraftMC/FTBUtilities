package com.feed_the_beast.ftbu.cmd;

import com.feed_the_beast.ftbl.api.cmd.CommandLM;
import com.latmod.lib.util.LMFileUtils;
import com.latmod.lib.util.LMServerUtils;
import com.latmod.lib.util.LMUtils;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

import java.io.File;

public class CmdRestart extends CommandLM
{
    public CmdRestart()
    {
        super("restart");
    }

    public static void restart()
    {
        LMFileUtils.newFile(new File(LMUtils.folderMinecraft, "autostart.stamp"));
        LMServerUtils.getServer().initiateShutdown();
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender ics, String[] args) throws CommandException
    {
        restart();
    }
}