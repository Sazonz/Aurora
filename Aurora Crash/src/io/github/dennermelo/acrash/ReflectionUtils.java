package io.github.dennermelo.acrash;


import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * @author Mior
 * @version 1.0
 * @category utils
 */

public class ReflectionUtils {
	
   	public static Class<?> getNMSClass(String name) {
   		String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
   		try {
   			return Class.forName("net.minecraft.server." + version + "." + name);
   		} catch (ClassNotFoundException | ArrayIndexOutOfBoundsException e) {
   			return null;
   		}
   	}
   	
   	public static Class<?> getOBClass(String name) {
   		String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
   		try {
   			return Class.forName("org.bukkit.craftbukkit." + version + "." + name);
   		} catch (ClassNotFoundException | ArrayIndexOutOfBoundsException e) {
   			return null;
   		}
   	}
   	
   	public static void sendPacket(Player player, Object packet) {
   		try {
   			Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
   			Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
   			playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
   		} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException e) {
   			e.printStackTrace();
   		}
   	}
}