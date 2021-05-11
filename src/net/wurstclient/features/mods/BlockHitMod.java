/*
 * Copyright © 2014 - 2017 | Wurst-Imperium | All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package net.wurstclient.features.mods;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.wurstclient.events.LeftClickEvent;
import net.wurstclient.events.listeners.LeftClickListener;
import net.wurstclient.events.listeners.UpdateListener;
import net.wurstclient.utils.InventoryUtils;

@Mod.Info(
	description = "Automatically blocks whenever you hit something with a sword.\n"
		+ "Some say that you will receive less damage in PVP when doing this.",
	name = "BlockHit",
	tags = "AutoBlock, BlockHitting, auto block, block hitting",
	help = "Mods/BlockHit")
@Mod.Bypasses
public final class BlockHitMod extends Mod
	implements LeftClickListener, UpdateListener
{
	private int timer;
	
	@Override
	public void onEnable()
	{
		// reset timer
		timer = 0;
		
		wurst.events.add(LeftClickListener.class, this);
	}
	
	@Override
	public void onDisable()
	{
		wurst.events.remove(LeftClickListener.class, this);
		wurst.events.remove(UpdateListener.class, this);
	}
	
	@Override
	public void onLeftClick(LeftClickEvent event)
	{
		// check held item
		ItemStack stack = mc.player.getCurrentEquippedItem();
		if(InventoryUtils.isEmptySlot(stack)
			|| !(stack.getItem() instanceof ItemSword))
			return;
		
		doBlock();
	}
	
	@Override
	public void onUpdate()
	{
		switch(timer)
		{
			case 0:
				mc.gameSettings.keyBindUseItem.pressed = false;
				break;
			
			case 1:
			case 2:
				mc.gameSettings.keyBindUseItem.pressed = true;
				break;
			
			case 3:
				mc.gameSettings.keyBindUseItem.pressed = false;
				wurst.events.remove(UpdateListener.class, this);
				
				// reset timer
				timer = -1;
				break;
		}
		
		// update timer
		timer++;
	}
	
	public void doBlock()
	{
		// check if ready
		if(!isActive() || timer > 0)
			return;
		
		wurst.events.add(UpdateListener.class, this);
	}
}
