package meldexun.entity_desync_fix.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.entity.Entity;
import net.minecraft.network.play.server.SPacketEntityVelocity;

@Mixin(SPacketEntityVelocity.class)
public class MixinSPacketEntityVelocity {

	/**
	 * {@link SPacketEntityVelocity#SPacketEntityVelocity(Entity)} at line
	 */
	@Redirect(method = "<init>(Lnet/minecraft/entity/Entity;)V", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;motionX:D"))
	private static double getMotionX(Entity entity) {
		return ((IPrevMotion) entity).getPrevMotionX();
	}

	/**
	 * {@link SPacketEntityVelocity#SPacketEntityVelocity(Entity)} at line
	 */
	@Redirect(method = "<init>(Lnet/minecraft/entity/Entity;)V", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;motionY:D"))
	private static double getMotionY(Entity entity) {
		return ((IPrevMotion) entity).getPrevMotionY();
	}

	/**
	 * {@link SPacketEntityVelocity#SPacketEntityVelocity(Entity)} at line
	 */
	@Redirect(method = "<init>(Lnet/minecraft/entity/Entity;)V", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;motionZ:D"))
	private static double getMotionZ(Entity entity) {
		return ((IPrevMotion) entity).getPrevMotionZ();
	}

}