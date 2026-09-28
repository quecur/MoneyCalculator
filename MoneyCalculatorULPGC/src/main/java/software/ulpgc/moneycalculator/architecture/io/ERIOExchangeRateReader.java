package software.ulpgc.moneycalculator.architecture.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ERIOExchangeRateReader implements ExchangeRateReader{
    @Override
    public String read(LocalDate date) throws IOException {
        URLConnection connection = buildUrl(date).openConnection();
        connection.setRequestProperty("apikey", ERIOApi.key);

        try (InputStream inputStream = connection.getInputStream()) {
            return new String(inputStream.readAllBytes());
        }
    }


    private URL buildUrl(LocalDate date) throws IOException {
        return new URL("https://api.apilayer.com/exchangerates_data/"
                + date.format(getFormatDate()));
    }

    private DateTimeFormatter getFormatDate() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }
}
