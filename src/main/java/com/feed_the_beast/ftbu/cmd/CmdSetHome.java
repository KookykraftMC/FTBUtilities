package com.feed_the_beast.ftbu.cmd;

import com.feed_the_beast.ftbl.api.FTBLibAPI;
import com.feed_the_beast.ftbl.api.IForgePlayer;
import com.feed_the_beast.ftbl.api.cmd.CommandLM;
import com.feed_the_beast.ftbu.FTBUPermissions;
import com.feed_the_beast.ftbu.api.FTBULang;
import com.feed_the_beast.ftbu.world.FTBUPlayerData;
import com.latmod.lib.math.EntityDimPos;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class CmdSetHome extends CommandLM
{
    public CmdSetHome()
    {
        super("sethome");
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    @Override
    public String getCommandUsage(ICommandSender ics)
    {
        return '/' + commandName + " <ID>";
    }

    @Override
    public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos)
    {
        if(args.length == 1)
        {
            return getListOfStringsMatchingLastWord(args, FTBUPlayerData.get(FTBLibAPI.get().getUniverse().getPlayer(sender)).listHomes());
        }
        return null;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender ics, String[] args) throws CommandException
    {
        EntityPlayerMP ep = getCommandSenderAsPlayer(ics);
        IForgePlayer p = getForgePlayer(ep);
        FTBUPlayerData d = FTBUPlayerData.get(p);
        checkArgs(args, 1, "<home>");

        args[0] = args[0].toLowerCase();

        int maxHomes = FTBUPermissions.HOMES_MAX.get(p.getProfile());

        if(maxHomes <= 0 || d.homesSize() >= maxHomes)
        {
            if(maxHomes == 0 || d.getHome(args[0]) == null)
            {
                throw FTBULang.HOME_LIMIT.commandError();
            }
        }

        d.setHome(args[0], new EntityDimPos(ep).toBlockDimPos());
        FTBULang.HOME_SET.printChat(ics, args[0]);
    }
}