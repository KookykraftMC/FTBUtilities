package com.feed_the_beast.ftbu.net;

import com.feed_the_beast.ftbl.api.FTBLibAPI;
import com.feed_the_beast.ftbl.api.IForgePlayer;
import com.feed_the_beast.ftbl.api.net.LMNetworkWrapper;
import com.feed_the_beast.ftbl.api.net.MessageToServer;
import com.feed_the_beast.ftbl.gui.GuiLoading;
import com.feed_the_beast.ftbu.world.ServerInfoFile;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayerMP;

public class MessageRequestServerInfo extends MessageToServer<MessageRequestServerInfo>
{
    public MessageRequestServerInfo()
    {
    }

    @Override
    public LMNetworkWrapper getWrapper()
    {
        return FTBUNetHandler.NET;
    }

    @Override
    public void fromBytes(ByteBuf io)
    {
    }

    @Override
    public void toBytes(ByteBuf io)
    {
    }

    public GuiScreen openGui()
    {
        sendToServer();
        return new GuiLoading().getWrapper();
    }

    @Override
    public void onMessage(MessageRequestServerInfo m, EntityPlayerMP player)
    {
        IForgePlayer owner = FTBLibAPI.get().getUniverse().getPlayer(player);
        new ServerInfoFile(owner).displayGuide(owner.getPlayer());
    }
}