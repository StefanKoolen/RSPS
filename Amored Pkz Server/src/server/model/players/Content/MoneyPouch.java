package server.model.content;

import server.Config;
import server.engine.util.Misc;
import server.model.entity.player.Client;

/**
 * Represents a players' money pouch.
 * 
 * @author Jokerboy2000
 */
public class MoneyPouch {

	/**
	 * The integer that stores the amount of money a player has in their pouch.
	 */
	private int pouch;

	/**
	 * Checks the total amount of money a player is storing in their pouch.
	 * 
	 * @return
	 */
	public int getTotal() {
		return pouch;
	}

	/**
	 * Set the total amount of money a player has in their pouch.
	 * 
	 * @param amount
	 */
	public void setTotal(int amount) {
		pouch = amount;
	}

	public void refresh(Client c) {
		if (c.getMoneyPouch().getTotal() > 99999
				&& c.getMoneyPouch().getTotal() <= 999999) {
			c.getPA().sendFrame126(
					"" + c.getMoneyPouch().getTotal() / 1000 + "K", 8134);
		} else if (c.getMoneyPouch().getTotal() > 999999
				&& c.getMoneyPouch().getTotal() <= 2147483647) {
			c.getPA().sendFrame126(
					"" + c.getMoneyPouch().getTotal() / 1000000 + "M", 8134);
		} else {
			c.getPA()
					.sendFrame126("" + c.getMoneyPouch().getTotal() + "", 8134);
		}
		c.getPA().sendFrame126("" + c.getMoneyPouch().getTotal() + "", 8135);
	}

	/**
	 * Adding money to a players pouch.
	 * 
	 * @param amount
	 */
	public void addMoney(int amount, Client c, boolean delete) {
		boolean max = false;
		if (getTotal() == Config.MAXITEM_AMOUNT) {
			c.sendMessage("Your money pouch is full.");
			return;
		}
		if (c.getItems().getItemCount(995) > Config.MAXITEM_AMOUNT - getTotal()) {
			amount = Config.MAXITEM_AMOUNT - getTotal();
			max = true;
		}
		String amt = Integer.toString(amount);
		if (amount > 1) {
			c.sendMessage(Misc.insertCommas(amt)
					+ " coins have been added to your money pouch.");
		} else {
			c.sendMessage("One coin has been added to your money pouch.");
		}
		pouch += amount;
		if (delete) {
			c.getItems().deleteItem(995, amount);
		}
		if (max) {
			c.getItems().addItem(995, getTotal() - amount);
			max = false;
		}
		refresh(c);
	}

	/**
	 * Remove money from a players pouch.
	 * 
	 * @param amount
	 */
	public void removeMoney(int amount, Client c) {
		if (c.getItems().getItemCount(995) == Config.MAXITEM_AMOUNT) {
			return;
		}
		if (amount <= 0 || getTotal() == 0) {
			return;
		}
		if (getTotal() < amount) {
			amount = getTotal();
		}
		if (c.getItems().getItemCount(995) > Config.MAXITEM_AMOUNT - amount) {
			amount = Config.MAXITEM_AMOUNT - c.getItems().getItemCount(995);
		}
		if (amount > -1) {
			if (amount > getTotal()) {
				amount = getTotal();
			}
			String amt = Integer.toString(amount);
			if (amount > 1) {
				c.sendMessage(Misc.insertCommas(amt)
						+ " coins have been removed from your money pouch.");
			} else {
				c.sendMessage("One coin has been removed from your money pouch.");
			}
			pouch -= amount;
			c.getItems().addItem(995, amount);
			refresh(c);
		}
	}

	/**
	 * Buying an item from a shop.
	 * 
	 * @param amount
	 * @param c
	 */
	public void buyItem(int cost, Client c, boolean loop, int msgAmount) {
		if (cost <= 0 || getTotal() == 0) {
			return;
		}
		if (getTotal() - cost < 0) {
			return;
		}
		if (getTotal() < cost) {
			return;
		}
		pouch -= cost;
		String amt = Integer.toString(cost * msgAmount);
		if (loop) {
			return;
		}
		if (cost > 1) {
			c.sendMessage(Misc.insertCommas(amt)
					+ " coins have been deleted from your money pouch.");
		} else {
			c.sendMessage("One coin has been deleted from your money pouch.");
		}
		refresh(c);
	}

	/**
	 * Selling an item to a shop.
	 * 
	 * @param amount
	 * @param c
	 */
	public void sellItem(int refund, Client c, boolean loop, int msgAmount) {
		if (getTotal() == Config.MAXITEM_AMOUNT) {
			c.getItems().addItem(995, refund);
			refresh(c);
			return;
		}
		if (getTotal() + refund > Config.MAXITEM_AMOUNT) {
			while (getTotal() != Config.MAXITEM_AMOUNT) {
				pouch += 1;
				refund -= 1;
			}
			c.getItems().addItem(995, refund);
			refresh(c);
			String amt = Integer.toString(refund * msgAmount);
			if (loop) {
				return;
			}
			if (refund > 1) {
				c.sendMessage(Misc.insertCommas(amt)
						+ " coins have been added to your money pouch.");
			} else {
				c.sendMessage("One coin has been added to your money pouch.");
			}
			return;
		}
		pouch += refund;
		String amt = Integer.toString(refund * msgAmount);
		if (loop) {
			return;
		}
		if (refund > 1) {
			c.sendMessage(Misc.insertCommas(amt)
					+ " coins have been added to your money pouch.");
		} else {
			c.sendMessage("One coin has been added to your money pouch.");
		}
		refresh(c);
	}
}