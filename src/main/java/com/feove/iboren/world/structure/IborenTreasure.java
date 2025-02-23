package com.feove.iboren.world.structure;

import com.feove.iboren.IborenMod;
import com.mojang.serialization.Codec;
import jdk.nashorn.internal.runtime.logging.DebugLogger;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IborenTreasure extends Structure<NoFeatureConfig> {

    private static final Logger LOGGER = LogManager.getLogger(IborenTreasure.Start.class);

    public IborenTreasure(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public GenerationStage.Decoration step() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGenerator, BiomeProvider biomeProvider, long seed, SharedSeedRandom chunkrandom, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, NoFeatureConfig featureConfig) {
        BlockPos centerOfChunk = new BlockPos(chunkX * 16, 0, chunkZ * 16);
        int landHeight = chunkGenerator.getFirstFreeHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Type.WORLD_SURFACE_WG);
        BlockState topBlock = chunkGenerator.getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ())
                .getBlockState(new BlockPos(centerOfChunk.getX(), landHeight, centerOfChunk.getZ()));

        boolean result = topBlock.getFluidState().isEmpty();
        LOGGER.info("Checking structure eligibility at chunk ({}, {}): {}", chunkX, chunkZ, result);

        return result;
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return Start::new;
    }

    public static class Start extends StructureStart<NoFeatureConfig> {

        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistries, ChunkGenerator chunkGenerator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig noFeatureConfig) {

            LOGGER.info("Generating pieces for Iboren Treasure at chunk ({}, {})", chunkX, chunkZ);

            int x = chunkX * 16;
            int z = chunkZ * 16;
            int y = chunkGenerator.getFirstFreeHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG);
            BlockPos centerPos = new BlockPos(x, y, z);

            JigsawManager.addPieces(
                    dynamicRegistries,
                    new VillageConfig(() -> dynamicRegistries.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY)

                            .get(new ResourceLocation(IborenMod.MOD_ID, "structures/iboren_treasure")
                            ),
                            10
                    ),

                    AbstractVillagePiece::new,
                    chunkGenerator,
                    templateManager,
                    centerPos,
                    this.pieces,
                    this.random,
                    false,
                    true);

            LOGGER.info("Number of pieces generated: {}", this.pieces.size());

            this.pieces.forEach(piece -> piece.move(0, 1, 0));

            if (this.pieces.isEmpty()) {
                LOGGER.error("No pieces were generated for Iboren Treasure at chunk ({}, {})", chunkX, chunkZ);
                return;
            }

            Vector3i structureCenter = this.pieces.get(0).getBoundingBox().getCenter();

            int xOffset = centerPos.getX() - structureCenter.getX();
            int zOffset = centerPos.getZ() - structureCenter.getZ();
            for(StructurePiece structurePiece : this.pieces){
                structurePiece.move(xOffset, 0, zOffset);
            }

            this.calculateBoundingBox();
        }
    }
}
