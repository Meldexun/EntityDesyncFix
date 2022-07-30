package meldexun.entity_desync_fix.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import meldexun.entity_desync_fix.util.IPrevMotion;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTrackerEntry;

@Mixin(EntityTrackerEntry.class)
public class MixinEntityTrackerEntry {

	/** {@link EntityTrackerEntry#updatePlayerList(List)} */
	@Redirect(method = "updatePlayerList", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;posX:D"))
	public double getPosX(Entity entity) {
		return entity.prevPosX;
	}

	/** {@link EntityTrackerEntry#updatePlayerList(List)} */
	@Redirect(method = "updatePlayerList", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;posY:D"))
	public double getPosY(Entity entity) {
		return entity.prevPosY;
	}

	/** {@link EntityTrackerEntry#updatePlayerList(List)} */
	@Redirect(method = "updatePlayerList", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;posZ:D"))
	public double getPosZ(Entity entity) {
		return entity.prevPosZ;
	}



	/** {@link EntityTrackerEntry#updatePlayerList(List)} */
	@Redirect(method = "updatePlayerList", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;motionX:D"))
	public double getMotionX(Entity entity) {
		return ((IPrevMotion) entity).getPrevMotionX();
	}

	/** {@link EntityTrackerEntry#updatePlayerList(List)} */
	@Redirect(method = "updatePlayerList", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;motionY:D"))
	public double getMotionY(Entity entity) {
		return ((IPrevMotion) entity).getPrevMotionY();
	}

	/** {@link EntityTrackerEntry#updatePlayerList(List)} */
	@Redirect(method = "updatePlayerList", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;motionZ:D"))
	public double getMotionZ(Entity entity) {
		return ((IPrevMotion) entity).getPrevMotionZ();
	}

}
