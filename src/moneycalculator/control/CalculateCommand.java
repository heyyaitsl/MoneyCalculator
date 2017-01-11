package moneycalculator.control;

import moneycalculator.model.Currency;
import moneycalculator.model.Money;
import moneycalculator.persistence.ExchangeRateLoader;
import moneycalculator.ui.MoneyDialog;
import moneycalculator.ui.MoneyDisplay;

public class CalculateCommand implements Command {
    private final ExchangeRateLoader loader;
    private final MoneyDisplay moneyDisplay;
    private final MoneyDialog moneyDialog;
    private Currency eur = new Currency("EUR", "Euro", "€");

    public CalculateCommand(MoneyDisplay moneyDisplay, MoneyDialog moneyDialog, ExchangeRateLoader loader) {
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
        return new Money(money.getAmount() * rateOf(money.getCurrency()),eur);
    }

    private double rateOf(Currency currency) {
        return loader.load(currency, eur).getRate();
    }
    
}
