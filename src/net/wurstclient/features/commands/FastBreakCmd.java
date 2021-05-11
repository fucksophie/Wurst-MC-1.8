/*
 * Copyright © 2014 - 2017 | Wurst-Imperium | All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package net.wurstclient.features.commands;

import net.wurstclient.files.ConfigFiles;
import net.wurstclient.utils.ChatUtils;

@Cmd.Info(description = "Changes the settings of FastBreak.",
	name = "fastbreak",
	syntax = {"mode (normal|instant)"})
public final class FastBreakCmd extends Cmd
{
	@Override
	public void execute(String[] args) throws CmdError
	{
		if(args.length != 2)
			syntaxError();
		if(args[0].toLowerCase().equals("mode"))
		{// 0=normal, 1=instant
			if(args[1].toLowerCase().equals("normal"))
				wurst.options.fastbreakMode = 0;
			else if(args[1].toLowerCase().equals("instant"))
				wurst.options.fastbreakMode = 1;
			else
				syntaxError();
			ConfigFiles.OPTIONS.save();
			ChatUtils.message("FastBreak mode set to \"" + args[1] + "\".");
		}else
			syntaxError();
	}
}
