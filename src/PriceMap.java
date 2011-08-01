import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Date;
import java.util.Set;
import java.lang.Double;

public class PriceMap implements Map<Date, Double> {
	private ArrayList<Date> timesList;
	private ArrayList<Double> priceList;
	public PriceMap(){
		timesList = new ArrayList<Date>();
		priceList = new ArrayList<Double>();
	}
	@Override
	public void clear() {
		timesList.clear();
		priceList.clear();
	}
	@Override
	public boolean containsKey(Object arg0) {
		return timesList.indexOf(arg0) != -1;
	}

	@Override
	public boolean containsValue(Object arg0) {
		return priceList.indexOf(arg0) != -1;
	}

	@Override
	public Set<java.util.Map.Entry<Date, Double>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double get(Object arg0) {
		int index = timesList.indexOf(arg0);
		return priceList.get(index);
	}

	@Override
	public boolean isEmpty() {
		return timesList.size()>0;
	}

	@Override
	public Set<Date> keySet() {
		return new HashSet<Date>(timesList);//I'm not even sure if this works...Don't use this method
	}

	@Override
	public Double put(Date arg0, Double arg1) {
		timesList.add(arg0);
		priceList.add(arg1);
		return arg1;
	}

	@Override
	public void putAll(Map<? extends Date, ? extends Double> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Double remove(Object arg0) {
		int index = timesList.indexOf(arg0);
		Double removedElement = priceList.get(index);
		timesList.remove(index);
		priceList.remove(index);
		return removedElement;//returns the price of the element that was removed
	}

	@Override
	public int size() {
		return timesList.size();
	}

	@Override
	public Collection<Double> values() {
		// retuns an arrayList of prices
		return priceList;
	}

}
