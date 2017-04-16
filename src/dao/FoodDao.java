package dao;

import java.util.ArrayList;
import java.util.List;

import model.Food;

/**
 * 操作数据库，管理食物，增删改查，接口
 */

public interface FoodDao {
	

	public FoodDao instance();

	

	public List<Food> getFoodList();

	public List<String> getTypeList();

	public Food getFoodbyId(int id);

	public void addFood(String name,String type,int price,int numRest) ;

	public void removeFood(int id);

	public Object[][] getFoodsData();

}
