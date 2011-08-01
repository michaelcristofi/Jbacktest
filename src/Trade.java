import java.util.Date;
import java.lang.Double;

public class Trade {
	private Date openTime, closeTime;
	private Double openPrice, closePrice;
	private Double initialPositionSize;
	private boolean isLong;
	
	public Trade(Double price, Double PositionSize, boolean isBuy){
		openTime = new Date();
		openPrice = price;
		isLong = isBuy;
		initialPositionSize = PositionSize;
	}
	public void close(Double price){
		closeTime = new Date();
		closePrice = price;
	}
	public Double calcPercentProfit(){
		if(isLong){return ((closePrice-openPrice)/openPrice);}
		else {return (-(closePrice-openPrice)/openPrice);}
	}
	public Double calcProfit(){
		return (initialPositionSize * (calcPercentProfit() + 1));
	}
	public Double tradeDuration(){
		return new Double((double) (closeTime.getTime() - openTime.getTime()));//returns trade duration in milisends
	}
}
