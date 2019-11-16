package com.drizzs.testmod.world;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;

import static com.drizzs.testmod.items.ItemRegistry.ISLANDPIECE;

public class IslandPiece extends TemplateStructurePiece {

    private final String templateName;
    private final IslandVariant variant;
    private final Rotation rotation;
    private final Mirror mirror;

    public IslandPiece(TemplateManager templateManager, IslandVariant variant, String templateName, BlockPos templatePosition, Rotation rotation) {
        this(templateManager, variant, templateName, templatePosition, rotation, Mirror.NONE);
    }

    public IslandPiece(TemplateManager templateManager, IslandVariant variant, String templateName, BlockPos templatePosition, Rotation rotation, Mirror mirror) {
        super(ISLANDPIECE, 0);
        this.templateName = templateName;
        this.variant = variant;
        this.templatePosition = templatePosition;
        this.rotation = rotation;
        this.mirror = mirror;
        this.loadTemplate(templateManager);
    }

    public IslandPiece(TemplateManager templateManager, CompoundNBT nbt) {
        super(ISLANDPIECE, nbt);
        this.templateName = nbt.getString("Template");
        this.variant = IslandVariant.getVariantFromIndex(nbt.getInt("Variant"));
        this.rotation = Rotation.valueOf(nbt.getString("Rotation"));
        this.mirror = Mirror.valueOf(nbt.getString("Mirror"));
        this.loadTemplate(templateManager);
    }

    private void loadTemplate(TemplateManager templateManager) {
        Template template = templateManager.getTemplateDefaulted(new ResourceLocation("testmod:structures/"+this.templateName));
        PlacementSettings placementsettings = (new PlacementSettings()).setIgnoreEntities(true).setRotation(this.rotation).setMirror(this.mirror).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
        this.setup(template, this.templatePosition, placementsettings);
    }

    @Override
    protected void readAdditional(CompoundNBT tagCompound) {
        super.readAdditional(tagCompound);
        tagCompound.putString("Template", this.templateName);
        tagCompound.putInt("Variant", this.variant.getIndex());
        tagCompound.putString("Rotation", this.placeSettings.getRotation().name());
        tagCompound.putString("Mirror", this.placeSettings.getMirror().name());
    }

    @Override
    protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox mbb) {
        //happens without this stuff
        switch (function) {
            case "grassworld:grassfloor":
              worldIn.setBlockState(pos, this.variant.getGrassBlock(), 2);
                break;
            case "grassworld:lakefluid":
                worldIn.setBlockState(pos, this.variant.getLakeFluid(), 2);
                break;
            case "grassworld:actualgrass":
                worldIn.setBlockState(pos, this.variant.getTallGrass(), 2);
              break;
        }
    }
}