package ex1;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import net.CurrencyRate;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class CalcController {

    @FXML
    public TextField display;
    @FXML
    public ComboBox<CurrencyRate> sourceCurrencyCombo;
    @FXML
    public ComboBox<CurrencyRate> targetCurrencyCombo;
    @FXML
    public TextField result;

    List<CurrencyRate> currencies;

    public void initialize() throws IOException {
            URL url = new URL("http://api.nbp.pl/api/exchangerates/tables/A?format=json");
            JsonReader reader = Json.createReader(url.openStream());
            currencies = new ArrayList<>();
            JsonArray arr = reader.readArray().getJsonObject(0).getJsonArray("rates");
            arr.forEach(obj -> {
                JsonObject rate = obj.asJsonObject();
                String currency = rate.getString("currency");
                String code = rate.getString("code");
                BigDecimal mid = rate.getJsonNumber("mid").bigDecimalValue();
                CurrencyRate currencyRate = new CurrencyRate(currency, code, mid);
                currencies.add(currencyRate);
            });
            currencies.add(new CurrencyRate("polski złoty", "PLN", new BigDecimal("1.00000")));
            sourceCurrencyCombo.setItems(FXCollections.observableArrayList(currencies));
            targetCurrencyCombo.setItems(FXCollections.observableArrayList(currencies));
            sourceCurrencyCombo.setValue(currencies.get(0));
            targetCurrencyCombo.setValue(currencies.get(0));

    }

    public BigDecimal calcResult(BigDecimal amount, CurrencyRate source, CurrencyRate target){
        BigDecimal sourceRate = source.getMid();
        BigDecimal targetRate = target.getMid();
        return sourceRate.divide(targetRate, RoundingMode.HALF_EVEN).multiply(amount);
    }


    public void inputNumber(ActionEvent actionEvent) {
        String input = display.getText();
        Button btn = (Button) actionEvent.getSource();
        String numberStr = btn.getText();
        if (input.isEmpty() && numberStr.equals(",")){
            input = "0";
        }
        //czy jest już przecinek
        if (input.contains(",") && numberStr.equals(",")){
            return;
        }
        //czy długość łaćucha jest maksymalna
        if (input.length() == 14){
            return;
        }

        //liczba w display nie może być większą od 10_000
        if (!input.isEmpty()) {
            BigDecimal value = new BigDecimal((input+numberStr).replace(",","."));
            BigDecimal floor = new BigDecimal("10000");
            if (value.compareTo(floor) > 0 ){
                return;
            }
        }

        //operacja dla poprawnych danych
        display.setText(input+numberStr);
        updateResult();
    }

    private void updateResult() {
        if (sourceCurrencyCombo.getValue() == null || targetCurrencyCombo == null || display.getText().isEmpty()){
            return;
        }
        BigDecimal resultAmount = calcResult(new BigDecimal(display.getText().replace(",",".")), sourceCurrencyCombo.getValue(), targetCurrencyCombo.getValue());
        String str = String.format("%.2f %s", resultAmount.doubleValue(), targetCurrencyCombo.getValue().getCode());
        result.setText(str);
    }

    public void backspace(ActionEvent actionEvent) {
        String input = display.getText();
        if (!input.isEmpty()) {
            display.setText(input.substring(0, input.length() - 1));
            updateResult();
        }
    }

    public void calcFromSource(ActionEvent actionEvent) {
        updateResult();
    }

    public void calcFromTarget(ActionEvent actionEvent) {
        updateResult();
    }
}
