package com.bubbaboogs.modad.weapons.rogue;

import com.bubbaboogs.modad.entities.projectile.CinquedeaEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Cinquedea extends Item {
    public float THROW_SPEED;
    public Cinquedea(Properties settings, float throw_speed) {
        super(settings.durability(340));
        THROW_SPEED = throw_speed;
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand){
        ItemStack itemStack = user.getItemInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.TRIDENT_THROW, SoundSource.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
        if(!world.isClientSide()){
            CinquedeaEntity cinquedea = new CinquedeaEntity(world, user);
            cinquedea.shootFromRotation(user, user.getXRot(), user.getYRot(), 0.0f, THROW_SPEED, 0f);
            world.addFreshEntity(cinquedea);
        }

        user.awardStat(Stats.ITEM_USED.get(this));
        if(!user.getAbilities().instabuild){
            itemStack.hurtAndBreak(1, user, EquipmentSlot.MAINHAND);
        }

        return InteractionResult.SUCCESS;
    }
}
