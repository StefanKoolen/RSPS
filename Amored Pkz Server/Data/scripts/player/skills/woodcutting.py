# Script for Woodcutting Skill
# Author: Ryan/Lmctruck30
# 
#
# Stump: 1341(deadtree)

import random
from server.model.players import PlayerHandler
from server.util import Misc
from server.task import TaskManager
from server.task import TaskFactory

# ITEM ACTION LISTENERS
# Normal Trees
def objectAction1_1276(player, objX, objY):
	doAxeCheck(player, "TREE", 1276, objX, objY)
def objectAction1_1277(player, objX, objY):
	doAxeCheck(player, "TREE", 1277, objX, objY)
def objectAction1_1278(player, objX, objY):
	doAxeCheck(player, "TREE", 1278, objX, objY)
def objectAction1_1279(player, objX, objY):
	doAxeCheck(player, "TREE", 1279, objX, objY)
def objectAction1_1280(player, objX, objY):
	doAxeCheck(player, "TREE", 1280, objX, objY)
def objectAction1_1282(player, objX, objY):
	doAxeCheck(player, "TREE", 1282, objX, objY)
def objectAction1_1283(player, objX, objY):
	doAxeCheck(player, "TREE", 1283, objX, objY)
def objectAction1_1284(player, objX, objY):
	doAxeCheck(player, "TREE", 1284, objX, objY)
def objectAction1_1285(player, objX, objY):
	doAxeCheck(player, "TREE", 1285, objX, objY)
def objectAction1_1286(player, objX, objY):
	doAxeCheck(player, "TREE", 1286, objX, objY)
def objectAction1_1289(player, objX, objY):
	doAxeCheck(player, "TREE", 1289, objX, objY)
def objectAction1_1290(player, objX, objY):
	doAxeCheck(player, "TREE", 1290, objX, objY)
def objectAction1_1291(player, objX, objY):
	doAxeCheck(player, "TREE", 1291, objX, objY)
def objectAction1_1315(player, objX, objY):
	doAxeCheck(player, "TREE", 1315, objX, objY)
def objectAction1_1316(player, objX, objY):
	doAxeCheck(player, "TREE", 1316, objX, objY)
def objectAction1_1318(player, objX, objY):
	doAxeCheck(player, "TREE", 1318, objX, objY)
def objectAction1_1319(player, objX, objY):
	doAxeCheck(player, "TREE", 1319, objX, objY)
def objectAction1_1330(player, objX, objY):
	doAxeCheck(player, "TREE", 1330, objX, objY)
def objectAction1_1331(player, objX, objY):
	doAxeCheck(player, "TREE", 1331, objX, objY)
def objectAction1_1332(player, objX, objY):
	doAxeCheck(player, "TREE", 1332, objX, objY)
def objectAction1_1365(player, objX, objY):
	doAxeCheck(player, "TREE", 1365, objX, objY)
def objectAction1_1383(player, objX, objY):
	doAxeCheck(player, "TREE", 1383, objX, objY)
def objectAction1_1384(player, objX, objY):
	doAxeCheck(player, "TREE", 1384, objX, objY)
def objectAction1_2409(player, objX, objY):
	doAxeCheck(player, "TREE", 2409, objX, objY)
def objectAction1_3033(player, objX, objY):
	doAxeCheck(player, "TREE", 3033, objX, objY)
def objectAction1_3034(player, objX, objY):
	doAxeCheck(player, "TREE", 3034, objX, objY)
def objectAction1_3035(player, objX, objY):
	doAxeCheck(player, "TREE", 3035, objX, objY)
def objectAction1_3036(player, objX, objY):
	doAxeCheck(player, "TREE", 3036, objX, objY)
def objectAction1_3881(player, objX, objY):
	doAxeCheck(player, "TREE", 3881, objX, objY)
def objectAction1_3882(player, objX, objY):
	doAxeCheck(player, "TREE", 3882, objX, objY)
def objectAction1_3883(player, objX, objY):
	doAxeCheck(player, "TREE", 3883, objX, objY)
def objectAction1_5902(player, objX, objY):
	doAxeCheck(player, "TREE", 5902, objX, objY)
def objectAction1_5203(player, objX, objY):
	doAxeCheck(player, "TREE", 5203, objX, objY)
def objectAction1_5304(player, objX, objY):
	doAxeCheck(player, "TREE", 5304, objX, objY)

# Oak Trees
def objectAction1_1281(player, objX, objY):
	doAxeCheck(player, "OAK", 1281, objX, objY)
def objectAction1_1306(player, objX, objY):
	doAxeCheck(player, "OAK", 1306, objX, objY)

# Willow Trees
def objectAction1_1308(player, objX, objY):
	doAxeCheck(player, "WILLOW", 1308, objX, objY)
def objectAction1_5551(player, objX, objY):
	doAxeCheck(player, "WILLOW", 5551, objX, objY)
def objectAction1_5552(player, objX, objY):
	doAxeCheck(player, "WILLOW", 5552, objX, objY)
def objectAction1_5553(player, objX, objY):
	doAxeCheck(player, "WILLOW", 5553, objX, objY)	

# Yew Trees
def objectAction1_1309(player, objX, objY):
	doAxeCheck(player, "YEW", 1309, objX, objY)

