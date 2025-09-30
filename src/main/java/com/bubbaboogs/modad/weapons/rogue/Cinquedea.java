package com.bubbaboogs.modad.weapons.rogue;

import com.bubbaboogs.modad.entities.projectile.CinquedeaEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class Cinquedea extends Item {
    public float THROW_SPEED;
    public Cinquedea(Settings settings, float throw_speed) {
        super(settings.maxDamage(340));
        THROW_SPEED = throw_speed;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_TRIDENT_THROW, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
        if(!world.isClient){
            CinquedeaEntity cinquedea = new CinquedeaEntity(world, user);
            cinquedea.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, THROW_SPEED, 0f);
            world.spawnEntity(cinquedea);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if(!user.getAbilities().creativeMode){
            itemStack.damage(1, user, EquipmentSlot.MAINHAND);
        }

        return ActionResult.SUCCESS;
    }
}
