/*
 * Copyright © 2014 - 2017 | Wurst-Imperium | All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package net.wurstclient.utils;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class RenderUtils
{
	private static final AxisAlignedBB DEFAULT_AABB =
		new AxisAlignedBB(0, 0, 0, 1, 1, 1);
	
	public static void nukerBox(BlockPos blockPos, float damage)
	{
		double x = blockPos.getX()
			- Minecraft.getMinecraft().getRenderManager().renderPosX;
		double y = blockPos.getY()
			- Minecraft.getMinecraft().getRenderManager().renderPosY;
		double z = blockPos.getZ()
			- Minecraft.getMinecraft().getRenderManager().renderPosZ;
		GL11.glBlendFunc(770, 771);
		GL11.glEnable(GL_BLEND);
		GL11.glLineWidth(1.0F);
		GL11.glColor4d(damage, 1 - damage, 0, 0.15F);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		drawColorBox(new AxisAlignedBB(x + 0.5 - damage / 2,
			y + 0.5 - damage / 2, z + 0.5 - damage / 2, x + 0.5 + damage / 2,
			y + 0.5 + damage / 2, z + 0.5 + damage / 2));
		GL11.glColor4d(0, 0, 0, 0.5F);
		RenderGlobal.drawOutlinedBoundingBox(
			new AxisAlignedBB(x + 0.5 - damage / 2, y + 0.5 - damage / 2,
				z + 0.5 - damage / 2, x + 0.5 + damage / 2,
				y + 0.5 + damage / 2, z + 0.5 + damage / 2),
			-1);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		GL11.glDisable(GL_BLEND);
	}
	
	public static void searchBox(BlockPos blockPos)
	{
		double x = blockPos.getX()
			- Minecraft.getMinecraft().getRenderManager().renderPosX;
		double y = blockPos.getY()
			- Minecraft.getMinecraft().getRenderManager().renderPosY;
		double z = blockPos.getZ()
			- Minecraft.getMinecraft().getRenderManager().renderPosZ;
		GL11.glBlendFunc(770, 771);
		GL11.glEnable(GL_BLEND);
		GL11.glLineWidth(1.0F);
		float sinus =
			1F - MathHelper.abs(MathHelper.sin(Minecraft.getSystemTime()
				% 10000L / 10000.0F * (float)Math.PI * 4.0F) * 1F);
		GL11.glColor4d(1 - sinus, sinus, 0, 0.15);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		drawColorBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0));
		GL11.glColor4d(0, 0, 0, 0.5);
		RenderGlobal.drawOutlinedBoundingBox(
			new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), -1);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		GL11.glDisable(GL_BLEND);
	}
	
	public static void drawColorBox(AxisAlignedBB axisalignedbb)
	{
		Tessellator ts = Tessellator.getInstance();
		WorldRenderer wr = ts.getWorldRenderer();
		wr.startDrawingQuads();// Starts X.
		wr.addVertex(axisalignedbb.minX, axisalignedbb.minY,
			axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.maxY,
			axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.minY,
			axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.maxY,
			axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.minY,
			axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.maxY,
			axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.minY,
			axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.maxY,
			axisalignedbb.maxZ);
		ts.draw();
		wr.startDrawingQuads();
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.maxY,
			axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.minY,
			axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.maxY,
			axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.minY,
			axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.maxY,
			axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.minY,
			axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.maxY,
			axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.minY,
			axisalignedbb.maxZ);
		ts.draw();// Ends X.
		wr.startDrawingQuads();// Starts Y.
		wr.addVertex(axisalignedbb.minX, axisalignedbb.maxY,
			axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.maxY,
			axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.maxY,
			axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.maxY,
			axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.maxY,
			axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.maxY,
			axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.maxY,
			axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.maxY,
			axisalignedbb.minZ);
		ts.draw();
		wr.startDrawingQuads();
		wr.addVertex(axisalignedbb.minX, axisalignedbb.minY,
			axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.minY,
			axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.minY,
			axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.minY,
			axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.minY,
			axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.minY,
			axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.minY,
			axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.minY,
			axisalignedbb.minZ);
		ts.draw();// Ends Y.
		wr.startDrawingQuads();// Starts Z.
		wr.addVertex(axisalignedbb.minX, axisalignedbb.minY,
			axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.maxY,
			axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.minY,
			axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.maxY,
			axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.minY,
			axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.maxY,
			axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.minY,
			axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.maxY,
			axisalignedbb.minZ);
		ts.draw();
		wr.startDrawingQuads();
		wr.addVertex(axisalignedbb.minX, axisalignedbb.maxY,
			axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.minY,
			axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.maxY,
			axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.minX, axisalignedbb.minY,
			axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.maxY,
			axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.minY,
			axisalignedbb.minZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.maxY,
			axisalignedbb.maxZ);
		wr.addVertex(axisalignedbb.maxX, axisalignedbb.minY,
			axisalignedbb.maxZ);
		ts.draw();// Ends Z.
	}
	
	public static void scissorBox(int x, int y, int xend, int yend)
	{
		int width = xend - x;
		int height = yend - y;
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft(),
			Minecraft.getMinecraft().displayWidth,
			Minecraft.getMinecraft().displayHeight);
		int factor = sr.getScaleFactor();
		int bottomY = Minecraft.getMinecraft().currentScreen.height - yend;
		glScissor(x * factor, bottomY * factor, width * factor,
			height * factor);
	}
	
	public static void setColor(Color c)
	{
		glColor4f(c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f,
			c.getAlpha() / 255f);
	}
	
	public static void drawSolidBox()
	{
		drawSolidBox(DEFAULT_AABB);
	}
	
	public static void drawSolidBox(AxisAlignedBB bb)
	{
		glBegin(GL_QUADS);
		{
			glVertex3d(bb.minX, bb.minY, bb.minZ);
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			
			glVertex3d(bb.minX, bb.minY, bb.minZ);
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.minY, bb.minZ);
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
		}
		glEnd();
	}
	
	public static void drawOutlinedBox()
	{
		drawOutlinedBox(DEFAULT_AABB);
	}
	
	public static void drawOutlinedBox(AxisAlignedBB bb)
	{
		glBegin(GL_LINES);
		{
			glVertex3d(bb.minX, bb.minY, bb.minZ);
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			glVertex3d(bb.minX, bb.minY, bb.minZ);
			
			glVertex3d(bb.minX, bb.minY, bb.minZ);
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
		}
		glEnd();
	}
	
	public static void drawCrossBox()
	{
		drawOutlinedBox(DEFAULT_AABB);
	}
	
	public static void drawCrossBox(AxisAlignedBB bb)
	{
		glBegin(GL_LINES);
		{
			glVertex3d(bb.minX, bb.minY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.minY, bb.minZ);
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.minX, bb.maxY, bb.minZ);
			glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.maxX, bb.maxY, bb.minZ);
			glVertex3d(bb.minX, bb.maxY, bb.maxZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.minZ);
			glVertex3d(bb.minX, bb.minY, bb.maxZ);
			
			glVertex3d(bb.maxX, bb.minY, bb.maxZ);
			glVertex3d(bb.minX, bb.minY, bb.minZ);
		}
		glEnd();
	}
}
