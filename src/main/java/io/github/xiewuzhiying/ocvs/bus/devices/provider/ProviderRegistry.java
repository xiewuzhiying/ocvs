package io.github.xiewuzhiying.ocvs.bus.devices.provider;

import io.github.xiewuzhiying.ocvs.bus.devices.provider.item.ShipHandlerDeviceProvider;
import li.cil.oc2r.api.API;
import li.cil.oc2r.api.bus.device.provider.ItemDeviceProvider;
import li.cil.oc2r.api.util.Registries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

public class ProviderRegistry {

    private static final DeferredRegister<ItemDeviceProvider> ITEM_DEVICE_PROVIDERS = DeferredRegister.create(Registries.ITEM_DEVICE_PROVIDER, API.MOD_ID);
    public static final Supplier<IForgeRegistry<ItemDeviceProvider>> ITEM_DEVICE_PROVIDER_REGISTRY = ITEM_DEVICE_PROVIDERS.makeRegistry(RegistryBuilder::new);

    public static void initialize() {
        ITEM_DEVICE_PROVIDERS.register("ship_handler", ShipHandlerDeviceProvider::new);
    }
}
