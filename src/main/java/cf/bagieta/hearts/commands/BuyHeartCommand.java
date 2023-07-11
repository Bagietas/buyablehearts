package cf.bagieta.hearts.commands;

import cf.bagieta.hearts.Hearts;
import com.earth2me.essentials.api.Economy;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.math.BigDecimal;

public class BuyHeartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        FileConfiguration config = Hearts.INSTANCE.getConfig();

        if(commandSender instanceof Player player){
            try{
                if(Economy.getMoneyExact(player.getUniqueId()).intValue() >= config.getInt("heart-price")){
                    Economy.subtract(player.getUniqueId(), BigDecimal.valueOf(100));

                    AttributeInstance atr = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                    atr.setBaseValue(atr.getBaseValue()+2);

                    commandSender.sendMessage("§aYou've bought an heart.");
                }else{
                    commandSender.sendMessage("§cYou don't have enough money to buy heart.");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else{
            commandSender.sendMessage("§4Only players can perform this command!");
        }
        return false;
    }
}
