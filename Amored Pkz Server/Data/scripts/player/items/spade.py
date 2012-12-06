# Spade Functions
# Author: Lmctruck30
#

from server.util import ScriptManager

# Dig
def itemClick_952(player, itemId, itemSlot):
	player.startAnimation(830)
	if(player.inArea(3553, 3301, 3561, 3294)):
		player.teleTimer = 3;
		player.newLocation = 1;
	elif(player.inArea(3550, 3287, 3557, 3278)):
		player.teleTimer = 3;
		player.newLocation = 2;
	elif(player.inArea(3561, 3292, 3568, 3285)):
		player.teleTimer = 3;
		player.newLocation = 3;
	elif(player.inArea(3570, 3302, 3579, 3293)):
		player.teleTimer = 3;
		player.newLocation = 4;
	elif(player.inArea(3571, 3285, 3582, 3278)):
		player.teleTimer = 3;
		player.newLocation = 5;
	elif(player.inArea(3562, 3279, 3569, 3273)):
		player.teleTimer = 3;
		player.newLocation = 6;
	else:
		player.sendMessage("You find nothing...");


