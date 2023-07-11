package cf.bagieta.hearts.listeners;

import cf.bagieta.hearts.Hearts;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class EventPlayerDeath implements Listener {
    @EventHandler
    public void PlayerDeathEvent(PlayerDeathEvent event){
        PersistentDataContainer dataCon = event.getEntity().getPersistentDataContainer();
        int player_deaths = dataCon.getOrDefault(new NamespacedKey(Hearts.INSTANCE, "player_deaths"), PersistentDataType.INTEGER,0) + 1;
        dataCon.set(new NamespacedKey(Hearts.INSTANCE, "player_deaths"), PersistentDataType.INTEGER, player_deaths);

        if(player_deaths >= 3){
            AttributeInstance atr = event.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH);
            atr.setBaseValue(atr.getBaseValue()-2);

            dataCon.set(new NamespacedKey(Hearts.INSTANCE, "player_deaths"), PersistentDataType.INTEGER, 0);
        }
    }
}
