/*
 * Copyright © 2014 - 2017 | Wurst-Imperium | All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package net.wurstclient.utils;

import java.util.Collections;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.wurstclient.features.mods.XRayMod;

public class XRayUtils
{
	public static void initXRayBlocks()
	{
		// ores
		add(Blocks.COAL_ORE);
		add(Blocks.coal_block);
		add(Blocks.IRON_ORE);
		add(Blocks.iron_block);
		add(Blocks.GOLD_ORE);
		add(Blocks.gold_block);
		add(Blocks.LAPIS_ORE);
		add(Blocks.lapis_block);
		add(Blocks.REDSTONE_ORE);
		add(Blocks.lit_redstone_ore);
		add(Blocks.redstone_block);
		add(Blocks.DIAMOND_ORE);
		add(Blocks.diamond_block);
		add(Blocks.EMERALD_ORE);
		add(Blocks.emerald_block);
		add(Blocks.QUARTZ_ORE);
		
		// sort-of ores
		add(Blocks.CLAY);
		add(Blocks.glowstone);
		
		// utilities
		add(Blocks.crafting_table);
		add(Blocks.furnace);
		add(Blocks.lit_furnace);
		add(Blocks.torch);
		add(Blocks.ladder);
		add(Blocks.tnt);
		add(Blocks.enchanting_table);
		add(Blocks.bookshelf);
		add(Blocks.anvil);
		add(Blocks.brewing_stand);
		add(Blocks.beacon);
		
		// storage
		add(Blocks.CHEST);
		add(Blocks.trapped_chest);
		add(Blocks.ender_chest);
		add(Blocks.hopper);
		add(Blocks.dropper);
		add(Blocks.dispenser);
		
		// liquids
		add(Blocks.WATER);
		add(Blocks.FLOWING_WATER);
		add(Blocks.LAVA);
		add(Blocks.FLOWING_LAVA);
		
		// spawners
		add(Blocks.MOSSY_COBBLESTONE);
		add(Blocks.MOB_SPAWNER);
		
		// portals
		add(Blocks.portal);
		add(Blocks.end_portal);
		add(Blocks.end_portal_frame);
		
		// command blocks
		add(Blocks.COMMAND_BLOCK);
	}
	
	private static void add(Block block)
	{
		XRayMod.xrayBlocks.add(block);
	}
	
	public static boolean isXRayBlock(Block blockToCheck)
	{
		return XRayMod.xrayBlocks.contains(blockToCheck);
	}
	
	public static void sortBlocks()
	{
		Collections.sort(XRayMod.xrayBlocks,
			(o1, o2) -> Block.getIdFromBlock(o1) - Block.getIdFromBlock(o2));
	}
}
