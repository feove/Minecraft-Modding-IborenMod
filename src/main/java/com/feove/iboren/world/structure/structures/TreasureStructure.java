package com.feove.iboren.world.structure.structures;

import com.mojang.serialization.Codec;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class TreasureStructure extends Structure<NoFeatureConfig> {

    public TreasureStructure(Codec<NoFeatureConfig> codec) {

        super(codec);
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {

        return Start::new;
    }

    public static class Start extends StructureStart<NoFeatureConfig> {

        public Start(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox box, int references, long seed) {
            super((Structure<NoFeatureConfig>) structure, chunkX, chunkZ, box, references, seed);
        }


        @Override
        public void generatePieces(DynamicRegistries dynamicRegistries, ChunkGenerator ChunkGen, TemplateManager templateManager, int p_230364_4_, int p_230364_5_, Biome p_230364_6_, NoFeatureConfig p_230364_7_) {
        }
    }
}