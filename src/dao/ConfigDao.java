package dao;

import entity.Config;
/**
 * �������ݿ⣬�������ã��������ã��ӿ�
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
		config = new Config("����ʽKTV", 10, 1, 1);
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}
}
