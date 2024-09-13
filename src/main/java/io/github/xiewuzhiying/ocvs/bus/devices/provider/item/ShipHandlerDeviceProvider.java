package io.github.xiewuzhiying.ocvs.bus.devices.provider.item;

import io.github.xiewuzhiying.ocvs.OcvsMod;
import io.github.xiewuzhiying.ocvs.bus.devices.rpc.item.ShipHandlerDevice;
import li.cil.oc2r.api.bus.device.ItemDevice;
import li.cil.oc2r.api.bus.device.provider.ItemDeviceQuery;
import li.cil.oc2r.common.bus.device.provider.util.AbstractItemDeviceProvider;
import li.cil.oc2r.common.util.LocationSupplierUtils;
import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.NotNull;
import org.valkyrienskies.core.api.ships.ServerShip;
import org.valkyrienskies.mod.common.VSGameUtilsKt;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class ShipHandlerDeviceProvider extends AbstractItemDeviceProvider {

    public ShipHandlerDeviceProvider() {
        super(OcvsMod.EXAMPLE_ITEM);
    }

    @Override
    protected @NotNull Optional<ItemDevice> getItemDevice(@NotNull ItemDeviceQuery query) {
        AtomicReference<ServerShip> ship = new AtomicReference<>();
        LocationSupplierUtils.of(query).get().ifPresent(l -> l.tryGetLevel().ifPresent(level -> {
            if (!(level instanceof final ServerLevel serverLevel)) {
                return;
            }

            ship.set(VSGameUtilsKt.getShipManagingPos(serverLevel, l.blockPos()));
        }));
        if(ship.get() != null) {
            return Optional.of(new ShipHandlerDevice(query.getItemStack(), ship.get()));
        } else {
            return Optional.empty();
        }
    }
}
