/*
 * Copyright © 2014 - 2017 | Wurst-Imperium | All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package net.wurstclient.features.commands;

import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.CPacketPlayer.Position;
import net.wurstclient.utils.MiscUtils;

@Cmd.Info(description = "Applies the given amount of damage.",
	name = "damage",
	syntax = {"<amount>"})
public final class DamageCmd extends Cmd
{
	@Override
	public void execute(String[] args) throws CmdError
	{
		if(args.length == 0)
			syntaxError();
		
		// check amount
		if(!MiscUtils.isInteger(args[0]))
			syntaxError("Amount must be a number.");
		int dmg = Integer.parseInt(args[0]);
		if(dmg < 1)
			error("Amount must be at least 1.");
		if(dmg > 40)
			error("Amount must be at most 20.");
		
		// check gamemode
		if(mc.player.capabilities.isCreativeMode)
			error("Cannot damage in creative mode.");
		
		double posX = mc.player.posX;
		double posY = mc.player.posY;
		double posZ = mc.player.posZ;
		NetHandlerPlayClient sendQueue = mc.player.connection;
		
		// apply damage
		for(int i = 0; i < 80 + 20 * (dmg - 1D); ++i)
		{
			sendQueue
				.sendPacket(new Position(posX, posY + 0.049D, posZ, false));
			sendQueue.sendPacket(new Position(posX, posY, posZ, false));
		}
		sendQueue.sendPacket(new Position(posX, posY, posZ, true));
	}
}
