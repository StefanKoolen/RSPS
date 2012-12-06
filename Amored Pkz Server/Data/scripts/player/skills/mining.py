# Script for the Mining skill
# Author: blakeman8192

import random
from server.model.players import PlayerHandler
from server.task import TaskManager
from server.task import TaskFactory

# ITEM ACTION LISTENERS	
def objectAction1_2093(player, objX, objY):
	doCheck(player, "IRON", 2093, objX, objY)
def objectAction1_2092(player, objX, objY):
	doCheck(player, "IRON", 2092, objX, objY)
def objectAction1_2094(player, objX, objY):
	doCheck(player, "TIN", 2094, objX, objY)
def objectAction1_2095(player, objX, objY):
	doCheck(player, "TIN", 2095, objX, objY)
def objectAction1_2090(player, objX, objY):
	doCheck(player, "COPPER", 2090, objX, objY)
def objectAction1_2091(player, objX, objY):
	doCheck(player, "COPPER", 2091, objX, objY)
def objectAction1_2100(player, objX, objY):
	doCheck(player, "SILVER", 2100, objX, objY)
def objectAction1_2101(player, objX, objY):
	doCheck(player, "SILVER", 2101, objX, objY)
def objectAction1_2096(player, objX, objY):
	doCheck(player, "COAL", 2096, objX, objY)
def objectAction1_2097(player, objX, objY):
	doCheck(player, "COAL", 2097, objX, objY)
def objectAction1_2098(player, objX, objY):
	doCheck(player, "GOLD", 2098, objX, objY)
def objectAction1_2099(player, objX, objY):
	doCheck(player, "GOLD", 2099, objX, objY)	
def objectAction1_2102(player, objX, objY):
	doCheck(player, "MITHRIL", 2102, objX, objY)
def objectAction1_2103(player, objX, objY):
	doCheck(player, "MITHRIL", 2103, objX, objY)
def objectAction1_2104(player, objX, objY):
	doCheck(player, "ADAMANT", 2104, objX, objY)
def objectAction1_2105(player, objX, objY):
	doCheck(player, "ADAMANT", 2105, objX, objY)
def objectAction1_2106(player, objX, objY):
	doCheck(player, "RUNITE", 2106, objX, objY)
def objectAction1_2107(player, objX, objY):
	doCheck(player, "RUNITE", 2107, objX, objY)
	
# PICKAXE CHECK
def doCheck(player, name, objID, objX, objY):
	level = player.playerLevel[14]
	if player.withinInteractionDistance(objX, objY):
		if player.freeSlots() > 0:
			if player.hasItem(1275) and level >= 41:
				doMine(player, name, objID, objX, objY)
			elif player.hasItem(1271) and level >= 31:
				doMine(player, name, objID, objX, objY)
			elif player.hasItem(1273) and level >= 21:
				doMine(player, name, objID, objX, objY)
			elif player.hasItem(1269) and levle >= 5:
				doMine(player, name, objID, objX, objY)
			elif player.hasItem(1265) or player.hasItem(1267):
				doMine(player, name, objID, objX, objY)
			else:
				player.sendMessage("You do not have a pickaxe of which you have the level to use.")
		elif player.freeSlots() == 0:
			player.sendMessage("You don't have enough inventory space to do that.")
		
# SKILL EXECUTION
def doMine(player, name, objID, x, y):
	level = player.playerLevel[14]
	if name == "COPPER":
		player.sendMessage("You swing your pickaxe at the rock...")
		player.setAnimation(0x554)
		TaskManager.registerClientTask(TaskFactory.getDelayedTask("callback_mining_COPPER", player, objID, x, y), random.randrange(1, 4))
	elif name == "TIN":
		player.sendMessage("Your swing your pickaxe at the rock...")
		player.setAnimation(0x554)
		TaskManager.registerClientTask(TaskFactory.getDelayedTask("callback_mining_TIN", player, objID, x, y), random.randrange(1, 4))
	elif name == "IRON":
		if level >= 15:
			player.sendMessage("You swing your pickaxe at the rock...")
			player.setAnimation(0x554)
			TaskManager.registerClientTask(TaskFactory.getDelayedTask("callback_mining_IRON", player, objID, x, y), random.randrange(3, 6))
		else:
			player.sendMessage("You need a mining level of 15 to mine this rock.")
	elif name == "SILVER":
		if level >= 20:
			player.sendMessage("You swing your pickaxe at the rock...")
			player.setAnimation(0x554)
			TaskManager.registerClientTask(TaskFactory.getDelayedTask("callback_mining_SILVER", player, objID, x, y), random.randrange(4, 6))
		else:
			player.sendMessage("You need a mining level of 20 to mine this rock.")
	elif name == "COAL":
		if level >= 30:
			player.sendMessage("You swing your pickaxe at the rock...")
			player.setAnimation(0x554)
			TaskManager.registerClientTask(TaskFactory.getDelayedTask("callback_mining_COAL", player, objID, x, y), random.randrange(4, 9))
		else:
			player.sendMessage("You need a mining level of 30 to mine this rock.")
	elif name == "GOLD":
		if level >= 40:
			player.sendMessage("You swing your pickaxe at the rock...")
			player.setAnimation(0x554)
			TaskManager.registerClientTask(TaskFactory.getDelayedTask("callback_mining_GOLD", player, objID, x, y), random.randrange(5, 9))
		else:
			player.sendMessage("You need a mining level of 40 to mine this rock.")
	elif name == "MITHRIL":
		if level >= 55:
			player.sendMessage("You swing your pickaxe at the rock...")
			player.setAnimation(0x554)
			TaskManager.registerClientTask(TaskFactory.getDelayedTask("callback_mining_MITHRIL", player, objID, x, y), random.randrange(5, 13))
		else:
			player.sendMessage("You need a mining level of 55 to mine this rock...")
	elif name == "ADAMANT":
		if level >= 70:
			player.sendMessage("You swing your pickaxe at the rock...")
			player.setAnimation(0x554)
			TaskManager.registerClientTask(TaskFactory.getDelayedTask("callback_mining_ADAMANT", player, objID, x, y), random.randrange(8, 18))
		else:
			player.sendMessage("You need a mining level of 70 to mine this rock.")
	elif name == "RUNITE":
		if level >= 85:
			player.sendMessage("You swing your pickaxe at the rock...")
			player.setAnimation(0x554)
			TaskManager.registerClientTask(TaskFactory.getDelayedTask("callback_mining_RUNITE", player, objID, x, y), random.randrange(16, 32))
		else:
			player.sendMessage("You need a mining level of 85 to mine this rock.")

