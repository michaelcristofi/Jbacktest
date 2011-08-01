import java.util.ArrayList;

/**
 * @author David Byrd
 * @time 10:32:04 PM
 * @description This is the first version of a backtesting algorithm. It is an abstract class that will be overridden to customize 3 things:
 * 1. getData Method - how to pull the data from a CSV and create a PriceMap
 * 2. positionSize Method - asset allocation function (can think in terms of shares/amounts & fixed/dynamic)
 * 3. implement a ton of abstract methods
 */
public abstract class BackTest {
	//Trading Data - works behind the scenes;
	private boolean positionIsOpen;
	private Trade currentTrade;
	
	//BackTesting Data - defined in the constructor
	private Double portfolioSize;
	private Double investmentPerTradeWhenFixed = new Double(10000);//default value
	
	public BackTest(Double initialInvestment){
		portfolioSize = initialInvestment;//this is where you adjust the portfolio size
	}
	public BackTest(Double initialInvestment, Double fixedTradeAllocationAmount){
		portfolioSize = initialInvestment;
		investmentPerTradeWhenFixed = fixedTradeAllocationAmount; 
	}
	public void evaluate() {
		double profit = 0;
		for(Trade t : generateTrades(getData())){
			profit += t.calcProfit();
		}
		display(profit);
	}

	private ArrayList<Trade> generateTrades(PriceMap data){
		ArrayList<Trade> tradeHistory = new ArrayList<Trade>();
		positionIsOpen = false;
		ArrayList<Double> prices = (ArrayList<Double>) data.values();
		for (Double price : prices){
			//if position is open
			if(shouldCloseBuy(price)&&(positionIsOpen)){
				Trade tempTrade = currentTrade;
				tradeHistory.add(tempTrade);
				currentTrade.close(price);
			}
			if(shoudCloseSell(price) && (positionIsOpen)){
				Trade tempTrade = currentTrade;
				tradeHistory.add(tempTrade);
				currentTrade.close(price);
			}
			
			//if position is not open
			if(isBuy(price) && !(positionIsOpen)){
				positionIsOpen = true;
				currentTrade = new Trade(price,positionSize(price), true);
			}
			if(isSell(price) && !(positionIsOpen)){
				positionIsOpen = true;
				currentTrade = new Trade(price,positionSize(price), false);
			}
		} return tradeHistory;
	}
	private Double positionSize(Double price){//could be calculated as number of shares or as fixed number or as dynamic number
		return new Double(investmentPerTradeWhenFixed);//this is where you adjust the positionSize
		
	}
	protected abstract PriceMap getData();
	protected abstract boolean shoudCloseSell(Double price);
	protected abstract boolean shouldCloseBuy(Double price);
	protected abstract boolean isSell(Double price);
	protected abstract boolean isBuy(Double price);
	private void display(double profit) {
		System.out.println("Initial Investment: " + portfolioSize);
		System.out.println("Final Portfolio Size" + (portfolioSize + profit));
		if(profit > 0){System.out.println("Total Profit: " + profit);}
		else {System.out.println("Total Loss: " + profit);}
		System.out.println("% Return: " + (profit/portfolioSize));
		
	}

}
