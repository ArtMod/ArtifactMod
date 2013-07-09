package artifactmod.client.renderer.tileentity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelArtifactHarness extends ModelBase {
	ModelRenderer TopLeft;
	ModelRenderer MiddleLeft;
	ModelRenderer BottomLeft;
	ModelRenderer TopRight;
	ModelRenderer MiddleRight;
	ModelRenderer BottomRight;
	ModelRenderer TopRightRung;
	ModelRenderer MiddleLeftRung;
	ModelRenderer BottomRightRung;
	ModelRenderer BottomLeftRung;
	ModelRenderer MiddleRightRung;
	ModelRenderer TopLeftRung;

	public ModelArtifactHarness() {
		textureWidth = 24;
		textureHeight = 17;

		TopLeft = new ModelRenderer(this, 0, 0);
		TopLeft.addBox(0F, 0F, 0F, 3, 4, 2);
		TopLeft.setRotationPoint(-6.9F, 9.1F, -1F);
		TopLeft.setTextureSize(24, 17);
		TopLeft.mirror = true;
		setRotation(TopLeft, 0F, 0F, 0.5576851F);
		MiddleLeft = new ModelRenderer(this, 0, 0);
		MiddleLeft.addBox(0F, 0F, 0F, 3, 10, 2);
		MiddleLeft.setRotationPoint(-9F, 12.5F, -1F);
		MiddleLeft.setTextureSize(24, 17);
		MiddleLeft.mirror = true;
		setRotation(MiddleLeft, 0F, 0F, 0F);
		BottomLeft = new ModelRenderer(this, 0, 0);
		BottomLeft.addBox(0F, 0F, 0F, 3, 4, 2);
		BottomLeft.setRotationPoint(-9F, 22.5F, -1F);
		BottomLeft.setTextureSize(24, 17);
		BottomLeft.mirror = true;
		setRotation(BottomLeft, 0F, 0F, -0.5576792F);
		TopRight = new ModelRenderer(this, 0, 0);
		TopRight.addBox(0F, 0F, 0F, 3, 4, 2);
		TopRight.setRotationPoint(4.3F, 10.7F, -1F);
		TopRight.setTextureSize(24, 17);
		TopRight.mirror = true;
		setRotation(TopRight, 0F, 0F, -0.5576792F);
		MiddleRight = new ModelRenderer(this, 0, 0);
		MiddleRight.addBox(0F, 0F, 0F, 3, 10, 2);
		MiddleRight.setRotationPoint(5.9F, 12.5F, -1F);
		MiddleRight.setTextureSize(24, 17);
		MiddleRight.mirror = true;
		setRotation(MiddleRight, 0F, 0F, 0F);
		BottomRight = new ModelRenderer(this, 0, 0);
		BottomRight.addBox(0F, 0F, 0F, 3, 4, 2);
		BottomRight.setRotationPoint(6.4F, 21F, -1F);
		BottomRight.setTextureSize(24, 17);
		BottomRight.mirror = true;
		setRotation(BottomRight, 0F, 0F, 0.5576851F);
		TopRightRung = new ModelRenderer(this, 13, 0);
		TopRightRung.addBox(0F, 0F, 0F, 2, 4, 1);
		TopRightRung.setRotationPoint(5.2F, 10.8F, -0.5F);
		TopRightRung.setTextureSize(24, 17);
		TopRightRung.mirror = true;
		setRotation(TopRightRung, 0F, 0F, 0.9294712F);
		MiddleLeftRung = new ModelRenderer(this, 13, 0);
		MiddleLeftRung.addBox(0F, 0F, 0F, 2, 4, 1);
		MiddleLeftRung.setRotationPoint(-4.9F, 16.7F, -0.5F);
		MiddleLeftRung.setTextureSize(24, 17);
		MiddleLeftRung.mirror = true;
		setRotation(MiddleLeftRung, 0F, 0F, 1.570796F);
		BottomRightRung = new ModelRenderer(this, 12, 0);
		BottomRightRung.addBox(0F, 0F, 0F, 2, 4, 1);
		BottomRightRung.setRotationPoint(6.8F, 22.7F, -0.5F);
		BottomRightRung.setTextureSize(24, 17);
		BottomRightRung.mirror = true;
		setRotation(BottomRightRung, 0F, 0F, 2.156365F);
		BottomLeftRung = new ModelRenderer(this, 14, 0);
		BottomLeftRung.addBox(0F, 0F, 0F, 2, 4, 1);
		BottomLeftRung.setRotationPoint(-3.3F, 20.5F, -0.5F);
		BottomLeftRung.setTextureSize(24, 17);
		BottomLeftRung.mirror = true;
		setRotation(BottomLeftRung, 0F, 0F, 0.9294712F);
		MiddleRightRung = new ModelRenderer(this, 13, 0);
		MiddleRightRung.addBox(0F, 0F, 0F, 2, 4, 1);
		MiddleRightRung.setRotationPoint(8.8F, 16.7F, -0.5F);
		MiddleRightRung.setTextureSize(24, 17);
		MiddleRightRung.mirror = true;
		setRotation(MiddleRightRung, 0F, 0F, 1.570796F);
		TopLeftRung = new ModelRenderer(this, 12, 0);
		TopLeftRung.addBox(0F, 0F, 0F, 2, 4, 1);
		TopLeftRung.setRotationPoint(-1.9F, 13.2F, -0.5F);
		TopLeftRung.setTextureSize(24, 17);
		TopLeftRung.mirror = true;
		setRotation(TopLeftRung, 0F, 0F, 2.156365F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		TopLeft.render(f5);
		MiddleLeft.render(f5);
		BottomLeft.render(f5);
		TopRight.render(f5);
		MiddleRight.render(f5);
		BottomRight.render(f5);
		TopRightRung.render(f5);
		MiddleLeftRung.render(f5);
		BottomRightRung.render(f5);
		BottomLeftRung.render(f5);
		MiddleRightRung.render(f5);
		TopLeftRung.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
