package cn.scarefree.ftbt_offline_fix.mixin;

import dev.ftb.mods.ftbteams.FTBTeams;
import dev.ftb.mods.ftbteams.data.ClientTeam;
import dev.ftb.mods.ftbteams.data.ClientTeamManager;
import dev.ftb.mods.ftbteams.data.KnownClientPlayer;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

@Mixin(ClientTeamManager.class)
public abstract class ClientTeamManagerMixin {
    @Shadow(remap = false) public ClientTeam selfTeam;

    @Shadow(remap = false) @Final public Map<UUID, ClientTeam> teamMap;

    @Shadow(remap = false) public KnownClientPlayer selfKnownPlayer;

    @Shadow(remap = false) @Final public Map<UUID, KnownClientPlayer> knownPlayers;

    @Inject(method = "initSelfDetails", at = @At("HEAD"), cancellable = true, remap = false)
    public void initSelfDetails(UUID selfTeamID, CallbackInfo ci) {
        this.selfTeam = this.teamMap.get(selfTeamID);
        String playerName = Minecraft.getInstance().getUser().getName();
        UUID playerId = UUID.nameUUIDFromBytes(("OfflinePlayer:" + playerName).getBytes(StandardCharsets.UTF_8));
        this.selfKnownPlayer = knownPlayers.get(playerId);
        if (selfKnownPlayer == null) {
            FTBTeams.LOGGER.warn("Local player id {} was not found in the known players list [{}]! FTB Teams will not be able to function correctly!",
                    playerId, String.join(",", knownPlayers.keySet().stream().map(UUID::toString).toList()));
        }
        ci.cancel();
    }
}
