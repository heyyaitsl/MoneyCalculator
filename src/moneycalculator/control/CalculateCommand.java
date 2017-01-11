package moneycalculator.control;

import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;
import moneycalculator.model.Money;
import moneycalculator.persistence.ExchangeRateLoader;
import moneycalculator.ui.MoneyDialog;
import moneycalculator.ui.MoneyDisplay;

public class CalculateCommand implements Command {
    private final ExchangeRateLoader loader;
    private final MoneyDisplay moneyDisplay;
    private final MoneyDialog moneyDialog;
    private Currency eur = new Currency("EUR", "Euro", "€");

    public CalculateCommand(ExchangeRateLoader loader, MoneyDisplay moneyDisplay, MoneyDialog moneyDialog) {
        this.loader = loader;
        this.moneyDisplay = moneyDisplay;
        this.moneyDialog = moneyDialog;
    }

    @Override
    public String name() {
        return "calculate";
    }

    @Override
    public void execute() {
        Money money = moneyDialog.get();
        moneyDisplay.display(exchange(money));
    }

    private Money exchange(Money money) {
        ExchangeRate exchangeRate = loader.load(money.getCurrency(), eur);
        return new Money(money.getAmount()*exchangeRate.getRate(), eur);
    }
    
}
