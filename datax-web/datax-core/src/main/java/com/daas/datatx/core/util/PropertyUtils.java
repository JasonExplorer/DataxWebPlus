package com.daas.datatx.core.util;

import com.daas.datatx.core.enums.ResUploadType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * property utils
 * single instance
 */
public class PropertyUtils {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(PropertyUtils.class);

    private static final Properties properties = new Properties();

    private PropertyUtils() {
        throw new IllegalStateException("PropertyUtils class");
    }

    /*static {
        String[] propertyFiles = new String[]{COMMON_PROPERTIES_PATH};
        for (String fileName : propertyFiles) {
            InputStream fis = null;
            try {
                fis = PropertyUtils.class.getResourceAsStream(fileName);
                properties.load(fis);

            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                if (fis != null) {
                    IOUtils.closeQuietly(fis);
                }
                System.exit(1);
            } finally {
                IOUtils.closeQuietly(fis);
            }
        }
    }*/

    /**
     *
     * @return  judge whether resource upload startup
     */
    public static Boolean getResUploadStartupState(){
        String resUploadStartupType = PropertyUtils.getString(Constants.RESOURCE_STORAGE_TYPE);
        ResUploadType resUploadType = ResUploadType.valueOf(resUploadStartupType);
        return resUploadType == ResUploadType.HDFS || resUploadType == ResUploadType.S3;
    }

    /**
     * get property value
     *
     * @param key property name
     * @return property value
     */
    public static String getString(String key) {
        return properties.getProperty(key.trim());
    }

    /**
     * get property value
     *
     * @param key property name
     * @param defaultVal default value
     * @return property value
     */
    public static String getString(String key, String defaultVal) {
        String val = properties.getProperty(key.trim());
        return val == null ? defaultVal : val;
    }

    /**
     * get property value
     *
     * @param key property name
     * @return  get property int value , if key == null, then return -1
     */
    public static int getInt(String key) {
        return getInt(key, -1);
    }

    /**
     *
     * @param key key
     * @param defaultValue default value
     * @return property value
     */
    public static int getInt(String key, int defaultValue) {
        String value = getString(key);
        if (value == null) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            logger.info(e.getMessage(),e);
        }
        return defaultValue;
    }

    /**
     * get property value
     *
     * @param key property name
     * @return property value
     */
    public static boolean getBoolean(String key) {
        String value = properties.getProperty(key.trim());
        if(null != value){
            return Boolean.parseBoolean(value);
        }

        return false;
    }

    /**
     * get property value
     *
     * @param key property name
     * @param defaultValue default value
     * @return property value
     */
    public static Boolean getBoolean(String key, boolean defaultValue) {
        String value = properties.getProperty(key.trim());
        if(null != value){
            return Boolean.parseBoolean(value);
        }

        return defaultValue;
    }

    /**
     * get property long value
     * @param key key
     * @param defaultVal default value
     * @return property value
     */
    public static long getLong(String key, long defaultVal) {
        String val = getString(key);
        return val == null ? defaultVal : Long.parseLong(val);
    }

    /**
     *
     * @param key key
     * @return property value
     */
    public static long getLong(String key) {
        return getLong(key,-1);
    }

    /**
     *
     * @param key key
     * @param defaultVal default value
     * @return property value
     */
    public double getDouble(String key, double defaultVal) {
        String val = getString(key);
        return val == null ? defaultVal : Double.parseDouble(val);
    }


    /**
     *  get array
     * @param key       property name
     * @param splitStr  separator
     * @return property value through array
     */
    public static String[] getArray(String key, String splitStr) {
        String value = getString(key);
        if (value == null) {
            return new String[0];
        }
        try {
            String[] propertyArray = value.split(splitStr);
            return propertyArray;
        } catch (NumberFormatException e) {
            logger.info(e.getMessage(),e);
        }
        return new String[0];
    }

    /**
     *
     * @param key key
     * @param type type
     * @param defaultValue default value
     * @param <T> T
     * @return  get enum value
     */
    public <T extends Enum<T>> T getEnum(String key, Class<T> type,
                                         T defaultValue) {
        String val = getString(key);
        return val == null ? defaultValue : Enum.valueOf(type, val);
    }

    /**
     * get all properties with specified prefix, like: fs.
     * @param prefix prefix to search
     * @return all properties with specified prefix
     */
    public static Map<String, String> getPrefixedProperties(String prefix) {
        Map<String, String> matchedProperties = new HashMap<>();
        for (String propName : properties.stringPropertyNames()) {
            if (propName.startsWith(prefix)) {
                matchedProperties.put(propName, properties.getProperty(propName));
            }
        }
        return matchedProperties;
    }
}
