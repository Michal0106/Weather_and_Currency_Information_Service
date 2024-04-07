package zad1;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ServiceGUI extends JFrame {
    private Service service;
    private JTextField countryTextField;
    private JTextField townTextField;
    private JTextField currencyTextField;
    private JTextArea resultTextArea;
    public JFXPanel jfxPanel;
    public JPanel webPanel;

    public ServiceGUI() {
        super("Service GUI");

        webPanel = new JPanel();
        service = new Service("Poland");
        jfxPanel = new JFXPanel();


        JLabel countryLabel = new JLabel("Country:");
        countryTextField = new JTextField(20);
        countryTextField.setText("United States");

        JLabel townLabel = new JLabel("Town:");
        townTextField = new JTextField(20);

        JLabel currencyLabel = new JLabel("Currency:");
        currencyTextField = new JTextField(20);

        JButton weatherButton = new JButton("Get Weather");
        weatherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String town = townTextField.getText();
                String weatherInfo = service.getWeather(town);
                resultTextArea.setText(weatherInfo);
            }
        });

        JButton rateButton = new JButton("Get Exchange Rate");
        rateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String currency = currencyTextField.getText();
                Double exchangeRate = service.getRateFor(currency);
                resultTextArea.setText("Exchange Rate for " + currency + ": " + exchangeRate);
            }
        });

        JButton nbpRateButton = new JButton("Get NBP Exchange Rate");
        nbpRateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String country = countryTextField.getText();
                service.setCountryCode(country);
                Double nbpRate = service.getNBPRate();
                resultTextArea.setText("Exchange Rate from NBP: " + nbpRate);
            }
        });

        JButton webButton = new JButton("Show me website");
        webButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String town = townTextField.getText();
                Platform.runLater(() -> showWebsite(town));
            }
        });

        resultTextArea = new JTextArea(10, 30);
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(countryLabel);
        panel.add(countryTextField);
        panel.add(townLabel);
        panel.add(townTextField);
        panel.add(currencyLabel);
        panel.add(currencyTextField);
        panel.add(weatherButton);
        panel.add(rateButton);
        panel.add(nbpRateButton);
        panel.add(webButton);

        webPanel.add(jfxPanel);
        setLayout(new BorderLayout());

        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(webPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void showWebsite(String town) {
        String url = "https://en.wikipedia.org/wiki/";
        WebView webView = new WebView();
        webView.getEngine().load(url + town);
        Scene scene = new Scene(webView);
        jfxPanel.setScene(scene);
    }
}

