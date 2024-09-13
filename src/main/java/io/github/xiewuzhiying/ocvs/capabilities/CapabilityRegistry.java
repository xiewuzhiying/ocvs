package io.github.xiewuzhiying.ocvs.capabilities;

import li.cil.oc2r.api.API;
import li.cil.oc2r.common.capabilities.Capabilities;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.valkyrienskies.core.api.ships.ServerShip;

@Mod.EventBusSubscriber(modid = API.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class CapabilityRegistry {
    static final Capability<ServerShip> SHIP_HANDLER = CapabilityManager.get(new CapabilityToken<>() { });

    @SubscribeEvent
    public static void initialize(final RegisterCapabilitiesEvent event) {
        Capabilities.registerCapabilities(event::register);
    }
}
