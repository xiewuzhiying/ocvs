package io.github.xiewuzhiying.ocvs.bus.devices.provider;

import io.github.xiewuzhiying.ocvs.OcvsMod;
import io.github.xiewuzhiying.ocvs.bus.devices.provider.item.ExtendedShipHandlerDeviceProvider;
import io.github.xiewuzhiying.ocvs.bus.devices.provider.item.ShipHandlerDeviceProvider;
import li.cil.oc2r.api.bus.device.provider.ItemDeviceProvider;
import li.cil.oc2r.api.util.Registries;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

public class ProviderRegistry {

    public static final DeferredRegister<ItemDeviceProvider> ITEM_DEVICE_PROVIDERS = DeferredRegister.create(Registries.ITEM_DEVICE_PROVIDER, OcvsMod.MODID);

    public static void initialize() {
        ITEM_DEVICE_PROVIDERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEM_DEVICE_PROVIDERS.register("ship_handler", ShipHandlerDeviceProvider::new);
        ITEM_DEVICE_PROVIDERS.register("extended_ship_handler", ExtendedShipHandlerDeviceProvider::new);
    }
}
