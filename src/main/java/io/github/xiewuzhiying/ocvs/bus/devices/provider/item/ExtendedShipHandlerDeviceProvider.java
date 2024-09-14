package io.github.xiewuzhiying.ocvs.bus.devices.provider.item;

import io.github.xiewuzhiying.ocvs.OcvsMod;
import io.github.xiewuzhiying.ocvs.bus.devices.rpc.item.ExtendedShipHandlerDevice;
import io.github.xiewuzhiying.ocvs.bus.devices.rpc.item.ShipHandlerDevice;
import li.cil.oc2r.api.bus.device.ItemDevice;
import li.cil.oc2r.api.bus.device.object.ObjectDevice;
import li.cil.oc2r.api.bus.device.provider.ItemDeviceQuery;
import li.cil.oc2r.common.bus.device.provider.util.AbstractItemDeviceProvider;
import li.cil.oc2r.common.util.BlockLocation;
import li.cil.oc2r.common.util.LocationSupplierUtils;
import net.minecraft.server.level.ServerLevel;
import org.apache.commons.lang3.tuple.Pair;
import org.valkyrienskies.core.api.ships.ServerShip;
import org.valkyrienskies.mod.common.VSGameUtilsKt;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class ExtendedShipHandlerDeviceProvider extends AbstractItemDeviceProvider {

    public ExtendedShipHandlerDeviceProvider() {
        super(OcvsMod.EXTENDED_SHIP_HANDLER_ITEM);
    }

    @Override
    protected Optional<ItemDevice> getItemDevice(final ItemDeviceQuery query) {
        Supplier<Optional<BlockLocation>> location = LocationSupplierUtils.of(query);
        AtomicReference<ServerLevel> serverLevel = new AtomicReference<>();
        AtomicReference<ServerShip> ship = new AtomicReference<>();
        location.get().ifPresent(l -> l.tryGetLevel().ifPresent(level -> {
            if (!(level instanceof final ServerLevel serverLevel2)) {
                return;
            }
            serverLevel.set(serverLevel2);
            ship.set(VSGameUtilsKt.getShipManagingPos(serverLevel2, l.blockPos()));
        }));
        if (serverLevel.get() != null && ship.get() != null) {
            return Optional.of(new ObjectDevice(new ExtendedShipHandlerDevice(ship.get(), serverLevel.get())));
        } else {
            return Optional.empty();
        }
    }

    @Override
    protected int getItemDeviceEnergyConsumption(final ItemDeviceQuery query) {
        return 1;
    }
}