# Magic Trees
def objectAction1_1292(player, objX, objY):
	doAxeCheck(player, "MAGE", 1292, objX, objY)
def objectAction1_1306(player, objX, objY):
	doAxeCheck(player, "WILLOW", 1306, objX, objY)

# AXE CHECK
def doAxeCheck(player, name, objID, objX, objY):
	level = player.playerLevel[8]
	if player.withinInteractionDistance(objX, objY):
		if player.freeSlots() > 0:
			if player.hasItem(6739) and level >= 61:
				doCut(player, name, objID, objX, objY)
			elif player.hasItem(1359) and level >= 41:
				doCut(player, name, objID, objX, objY)
			elif player.hasItem(1357) and level >= 31:
				doCut(player, name, objID, objX, objY)
			elif player.hasItem(1355) and level >= 21:
				doCut(player, name, objID, objX, objY)
			elif player.hasItem(1353) and level >= 6 or player.hasItem(1361) and level >= 6:
				doCut(player, name, objID, objX, objY)
			elif player.hasItem(1351) or player.hasItem(1349):
				doCut(player, name, objID, objX, objY)
			else:
				player.sendMessage("You do not have a axe of which you have the level to use.")
		elif player.freeSlots() == 0:
			player.resetAnimation()
			player.sendMessage("You don't have enough inventory space to do that.")

# SKILL EXECUTION
def doCut(player, name, objID, x, y):
	level = player.playerLevel[8]
	if name == "TREE":
		player.sendMessage("You swing your axe at the tree...")
		player.setAnimation(867)
		TaskManager.registerClientTask(TaskFactory.getDelayedTask("callback_woodcutting_TREE", player, objID, x, y), random.randrange(1, 4))
	elif name == "OAK":
		if level >= 15:
			player.sendMessage("You swing your axe at the tree...")
			player.setAnimation(867)
			TaskManager.registerClientTask(TaskFactory.getDelayedTask("callback_woodcutting_OAK", player, objID, x, y), random.randrange(3, 6))
		else:
			player.sendMessage("You need a woodcutting level of 15 to cut this tree.")
	elif name == "WILLOW":
		if level >= 30:
			player.sendMessage("You swing your axe at the tree...")
			player.setAnimation(867)
			TaskManager.registerClientTask(TaskFactory.getDelayedTask("callback_woodcutting_WILLOW", player, objID, x, y), random.randrange(3, 8))
		else:
			player.sendMessage("You need a woodcutting level of 30 to cut this tree.")
	elif name == "YEW":
		if level >= 60:
			player.sendMessage("You swing your axe at the tree...")
			player.setAnimation(867)
			TaskManager.registerClientTask(TaskFactory.getDelayedTask("callback_woodcutting_YEW", player, objID, x, y), random.randrange(3, 12))
		else:
			player.sendMessage("You need a woodcutting level of 60 to cut this tree.")
	
	
# SKILL CALLBACK FUNCTIONS
def callback_woodcutting_TREE(player, id, x, y):
	player.sendMessage("You get some logs.")
	player.addItem(1511, 1)
	player.addSkillXP(50, 8)
	if random.randrange(1 , 2) == 1:
		player.resetAnimation()
		PlayerManager.replaceObjectGlobal(x, y, 1341, 0, 10)
		TaskManager.registerClientTask(TaskFactory.getDelayedGlobalTask("callback_woodcutting_replace", id, x, y), 3)
	else:
		doAxeCheck(player, "TREE", id, x, y)

def callback_woodcutting_OAK(player, id, x, y):
	player.sendMessage("You get some oak logs.")
	player.addItem(1519, 1)
	player.addSkillXP(75, 8)
	if Misc.random(4) == 1:
		player.resetAnimation()
		PlayerManager.replaceObjectGlobal(x, y, 1341, 0, 10)
		TaskManager.registerClientTask(TaskFactory.getDelayedGlobalTask("callback_woodcutting_replace", id, x, y), 5)
	else:
		doAxeCheck(player, "OAK", id, x, y)

def callback_woodcutting_WILLOW(player, id, x, y):
	player.sendMessage("You get some willow logs.")
	player.addItem(1521, 1)
	player.addSkillXP(135, 8)
	if Misc.random(6) == 1:
		player.resetAnimation()
		PlayerManager.replaceObjectGlobal(x, y, 1341, 0, 10)
		TaskManager.registerClientTask(TaskFactory.getDelayedGlobalTask("callback_woodcutting_replace", id, x, y), 8)
	else:
		doAxeCheck(player, "WILLOW", id, x, y)

def callback_woodcutting_YEW(player, id, x, y):
	player.sendMessage("You get some willow logs.")
	player.addItem(1515, 1)
	player.addSkillXP(350, 8)
	if Misc.random(6) == 1:
		player.resetAnimation()
		PlayerManager.replaceObjectGlobal(x, y, 1341, 0, 10)
		TaskManager.registerClientTask(TaskFactory.getDelayedGlobalTask("callback_woodcutting_replace", id, x, y), 8)
	else:
		doAxeCheck(player, "YEW", id, x, y)

# OBJECT RESPAWN
def callback_woodcutting_replace(id, x, y):
	PlayerManager.replaceObjectGlobal(x, y, id, 0, 10)