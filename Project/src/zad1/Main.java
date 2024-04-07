/**
 *
 *  @author Mróz Michał S27673
 *
 */

package zad1;


import javax.swing.*;

public class Main {
  public static void main(String[] args) {
    Service s = new Service("Poland");
    String weatherJson = s.getWeather("London");
    Double rate1 = s.getRateFor("USD");
    Double rate2 = s.getNBPRate();

    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new ServiceGUI();
      }
    });
  }
}
