package com.oe;

import net.fabricmc.api.ModInitializer;
import com.oe.register.ModEnchantments;
import com.oe.event.EnchantmentEvent;// ✅ 按你文件名来
import net.minecraft.util.Identifier; // ✅ 和 ModEnchantments 保持一致
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStream;
import java.util.Properties;

public class OEmod implements ModInitializer {
	public static final String MOD_ID = "oe";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final boolean TEST;
  
    static {
        boolean mode = false; // 默认值
        try (InputStream input = OEmod.class.getResourceAsStream("/test_mode.properties")) {
            if (input != null) {
                Properties props = new Properties();
                props.load(input);
                String value = props.getProperty("test_mode");
                LOGGER.info(value);
                if ((value.trim()).equals("\"true\"")) {
                    mode = true;
                } else {
                    LOGGER.info("mode is not true");
                }
            } else {
                // 资源不存在时也算失败，记录日志
                LOGGER.info("config.properties not found, using default test_mode=false");
            }
        } catch (Exception e) {
            // 读取失败时用 LOGGER.info 记录（按你的要求）
            LOGGER.info("Failed to load config.properties, using default test_mode=false", e);
        }
        TEST = mode;
    }

    
  
	@Override
	public void onInitialize() {
		ModEnchantments.register();       // ✅ 注册附魔
		EnchantmentEvent.register();
        if (TEST) {
            LOGGER.info("OE start successfully(test mode)");
        } else {
		    LOGGER.info("OE start successfully");
        }
    }

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}

    public static String commonid() {
       return MOD_ID;
    }

    public static boolean test() {
        return TEST;
    }
  
}