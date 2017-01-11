package moneycalculator;

import moneycalculator.control.CalculateCommand;
import moneycalculator.model.Currency;
import moneycalculator.persistence.rest.RestExchangeRateLoader;

public class Main {

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame(currencies());
        mainFrame.add(
            new CalculateCommand(
                new RestExchangeRateLoader(), 
                mainFrame.getMoneyDisplay(), 
                mainFrame.getMoneyDialog()
            ));
    }
    
    private static Currency[] currencies() {
        return new Currency[] {
            new Currency("USD", "Dólar USA", "$"),
            new Currency("GBP", "Libra esterlina", "£"),
            new Currency("CAD", "Dólar canadiense", "$")
        };
    }
    

}
