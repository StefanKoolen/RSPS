package server.model.players.packets;
 
import server.Server;
import server.model.players.Client;
import server.model.players.PacketType;
import server.util.Misc;
 
/**
 * @author JaydenD12/Jaydennn
 */
 
public class ItemOnPlayer implements PacketType {
    @Override
    public void processPacket(Client c, int packetType, int packetSize) {
        int playerId = c.inStream.readUnsignedWord();
        int itemId = c.playerItems[c.inStream.readSignedWordBigEndian()] - 1;
 
        switch (itemId) {
        case 962:
            handleCrackers(c, itemId, playerId);
            break;
        default:
            c.sendMessage("Nothing interesting happens.");
            break;
        }
 
    }
 
    private void handleCrackers(Client c, int itemId, int playerId) {
        Client usedOn = (Client) Server.playerHandler.players[playerId];
        if (!c.getItems().playerHasItem(itemId))
            return;
         
        if (usedOn.getItems().freeSlots() < 1) {
            c.sendMessage("The other player doesn't have enough inventory space!");
            return;
        }
         
        c.sendMessage("You crack the cracker...");
        c.getItems().deleteItem(itemId, 1);
         
        if (Misc.random(1) == 0) {
            c.getItems().addItem(getRandomPhat(), 1);
            c.sendMessage("You got the prize!");
            usedOn.sendMessage("You didn't get the prize.");
        } else {
            usedOn.getItems().addItem(getRandomPhat(), 1);
            usedOn.sendMessage("You got the prize!");
            c.sendMessage("You didn't get the prize.");
        }
    }
 
    private int getRandomPhat() {
        int[] phats = { 1038, 1040, 1042, 1044, 1048 };
        return phats[(int) Math.floor(Math.random() * phats.length)];
    }
}