# SKILL CALLBACK FUNCTIONS
def callback_mining_COPPER(player, id, x, y):
	player.sendMessage("You get some copper ore.")
	player.addItem(436, 1)
	player.addSkillXP(17, 14);
	player.resetAnimation()
	PlayerManager.replaceObjectGlobal(x, y, 450, 0, 10)
	TaskManager.registerClientTask(TaskFactory.getDelayedGlobalTask("callback_mining_replace", id, x, y), 3)

def callback_mining_TIN(player, id, x, y):
	player.sendMessage("You get some tin ore.")
	player.addItem(438, 1)
	player.addSkillXP(17, 14)
	player.resetAnimation()
	PlayerManager.replaceObjectGlobal(x, y, 450, 0, 10)
	TaskManager.registerClientTask(TaskFactory.getDelayedGlobalTask("callback_mining_replace", id, x, y), 3)
	
def callback_mining_IRON(player, id, x, y):
	player.sendMessage("You get some iron ore.")
	player.addItem(440, 1)
	player.addSkillXP(35, 14)
	player.resetAnimation()
	PlayerManager.replaceObjectGlobal(x, y, 450, 0, 10)
	TaskManager.registerClientTask(TaskFactory.getDelayedGlobalTask("callback_mining_replace", id, x, y), 7)
	
def callback_mining_SILVER(player, id, x, y):
	player.sendMessage("You get some silver ore.")
	player.addItem(440, 1)
	player.addSkillXP(40, 14)
	player.resetAnimation()
	PlayerManager.replaceObjectGlobal(x, y, 450, 0, 10)
	TaskManager.registerClientTask(TaskFactory.getDelayedGlobalTask("callback_mining_replace", id, x, y), 84)
	
def callback_mining_COAL(player, id, x, y):
	player.sendMessage("You get some coal.")
	player.addItem(453, 1)
	player.addSkillXP(50, 14)
	player.resetAnimation()
	PlayerManager.replaceObjectGlobal(x, y, 450, 0, 10)
	TaskManager.registerClientTask(TaskFactory.getDelayedGlobalTask("callback_mining_replace", id, x, y), 42)

def callback_mining_GOLD(player, id, x, y):
	player.sendMessage("You get some gold ore.")
	player.addItem(444, 1)
	player.addSkillXP(65, 14)
	player.resetAnimation()
	PlayerManager.replaceObjectGlobal(x, y, 450, 0, 10)
	TaskManager.registerClientTask(TaskFactory.getDelayedGlobalTask("callback_mining_replace", id, x, y), 84)
	
def callback_mining_MITHRIL(player, id, x, y):
	player.sendMessage("You get some mithril ore.")
	player.addItem(447, 1)
	player.addSkillXP(80, 14)
	player.resetAnimation()
	PlayerManager.replaceObjectGlobal(x, y, 450, 0, 10)
	TaskManager.registerClientTask(TaskFactory.getDelayedGlobalTask("callback_mining_replace", id, x, y), 168)
	
def callback_mining_ADAMANT(player, id, x, y):
	player.sendMessage("You get some adamantite ore.")
	player.addItem(449, 1)
	player.addSkillXP(95, 14)
	player.resetAnimation()
	PlayerManager.replaceObjectGlobal(x, y, 450, 0, 10)
	TaskManager.registerClientTask(TaskFactory.getDelayedGlobalTask("callback_mining_replace", id, x, y), 336)
	
def callback_mining_RUNITE(player, id, x, y):
	player.sendMessage("You get some runite ore.")
	player.addItem(451, 1)
	player.addSkillXP(125, 14)
	player.resetAnimation()
	PlayerManager.replaceObjectGlobal(x, y, 450, 0, 10)
	TaskManager.registerClientTask(TaskFactory.getDelayedGlobalTask("callback_mining_replace", id, x, y), 1050)
	
# OBJECT RESPAWN
def callback_mining_replace(id, x, y):
	PlayerManager.replaceObjectGlobal(x, y, id, 0, 10)

