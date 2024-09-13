package io.github.xiewuzhiying.ocvs.capabilities;

import net.minecraftforge.common.capabilities.Capability;
import org.valkyrienskies.core.api.ships.ServerShip;

public final class Capabilities {
    public static Capability<ServerShip> shipHandler() {
        return CapabilityRegistry.SHIP_HANDLER;
    }
}
