package dao;

import java.util.ArrayList;
import java.util.List;

import model.Food;

/**
 * �������ݿ⣬����ʳ���ɾ�Ĳ飬�ӿ�
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
