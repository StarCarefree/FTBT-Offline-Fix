package cn.scarefree.ftbt_offline_fix.mixin;

import dev.ftb.mods.ftbteams.data.ClientTeam;
import dev.ftb.mods.ftbteams.data.ClientTeamManager;
import dev.ftb.mods.ftbteams.data.KnownClientPlayer;
import dev.ftb.mods.ftbteams.data.TeamManager;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(ClientTeamManager.class)
public class ClientTeamManagerMixin {
    @Shadow(remap = false) public KnownClientPlayer selfKnownPlayer;

    @Inject(method = "initSelfDetails", at = @At("TAIL"), remap = false)
    public void initSelfDetails(UUID selfTeamID, CallbackInfo ci) {
        if(Minecraft.getInstance().getUser().getGameProfile().isLegacy()){
            selfKnownPlayer=new KnownClientPlayer(TeamManager.INSTANCE.getInternalPlayerTeam(Minecraft.getInstance().getUser().getGameProfile().getId()));
        }
    }
}
