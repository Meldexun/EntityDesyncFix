package meldexun.entity_desync_fix.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.entity.Entity;
import net.minecraft.network.play.server.SPacketEntityTeleport;

@Mixin(SPacketEntityTeleport.class)
public class MixinSPacketEntityTeleport {

	/**
	 * {@link SPacketEntityTeleport#SPacketEntityTeleport(Entity)} at line
	 */
	@Redirect(method = "<init>(Lnet/minecraft/entity/Entity;)V", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;posX:D"))
	public double getPosX(Entity entity) {
		return entity.prevPosX;
	}

	/**
	 * {@link SPacketEntityTeleport#SPacketEntityTeleport(Entity)} at line
	 */
	@Redirect(method = "<init>(Lnet/minecraft/entity/Entity;)V", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;posY:D"))
	public double getPosY(Entity entity) {
		return entity.prevPosY;
	}

	/**
	 * {@link SPacketEntityTeleport#SPacketEntityTeleport(Entity)} at line
	 */
	@Redirect(method = "<init>(Lnet/minecraft/entity/Entity;)V", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;posZ:D"))
	public double getPosZ(Entity entity) {
		return entity.prevPosZ;
	}

}
