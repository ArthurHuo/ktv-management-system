package dao;

import entity.Config;
/**
 * 操作数据库，加钟配置，各种配置，接口
 */
public class ConfigDao {
	private Config config;
	private static ConfigDao configDao;

	public static ConfigDao instance() {
		if (configDao == null)
			configDao = new ConfigDao();
		return configDao;
	}

	public ConfigDao() {
		config = new Config("量贩式KTV", 10, 1, 1);
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}
}
