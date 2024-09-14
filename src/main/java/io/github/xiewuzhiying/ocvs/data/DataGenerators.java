package io.github.xiewuzhiying.ocvs.data;

import io.github.xiewuzhiying.ocvs.OcvsMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {
    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {
        final DataGenerator generator = event.getGenerator();
        final ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        var blockTagsProvider = generator.addProvider(event.includeServer(), (DataProvider.Factory<ModBlockTagsProvider>) output -> new ModBlockTagsProvider(output, event.getLookupProvider(), existingFileHelper));
        generator.addProvider(event.includeServer(), (DataProvider.Factory<ModItemTagsProvider>) output -> new ModItemTagsProvider(output, event.getLookupProvider(), blockTagsProvider.contentsGetter(), OcvsMod.MODID, existingFileHelper));
    }
}
