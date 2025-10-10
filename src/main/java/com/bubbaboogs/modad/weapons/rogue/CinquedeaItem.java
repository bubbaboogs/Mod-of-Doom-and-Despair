package com.bubbaboogs.modad.weapons.rogue;

import com.bubbaboogs.modad.entities.projectile.CinquedeaEntity;
import net.minecraft.block.BlockState;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.item.consume.UseAction;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import java.util.List;


public class CinquedeaItem extends Item implements ProjectileItem {
    public float THROW_SPEED = 0.6F;
    public float VELOCITY = 2f;

    public CinquedeaItem(Item.Settings settings) {
        super(settings.maxDamage(340));
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder().add(EntityAttributes.ATTACK_DAMAGE, new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 4.0, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND).add(EntityAttributes.ATTACK_SPEED, new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.9000000953674316, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND).build();
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (user instanceof PlayerEntity playerEntity) {
            System.out.println("onStoppedUsing triggered");  // Add this line
            ItemStack stack = user.getStackInHand(hand);
            user.setCurrentHand(hand);
                if (!isAboutToBreak(stack)) {
                    RegistryEntry<SoundEvent> registryEntry = (RegistryEntry) EnchantmentHelper.getEffect(stack, EnchantmentEffectComponentTypes.TRIDENT_SOUND).orElse(SoundEvents.ITEM_TRIDENT_THROW);
                    if (!world.isClient()) {
                        stack.damage(1, playerEntity);
                        CinquedeaEntity tridentEntity = new CinquedeaEntity(world, playerEntity, stack);
                        tridentEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, VELOCITY, 1.0F);

                        Vec3d vel = tridentEntity.getVelocity();
                        float yaw = (float)(MathHelper.atan2(vel.x, vel.z) * (180D / Math.PI));
                        float pitch = (float)(MathHelper.atan2(vel.y, vel.horizontalLength()) * (180D / Math.PI));

                        tridentEntity.setYaw(yaw);
                        tridentEntity.setPitch(pitch);

                        world.spawnEntity(tridentEntity);
                        world.playSoundFromEntity((PlayerEntity) null, tridentEntity, (SoundEvent) registryEntry.value(), SoundCategory.PLAYERS, 1.0F, 1.0F);
                        user.getItemCooldownManager().set(stack, (int) (THROW_SPEED * 20));
                    }

                    playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                    return ActionResult.SUCCESS;
                }
            }
        return ActionResult.PASS;
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        CinquedeaEntity tridentEntity = new CinquedeaEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack.copyWithCount(1));
        tridentEntity.pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED;
        return tridentEntity;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    public static ToolComponent createToolComponent() {
        return new ToolComponent(List.of(), 1.0F, 2, false);
    }

    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }


    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }

    private static boolean isAboutToBreak(ItemStack stack) {
        boolean aboutToBreak = stack.getDamage() >= stack.getMaxDamage() - 1;
        return aboutToBreak;
    }
}

