package com.feed_the_beast.ftbu.client;

import com.feed_the_beast.ftbl.api.FTBLibAPI;
import com.feed_the_beast.ftbl.api.client.FTBLibClient;
import com.feed_the_beast.ftbl.api.gui.GuiHelper;
import com.feed_the_beast.ftbl.api.gui.GuiIcons;
import com.feed_the_beast.ftbl.api.gui.IMouseButton;
import com.feed_the_beast.ftbl.api_impl.SidebarButton;
import com.feed_the_beast.ftbu.FTBUFinals;
import com.feed_the_beast.ftbu.gui.GuiClaimChunks;
import com.feed_the_beast.ftbu.gui.guide.local.InfoPageLocalGuideRepoList;
import com.feed_the_beast.ftbu.net.MessageRequestServerInfo;
import com.latmod.lib.EnumEnabled;
import com.latmod.lib.util.LMUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class FTBUActions
{
    public static void init()
    {
        FTBLibAPI.get().getRegistries().sidebarButtons().register(new ResourceLocation(FTBUFinals.MOD_ID, "guide"), new SidebarButton(0, GuiIcons.BOOK, EnumEnabled.ENABLED)
        {
            @Override
            public void onClicked(IMouseButton button)
            {
                GuiHelper.playClickSound();
                InfoPageLocalGuideRepoList.openGui();
            }
        });

        FTBLibAPI.get().getRegistries().sidebarButtons().register(new ResourceLocation(FTBUFinals.MOD_ID, "server_info"), new SidebarButton(0, GuiIcons.BOOK_RED, EnumEnabled.ENABLED)
        {
            @Override
            public void onClicked(IMouseButton button)
            {
                Minecraft.getMinecraft().displayGuiScreen(new MessageRequestServerInfo().openGui());
            }

            @Override
            public boolean isVisible()
            {
                //FIXME: return FTBUWorldData.isLoadedW(ForgeWorldSP.inst);
                return true;
            }
        });

        FTBLibAPI.get().getRegistries().sidebarButtons().register(new ResourceLocation(FTBUFinals.MOD_ID, "claimed_chunks"), new SidebarButton(0, GuiIcons.MAP, EnumEnabled.ENABLED)
        {
            @Override
            public void onClicked(IMouseButton button)
            {
                new GuiClaimChunks().openGui();
            }

            @Override
            public boolean isVisible()
            {
                //FIXME: return FTBUWorldData.isLoadedW(ForgeWorldSP.inst);
                return true;
            }
        });

        FTBLibAPI.get().getRegistries().sidebarButtons().register(new ResourceLocation(FTBUFinals.MOD_ID, "trash_can"), new SidebarButton(0, GuiIcons.BIN, EnumEnabled.ENABLED)
        {
            @Override
            public void onClicked(IMouseButton button)
            {
                FTBLibClient.execClientCommand("/ftb trash_can", false);
            }
        });

        FTBLibAPI.get().getRegistries().sidebarButtons().register(new ResourceLocation(FTBUFinals.MOD_ID, "shop"), new SidebarButton(0, GuiIcons.MONEY_BAG, EnumEnabled.ENABLED)
        {
            @Override
            public void onClicked(IMouseButton button)
            {
                FTBLibClient.execClientCommand("/ftb shop", false);
            }

            @Override
            public boolean isVisible()
            {
                return LMUtils.DEV_ENV;
            }
        });

        /*FTBLibAPI.get().getRegistries().sidebarButtons().register(new ResourceLocation(FTBUFinals.MOD_ID, "mail"), new PlayerAction(0, GuiIcons.feather)
        {
            @Override
            @SideOnly(Side.CLIENT)
            public void onClicked(ForgePlayerSP player)
            {
            }

            @Override
            @SideOnly(Side.CLIENT)
            public boolean isVisibleFor(ForgePlayerSP player)
            {
                return FTBLib.DEV_ENV && super.isVisibleFor(player);
            }
        });*/
    }
}