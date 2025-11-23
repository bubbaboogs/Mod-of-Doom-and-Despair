package com.bubbaboogs.modad.weapons.rogue;

import com.bubbaboogs.modad.entities.projectile.CinquedeaEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Position;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import java.util.List;


public class GildedDaggerItem extends Item implements ProjectileItem {
    public float THROW_SPEED = 0.6F;
    public float VELOCITY = 2f;

    public GildedDaggerItem(Item.Properties settings) {
        super(settings.durability(340));
    }

    public static ItemAttributeModifiers createAttributeModifiers() {
        return ItemAttributeModifiers.builder().add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 4.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, -2.9000000953674316, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build();
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand) {
        if (user instanceof Player playerEntity) {
            System.out.println("onStoppedUsing triggered");  // Add this line
            ItemStack stack = user.getItemInHand(hand);
            user.startUsingItem(hand);
            if (!isAboutToBreak(stack)) {
                Holder<SoundEvent> registryEntry = (Holder) EnchantmentHelper.pickHighestLevel(stack, EnchantmentEffectComponents.TRIDENT_SOUND).orElse(SoundEvents.TRIDENT_THROW);
                if (!world.isClientSide()) {
                    stack.hurtWithoutBreaking(1, playerEntity);
                    CinquedeaEntity tridentEntity = new CinquedeaEntity(world, playerEntity, stack);
                    tridentEntity.shootFromRotation(playerEntity, playerEntity.getXRot(), playerEntity.getYRot(), 0.0F, VELOCITY, 1.0F);

                    Vec3 vel = tridentEntity.getDeltaMovement();
                    float yaw = (float)(Mth.atan2(vel.x, vel.z) * (180D / Math.PI));
                    float pitch = (float)(Mth.atan2(vel.y, vel.horizontalDistance()) * (180D / Math.PI));

                    tridentEntity.setYRot(yaw);
                    tridentEntity.setXRot(pitch);

                    world.addFreshEntity(tridentEntity);
                    world.playSound((Player) null, tridentEntity, (SoundEvent) registryEntry.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
                    user.getCooldowns().addCooldown(stack, (int) (THROW_SPEED * 20));
                }

                playerEntity.awardStat(Stats.ITEM_USED.get(this));
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public Projectile asProjectile(Level world, Position pos, ItemStack stack, Direction direction) {
        CinquedeaEntity tridentEntity = new CinquedeaEntity(world, pos.x(), pos.y(), pos.z(), stack.copyWithCount(1));
        tridentEntity.pickup = AbstractArrow.Pickup.DISALLOWED;
        return tridentEntity;
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.NONE;
    }

    public static Tool createToolComponent() {
        return new Tool(List.of(), 1.0F, 2, false);
    }

    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    }


    public boolean canMine(BlockState state, Level world, BlockPos pos, Player miner) {
        return !miner.isCreative();
    }

    private static boolean isAboutToBreak(ItemStack stack) {
        boolean aboutToBreak = stack.getDamageValue() >= stack.getMaxDamage() - 1;
        return aboutToBreak;
    }
}

