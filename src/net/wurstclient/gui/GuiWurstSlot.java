/*
 * Copyright © 2014 - 2017 | Wurst-Imperium | All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package net.wurstclient.gui;

import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;

public abstract class GuiWurstSlot
{
	private final Minecraft field_148161_k;
	protected int field_148155_a;
	private int field_148158_l;
	protected int field_148153_b;
	protected int field_148154_c;
	protected int field_148151_d;
	protected int field_148152_e;
	protected final int field_148149_f;
	private int field_148159_m;
	private int field_148156_n;
	protected int field_148150_g;
	protected int field_148162_h;
	protected boolean field_148163_i = true;
	private float field_148157_o = -2.0F;
	private float field_148170_p;
	private float field_148169_q;
	private int field_148168_r = -1;
	private long field_148167_s;
	private boolean field_148166_t = true;
	private boolean field_148165_u;
	protected int field_148160_j;
	private boolean field_148164_v = true;
	
	public GuiWurstSlot(Minecraft par1Minecraft, int par2, int par3, int par4,
		int par5, int par6)
	{
		field_148161_k = par1Minecraft;
		field_148155_a = par2;
		field_148158_l = par3;
		field_148153_b = par4;
		field_148154_c = par5;
		field_148149_f = par6;
		field_148152_e = 0;
		field_148151_d = par2;
	}
	
	public void func_148122_a(int p_148122_1_, int p_148122_2_, int p_148122_3_,
		int p_148122_4_)
	{
		field_148155_a = p_148122_1_;
		field_148158_l = p_148122_2_;
		field_148153_b = p_148122_3_;
		field_148154_c = p_148122_4_;
		field_148152_e = 0;
		field_148151_d = p_148122_1_;
	}
	
	public void func_148130_a(boolean p_148130_1_)
	{
		field_148166_t = p_148130_1_;
	}
	
	protected void func_148133_a(boolean p_148133_1_, int p_148133_2_)
	{
		field_148165_u = p_148133_1_;
		field_148160_j = p_148133_2_;
		
		if(!p_148133_1_)
			field_148160_j = 0;
	}
	
	protected abstract int getSize();
	
	protected abstract void elementClicked(int var1, boolean var2, int var3,
		int var4);
	
	protected abstract boolean isSelected(int var1);
	
	protected int func_148138_e()
	{
		return getSize() * field_148149_f + field_148160_j;
	}
	
	protected abstract void drawBackground();
	
	protected abstract void drawSlot(int var1, int var2, int var3, int var4,
		int var5, int var6);
	
	protected void func_148129_a(int p_148129_1_, int p_148129_2_,
		Tessellator p_148129_3_)
	{}
	
	protected void func_148132_a(int p_148132_1_, int p_148132_2_)
	{}
	
	protected void func_148142_b(int p_148142_1_, int p_148142_2_)
	{}
	
	public int func_148124_c(int p_148124_1_, int p_148124_2_)
	{
		int var3 = field_148152_e + field_148155_a / 2 - func_148139_c() / 2;
		int var4 = field_148152_e + field_148155_a / 2 + func_148139_c() / 2;
		int var5 = p_148124_2_ - field_148153_b - field_148160_j
			+ (int)field_148169_q - 4;
		int var6 = var5 / field_148149_f;
		return p_148124_1_ < func_148137_d() && p_148124_1_ >= var3
			&& p_148124_1_ <= var4 && var6 >= 0 && var5 >= 0 && var6 < getSize()
				? var6 : -1;
	}
	
	public void registerScrollButtons(int p_148134_1_, int p_148134_2_)
	{
		field_148159_m = p_148134_1_;
		field_148156_n = p_148134_2_;
	}
	
	private void func_148121_k()
	{
		int var1 = func_148135_f();
		
		if(var1 < 0)
			var1 /= 2;
		
		if(!field_148163_i && var1 < 0)
			var1 = 0;
		
		if(field_148169_q < 0.0F)
			field_148169_q = 0.0F;
		
		if(field_148169_q > var1)
			field_148169_q = var1;
	}
	
	public int func_148135_f()
	{
		return func_148138_e() - (field_148154_c - field_148153_b - 4);
	}
	
	public int func_148148_g()
	{
		return (int)field_148169_q;
	}
	
	public boolean func_148141_e(int p_148141_1_)
	{
		return p_148141_1_ >= field_148153_b && p_148141_1_ <= field_148154_c;
	}
	
	public void func_148145_f(int p_148145_1_)
	{
		field_148169_q += p_148145_1_;
		func_148121_k();
		field_148157_o = -2.0F;
	}
	
	public void func_148147_a(GuiButton p_148147_1_)
	{
		if(p_148147_1_.enabled)
			if(p_148147_1_.id == field_148159_m)
			{
				field_148169_q -= field_148149_f * 2 / 3;
				field_148157_o = -2.0F;
				func_148121_k();
			}else if(p_148147_1_.id == field_148156_n)
			{
				field_148169_q += field_148149_f * 2 / 3;
				field_148157_o = -2.0F;
				func_148121_k();
			}
	}
	
	public void drawScreen(int p_148128_1_, int p_148128_2_, float p_148128_3_)
	{
		field_148150_g = p_148128_1_;
		field_148162_h = p_148128_2_;
		drawBackground();
		int var4 = getSize();
		int var5 = func_148137_d();
		int var6 = var5 + 6;
		int var9;
		int var10;
		int var13;
		int var19;
		
		if(p_148128_1_ > field_148152_e && p_148128_1_ < field_148151_d
			&& p_148128_2_ > field_148153_b && p_148128_2_ < field_148154_c)
			if(Mouse.isButtonDown(0) && func_148125_i())
			{
				if(field_148157_o == -1.0F)
				{
					boolean var15 = true;
					
					if(p_148128_2_ >= field_148153_b
						&& p_148128_2_ <= field_148154_c)
					{
						int var8 = field_148155_a / 2 - func_148139_c() / 2;
						var9 = field_148155_a / 2 + func_148139_c() / 2;
						var10 = p_148128_2_ - field_148153_b - field_148160_j
							+ (int)field_148169_q - 4;
						int var11 = var10 / field_148149_f;
						
						if(p_148128_1_ >= var8 && p_148128_1_ <= var9
							&& var11 >= 0 && var10 >= 0 && var11 < var4)
						{
							boolean var12 = var11 == field_148168_r
								&& Minecraft.getSystemTime()
									- field_148167_s < 250L;
							elementClicked(var11, var12, p_148128_1_,
								p_148128_2_);
							field_148168_r = var11;
							field_148167_s = Minecraft.getSystemTime();
						}else if(p_148128_1_ >= var8 && p_148128_1_ <= var9
							&& var10 < 0)
						{
							func_148132_a(p_148128_1_ - var8, p_148128_2_
								- field_148153_b + (int)field_148169_q - 4);
							var15 = false;
						}
						
						if(p_148128_1_ >= var5 && p_148128_1_ <= var6)
						{
							field_148170_p = -1.0F;
							var19 = func_148135_f();
							
							if(var19 < 1)
								var19 = 1;
							
							var13 =
								(int)((float)((field_148154_c - field_148153_b)
									* (field_148154_c - field_148153_b))
									/ (float)func_148138_e());
							
							if(var13 < 32)
								var13 = 32;
							
							if(var13 > field_148154_c - field_148153_b - 8)
								var13 = field_148154_c - field_148153_b - 8;
							
							field_148170_p /=
								(float)(field_148154_c - field_148153_b - var13)
									/ (float)var19;
						}else
							field_148170_p = 1.0F;
						
						if(var15)
							field_148157_o = p_148128_2_;
						else
							field_148157_o = -2.0F;
					}else
						field_148157_o = -2.0F;
				}else if(field_148157_o >= 0.0F)
				{
					field_148169_q -=
						(p_148128_2_ - field_148157_o) * field_148170_p;
					field_148157_o = p_148128_2_;
				}
			}else
			{
				try
				{
					for(; !field_148161_k.gameSettings.touchscreen
						&& Mouse.next(); field_148161_k.currentScreen
							.handleMouseInput())
					{
						int var7 = Mouse.getEventDWheel();
						
						if(var7 != 0)
						{
							if(var7 > 0)
								var7 = -1;
							else if(var7 < 0)
								var7 = 1;
							
							field_148169_q += var7 * field_148149_f / 2;
						}
					}
				}catch(IOException e)
				{
					e.printStackTrace();
				}
				
				field_148157_o = -1.0F;
			}
		
		func_148121_k();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_FOG);
		Tessellator ts = Tessellator.getInstance();
		WorldRenderer wr = ts.getWorldRenderer();
		field_148161_k.getTextureManager().bindTexture(Gui.optionsBackground);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		float var16 = 32.0F;
		wr.startDrawingQuads();
		wr.setColorOpaque_I(2105376);
		wr.addVertexWithUV(field_148152_e, field_148154_c, 0.0D,
			field_148152_e / var16,
			(field_148154_c + (int)field_148169_q) / var16);
		wr.addVertexWithUV(field_148151_d, field_148154_c, 0.0D,
			field_148151_d / var16,
			(field_148154_c + (int)field_148169_q) / var16);
		wr.addVertexWithUV(field_148151_d, field_148153_b, 0.0D,
			field_148151_d / var16,
			(field_148153_b + (int)field_148169_q) / var16);
		wr.addVertexWithUV(field_148152_e, field_148153_b, 0.0D,
			field_148152_e / var16,
			(field_148153_b + (int)field_148169_q) / var16);
		ts.draw();
		var9 = field_148152_e + field_148155_a / 2 - func_148139_c() / 2 + 2;
		var10 = field_148153_b + 4 - (int)field_148169_q;
		
		if(field_148165_u)
			func_148129_a(var9, var10, Tessellator.getInstance());
		
		func_148120_b(var9, var10, p_148128_1_, p_148128_2_);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		byte var18 = 4;
		func_148136_c(0, field_148153_b, 255, 255);
		func_148136_c(field_148154_c, field_148158_l, 255, 255);
		GL11.glEnable(GL11.GL_BLEND);
		OpenGlHelper.glBlendFunc(770, 771, 0, 1);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		wr.startDrawingQuads();
		wr.setColorRGBA_I(0, 0);
		wr.addVertexWithUV(field_148152_e, field_148153_b + var18, 0.0D, 0.0D,
			1.0D);
		wr.addVertexWithUV(field_148151_d, field_148153_b + var18, 0.0D, 1.0D,
			1.0D);
		wr.setColorRGBA_I(0, 255);
		wr.addVertexWithUV(field_148151_d, field_148153_b, 0.0D, 1.0D, 0.0D);
		wr.addVertexWithUV(field_148152_e, field_148153_b, 0.0D, 0.0D, 0.0D);
		ts.draw();
		wr.startDrawingQuads();
		wr.setColorRGBA_I(0, 255);
		wr.addVertexWithUV(field_148152_e, field_148154_c, 0.0D, 0.0D, 1.0D);
		wr.addVertexWithUV(field_148151_d, field_148154_c, 0.0D, 1.0D, 1.0D);
		wr.setColorRGBA_I(0, 0);
		wr.addVertexWithUV(field_148151_d, field_148154_c - var18, 0.0D, 1.0D,
			0.0D);
		wr.addVertexWithUV(field_148152_e, field_148154_c - var18, 0.0D, 0.0D,
			0.0D);
		ts.draw();
		var19 = func_148135_f();
		
		if(var19 > 0)
		{
			var13 = (field_148154_c - field_148153_b)
				* (field_148154_c - field_148153_b) / func_148138_e();
			
			if(var13 < 32)
				var13 = 32;
			
			if(var13 > field_148154_c - field_148153_b - 8)
				var13 = field_148154_c - field_148153_b - 8;
			
			int var14 =
				(int)field_148169_q * (field_148154_c - field_148153_b - var13)
					/ var19 + field_148153_b;
			
			if(var14 < field_148153_b)
				var14 = field_148153_b;
			
			wr.startDrawingQuads();
			wr.setColorRGBA_I(0, 255);
			wr.addVertexWithUV(var5, field_148154_c, 0.0D, 0.0D, 1.0D);
			wr.addVertexWithUV(var6, field_148154_c, 0.0D, 1.0D, 1.0D);
			wr.addVertexWithUV(var6, field_148153_b, 0.0D, 1.0D, 0.0D);
			wr.addVertexWithUV(var5, field_148153_b, 0.0D, 0.0D, 0.0D);
			ts.draw();
			wr.startDrawingQuads();
			wr.setColorRGBA_I(8421504, 255);
			wr.addVertexWithUV(var5, var14 + var13, 0.0D, 0.0D, 1.0D);
			wr.addVertexWithUV(var6, var14 + var13, 0.0D, 1.0D, 1.0D);
			wr.addVertexWithUV(var6, var14, 0.0D, 1.0D, 0.0D);
			wr.addVertexWithUV(var5, var14, 0.0D, 0.0D, 0.0D);
			ts.draw();
			wr.startDrawingQuads();
			wr.setColorRGBA_I(12632256, 255);
			wr.addVertexWithUV(var5, var14 + var13 - 1, 0.0D, 0.0D, 1.0D);
			wr.addVertexWithUV(var6 - 1, var14 + var13 - 1, 0.0D, 1.0D, 1.0D);
			wr.addVertexWithUV(var6 - 1, var14, 0.0D, 1.0D, 0.0D);
			wr.addVertexWithUV(var5, var14, 0.0D, 0.0D, 0.0D);
			ts.draw();
		}
		
		func_148142_b(p_148128_1_, p_148128_2_);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	public void func_148143_b(boolean p_148143_1_)
	{
		field_148164_v = p_148143_1_;
	}
	
	public boolean func_148125_i()
	{
		return field_148164_v;
	}
	
	public int func_148139_c()
	{
		return 250;
	}
	
	protected void func_148120_b(int p_148120_1_, int p_148120_2_,
		int p_148120_3_, int p_148120_4_)
	{
		int var5 = getSize();
		Tessellator ts = Tessellator.getInstance();
		WorldRenderer wr = ts.getWorldRenderer();
		
		for(int var7 = 0; var7 < var5; ++var7)
		{
			int var8 = p_148120_2_ + var7 * field_148149_f + field_148160_j;
			int var9 = field_148149_f - 4;
			
			if(var8 <= field_148154_c && var8 + var9 >= field_148153_b)
			{
				if(field_148166_t && isSelected(var7))
				{
					int var10 = field_148152_e + field_148155_a / 2
						- func_148139_c() / 2;
					int var11 = field_148152_e + field_148155_a / 2
						+ func_148139_c() / 2;
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					GL11.glDisable(GL11.GL_TEXTURE_2D);
					wr.startDrawingQuads();
					wr.setColorOpaque_I(8421504);
					wr.addVertexWithUV(var10, var8 + var9 + 2, 0.0D, 0.0D,
						1.0D);
					wr.addVertexWithUV(var11, var8 + var9 + 2, 0.0D, 1.0D,
						1.0D);
					wr.addVertexWithUV(var11, var8 - 2, 0.0D, 1.0D, 0.0D);
					wr.addVertexWithUV(var10, var8 - 2, 0.0D, 0.0D, 0.0D);
					wr.setColorOpaque_I(0);
					wr.addVertexWithUV(var10 + 1, var8 + var9 + 1, 0.0D, 0.0D,
						1.0D);
					wr.addVertexWithUV(var11 - 1, var8 + var9 + 1, 0.0D, 1.0D,
						1.0D);
					wr.addVertexWithUV(var11 - 1, var8 - 1, 0.0D, 1.0D, 0.0D);
					wr.addVertexWithUV(var10 + 1, var8 - 1, 0.0D, 0.0D, 0.0D);
					ts.draw();
					GL11.glEnable(GL11.GL_TEXTURE_2D);
				}
				
				drawSlot(var7, p_148120_1_, var8, var9, p_148120_3_,
					p_148120_4_);
			}
		}
	}
	
	protected int func_148137_d()
	{
		return field_148155_a / 2 + 134;
	}
	
	private void func_148136_c(int p_148136_1_, int p_148136_2_,
		int p_148136_3_, int p_148136_4_)
	{
		Tessellator ts = Tessellator.getInstance();
		WorldRenderer wr = ts.getWorldRenderer();
		field_148161_k.getTextureManager().bindTexture(Gui.optionsBackground);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		float var6 = 32.0F;
		wr.startDrawingQuads();
		wr.setColorRGBA_I(4210752, p_148136_4_);
		wr.addVertexWithUV(field_148152_e, p_148136_2_, 0.0D, 0.0D,
			p_148136_2_ / var6);
		wr.addVertexWithUV(field_148152_e + field_148155_a, p_148136_2_, 0.0D,
			field_148155_a / var6, p_148136_2_ / var6);
		wr.setColorRGBA_I(4210752, p_148136_3_);
		wr.addVertexWithUV(field_148152_e + field_148155_a, p_148136_1_, 0.0D,
			field_148155_a / var6, p_148136_1_ / var6);
		wr.addVertexWithUV(field_148152_e, p_148136_1_, 0.0D, 0.0D,
			p_148136_1_ / var6);
		ts.draw();
	}
	
	public void func_148140_g(int p_148140_1_)
	{
		field_148152_e = p_148140_1_;
		field_148151_d = p_148140_1_ + field_148155_a;
	}
	
	public int func_148146_j()
	{
		return field_148149_f;
	}
}
