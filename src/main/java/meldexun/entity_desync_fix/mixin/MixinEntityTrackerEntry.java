package meldexun.entity_desync_fix.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import meldexun.entity_desync_fix.util.IPrevMotion;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.entity.player.EntityPlayerMP;

@Mixin(EntityTrackerEntry.class)
public class MixinEntityTrackerEntry {

	/** {@link EntityTrackerEntry#EntityTrackerEntry(Entity, int, int, int, boolean)} */
	@Inject(method = "<init>", at = @At("RETURN"))
	public void init(Entity entityIn, int rangeIn, int maxRangeIn, int updateFrequencyIn, boolean sendVelocityUpdatesIn, CallbackInfo info) {
		entityIn.prevPosX = entityIn.posX;
		entityIn.prevPosY = entityIn.posY;
		entityIn.prevPosZ = entityIn.posZ;
		((IPrevMotion) entityIn).setPrevMotionX(entityIn.motionX);
		((IPrevMotion) entityIn).setPrevMotionY(entityIn.motionY);
		((IPrevMotion) entityIn).setPrevMotionZ(entityIn.motionZ);
	}



	/** {@link EntityTrackerEntry#updatePlayerList(List)} */
	@Redirect(method = "updatePlayerList", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getDistanceSq(DDD)D"))
	public double getDistanceSq(Entity entity, double x, double y, double z) {
		double dx = x - entity.prevPosX;
		double dy = y - entity.prevPosY;
		double dz = z - entity.prevPosZ;
		return dx * dx + dy * dy + dz * dz;
	}



	/** {@link EntityTrackerEntry#updatePlayerList(List)} */
	@Redirect(method = "updatePlayerList", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;posX:D"))
	public double getPosX1(Entity entity) {
		return entity.prevPosX;
	}

	/** {@link EntityTrackerEntry#updatePlayerList(List)} */
	@Redirect(method = "updatePlayerList", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;posY:D"))
	public double getPosY1(Entity entity) {
		return entity.prevPosY;
	}

	/** {@link EntityTrackerEntry#updatePlayerList(List)} */
	@Redirect(method = "updatePlayerList", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;posZ:D"))
	public double getPosZ1(Entity entity) {
		return entity.prevPosZ;
	}



	/** {@link EntityTrackerEntry#updatePlayerList(List)} */
	@Redirect(method = "updatePlayerList", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;motionX:D"))
	public double getMotionX1(Entity entity) {
		return ((IPrevMotion) entity).getPrevMotionX();
	}

	/** {@link EntityTrackerEntry#updatePlayerList(List)} */
	@Redirect(method = "updatePlayerList", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;motionY:D"))
	public double getMotionY1(Entity entity) {
		return ((IPrevMotion) entity).getPrevMotionY();
	}

	/** {@link EntityTrackerEntry#updatePlayerList(List)} */
	@Redirect(method = "updatePlayerList", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;motionZ:D"))
	public double getMotionZ1(Entity entity) {
		return ((IPrevMotion) entity).getPrevMotionZ();
	}



	/** {@link EntityTrackerEntry#updatePlayerEntity(EntityPlayerMP)} */
	@Redirect(method = "updatePlayerEntity", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;motionX:D"))
	public double getMotionX2(Entity entity) {
		return ((IPrevMotion) entity).getPrevMotionX();
	}

	/** {@link EntityTrackerEntry#updatePlayerEntity(EntityPlayerMP)} */
	@Redirect(method = "updatePlayerEntity", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;motionY:D"))
	public double getMotionY2(Entity entity) {
		return ((IPrevMotion) entity).getPrevMotionY();
	}

	/** {@link EntityTrackerEntry#updatePlayerEntity(EntityPlayerMP)} */
	@Redirect(method = "updatePlayerEntity", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;motionZ:D"))
	public double getMotionZ2(Entity entity) {
		return ((IPrevMotion) entity).getPrevMotionZ();
	}



	/** {@link EntityTrackerEntry#isVisibleTo(EntityPlayerMP)} */
	@Redirect(method = "isVisibleTo", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/EntityPlayerMP;posX:D"))
	public double getPosX2(EntityPlayerMP player) {
		return player.prevPosX;
	}

	/** {@link EntityTrackerEntry#isVisibleTo(EntityPlayerMP)} */
	@Redirect(method = "isVisibleTo", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/EntityPlayerMP;posZ:D"))
	public double getPosZ2(EntityPlayerMP player) {
		return player.prevPosZ;
	}

}
