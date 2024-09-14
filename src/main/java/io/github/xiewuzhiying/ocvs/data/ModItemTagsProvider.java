package io.github.xiewuzhiying.ocvs.data;

import io.github.xiewuzhiying.ocvs.OcvsMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

import static li.cil.oc2r.common.tags.ItemTags.DEVICES_CARD;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(
            final PackOutput output,
            final CompletableFuture<HolderLookup.Provider> lookupProvider,
            final CompletableFuture<TagLookup<Block>> tagLookup,
            final String modId,
            @Nullable final ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, tagLookup, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(DEVICES_CARD).add(
                OcvsMod.SHIP_HANDLER_ITEM.get(),
                OcvsMod.EXTENDED_SHIP_HANDLER_ITEM.get()
        );
    }
}
