package io.github.xiewuzhiying.ocvs.bus.devices.provider.item;

import io.github.xiewuzhiying.ocvs.OcvsMod;
import io.github.xiewuzhiying.ocvs.bus.devices.rpc.item.ShipHandlerDevice;
import li.cil.oc2r.api.bus.device.ItemDevice;
import li.cil.oc2r.api.bus.device.object.ObjectDevice;
import li.cil.oc2r.api.bus.device.provider.ItemDeviceQuery;
import li.cil.oc2r.common.bus.device.provider.util.AbstractItemDeviceProvider;
import li.cil.oc2r.common.util.BlockLocation;
import li.cil.oc2r.common.util.LocationSupplierUtils;
import net.minecraft.server.level.ServerLevel;
import org.valkyrienskies.core.api.ships.ServerShip;
import org.valkyrienskies.mod.common.VSGameUtilsKt;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class ShipHandlerDeviceProvider extends AbstractItemDeviceProvider {

    public ShipHandlerDeviceProvider() {
        super(OcvsMod.SHIP_HANDLER_ITEM);
    }

    @Override
    protected Optional<ItemDevice> getItemDevice(final ItemDeviceQuery query) {
        return Optional.of(new ObjectDevice(new ShipHandlerDevice(getServerShipFormLocation(LocationSupplierUtils.of(query)))));
    }

    @Override
    protected int getItemDeviceEnergyConsumption(final ItemDeviceQuery query) {
        return 1;
    }

    public static @Nullable ServerShip getServerShipFormLocation(Supplier<Optional<BlockLocation>> location) {
        AtomicReference<ServerShip> serverShip = new AtomicReference<>();
        location.get().ifPresent(l -> l.tryGetLevel().ifPresent(level -> {
            if (!(level instanceof final ServerLevel serverLevel)) {
                return;
            }
            serverShip.set(VSGameUtilsKt.getShipManagingPos(serverLevel, l.blockPos()));
        }));
        return serverShip.get();
    }
}
