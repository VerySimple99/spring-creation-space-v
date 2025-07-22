package model;

import java.util.ArrayList;

public class CarDAO {
	private static CarDAO instance=new CarDAO();
	private CarDAO() {}
	public static CarDAO getInstance() {
		return instance;
	}

	public ArrayList<CarVO> getCarList(String maker) {
		ArrayList<CarVO> carList=new ArrayList<CarVO>();
		if(maker.equals("현대")) {			
			carList.add(new CarVO("소나타","현대",200));
			carList.add(new CarVO("아반테","현대",100));
			carList.add(new CarVO("모닝","현대",50));
		}else if(maker.equals("기아")) {
			carList.add(new CarVO("K5","기아",200));
			carList.add(new CarVO("K7","기아",600));
		}else if(maker.equals("르노")) {
			carList.add(new CarVO("SM6","르노",200));
		}
		return carList;
	}
}

































