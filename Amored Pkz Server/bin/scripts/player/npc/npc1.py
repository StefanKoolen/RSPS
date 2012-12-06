from server.model.players import Client
from server.util import ScriptManager

def npcClick1_943(c, npcId):
	if(c.tutorial == 0):
		c.getDH().sendDialogues(4, npcId)
		c.tutorial = 1
	elif(c.tutorial == 1):
		c.getDH().sendDialogues(4, npcId)

def npcClick1_944(c, npcId):
	if(c.tutorial == 2):
		c.getDH().sendDialogues(7, npcId)
	elif(c.tutorial < 2):
		c.getDH().sendDialogues(3, -1)

def npcClick1_949(c, npcId):
	if(c.tutorial > 1):
		c.getDH().sendDialogues(9, npcId)