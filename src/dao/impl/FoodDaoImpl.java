package dao.impl;

import java.util.ArrayList;
import java.util.List;

import model.Food;
import model.User;

import org.hibernate.Session;

import dao.FoodDao;
/**
 * 食物增删改查的实现
 */
public class FoodDaoImpl extends BaseDao implements FoodDao{
	private List<Food> foodList;
	private List<String> typeList;
	private static FoodDaoImpl foodDao;

	public FoodDaoImpl instance() {
		if (foodDao == null)
			foodDao = new FoodDaoImpl();
		return foodDao;
	}

	public FoodDaoImpl() {
		foodList = new ArrayList<Food>();
		typeList = new ArrayList<String>();
		
	}
	public void setFood(int id,String name,String type,int price,int num){
		Food food = new Food();
		food.setId(id);
		food.setName(name);
		food.setType(type);
		food.setPrice(price);
		food.setNumRest(num);
		Session session = getSession();
	    session.update(food);
		session.flush();
	}
	
	public List<Food> getFoods() {
		return getSession().createQuery("from Food").list();
	}
	public List<Food> getFoodList() {
		foodList = getFoods();
		return foodList;
	}

	public List<String> getTypeList() {
		return getSession().createQuery("select distinct type from Food").list();
	}

//	public Food findUserById(int id) {
//		return (Food)getSession().load(Food.class, id);
//	}


	public void addFood(String name,String type,int price,int numRest) {
		Food food = new Food();

		food.setName(name);
		food.setType(type);
		food.setPrice(price);
		food.setNumRest(numRest);
//		System.out.println(food);
		getSession().save(food);
//		getSession().createQuery("from Food");
//		foodList.add(food);
//		for (String type : typeList)
//			if (food.getType().equals(type))
//				return;
//		typeList.add(food.getType());
	}

//	public void removeFood(Food food) {
//		foodList.remove(food);
//	}

	public Object[][] getFoodsData() {
		foodList = getFoodList();
//		System.out.println(foodList);
		Object[][] result = new Object[1][5];
		if (foodList.size() > 0) {
			result = new Object[foodList.size()][5];
			int i = 0;
			for (Food food : foodList) {
				result[i][0] = food.getId();
				result[i][1] = food.getName();
				result[i][2] = food.getType();
				result[i][3] = food.getPrice();
				result[i][4] = food.getNumRest();
				i++;
			}
		} else {
			result[0][0] = 0;
			result[0][1] = "没有数据";
			result[0][2] = "";
			result[0][3] = 0;
			result[0][4] = 0;
		}
		return result;
	}




	public void removeFood(int id) {
		// TODO Auto-generated method stub
//		getSession().delete(food);
		Session session = getSession();
	    Food food = (Food)session.load(Food.class, id);
		session.delete(food);
		session.flush();
	}



	@Override
	public Food getFoodbyId(int id) {
		return (Food)getSession().load(Food.class, id);
	}

}
