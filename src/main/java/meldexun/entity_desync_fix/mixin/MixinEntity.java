package meldexun.entity_desync_fix.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;

@Mixin(Entity.class)
public class MixinEntity implements IPrevMotion {

	@Unique
	private double prevMotionX;
	@Unique
	private double prevMotionY;
	@Unique
	private double prevMotionZ;
	@Shadow
	private double motionX;
	@Shadow
	private double motionY;
	@Shadow
	private double motionZ;

	/**
	 * {@link Entity#onUpdate()} at line 
	 */
	@Inject(method = "onUpdate", at = @At("HEAD"))
	public void onOnUpdate(CallbackInfo info) {
		prevMotionX = motionX;
		prevMotionY = motionY;
		prevMotionZ = motionZ;
	}

	@Override
	public double getPrevMotionX() {
		return prevMotionX;
	}

	@Override
	public double getPrevMotionY() {
		return prevMotionY;
	}

	@Override
	public double getPrevMotionZ() {
		return prevMotionZ;
	}

}
