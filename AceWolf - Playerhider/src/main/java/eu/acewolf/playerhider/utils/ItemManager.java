package eu.acewolf.playerhider.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {
    public static ItemStack createItemEntchants(Material material, int anzahl, int subid, String displayname)
    {
        short newsubid = (short)subid;
        ItemStack i = new ItemStack(material, anzahl, newsubid);
        ItemMeta m = i.getItemMeta();
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        m.setDisplayName(displayname);
        m.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        i.setItemMeta(m);
        return i;
    }
    public static ItemStack createItem(Material material, int anzahl, int subid, String displayname)
    {
        short newsubid = (short)subid;
        ItemStack i = new ItemStack(material, anzahl, newsubid);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(displayname);
        i.setItemMeta(m);
        return i;
    }
}
