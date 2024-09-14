package io.github.xiewuzhiying.ocvs.data;

import li.cil.oc2r.api.API;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public final class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(final PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable final ExistingFileHelper existingFileHelper) {
        super(packOutput, lookupProvider, API.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

    }
}
