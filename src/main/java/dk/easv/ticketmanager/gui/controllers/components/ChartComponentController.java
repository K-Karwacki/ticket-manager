package dk.easv.ticketmanager.gui.controllers.components;

import dk.easv.ticketmanager.bll.services.interfaces.TicketManagementService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class ChartComponentController implements Initializable {
    private TicketManagementService ticketManagementService;

    @FXML
    private VBox chartComponent;

    @FXML
    private BarChart<String, Number> barChart;

    private static List<LocalDateTime> datesOfPurchases;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        barChart.setAnimated(false);
        System.out.println("Initialize - Controller instance: " + this);
        if(datesOfPurchases != null){
            updateChart("day");
        }
    }

    @FXML
    private void showDayView() {
        updateChart("day");
    }

    @FXML
    private void showWeekView() {
        updateChart("week");
    }

    @FXML
    private void showMonthView() {
        updateChart("month");
    }

    @FXML
    private void showYearView() {
        updateChart("year");
    }

    public void setServices(TicketManagementService ticketManagementService) {
        this.ticketManagementService = ticketManagementService;
        System.out.println("setServices - Controller instance: " + this);
        if (ticketManagementService != null) {
            datesOfPurchases = ticketManagementService.getAllDatesForPurchasedTickets();
            System.out.println("Dates loaded in setServices: " + datesOfPurchases.size());
            updateChart("month");
        }
    }

    private void updateChart(String period) {
        barChart.getData().clear();
        System.out.println("updateChart - Dates size: " + datesOfPurchases.size());

        Map<String, Integer> counts = new TreeMap<>();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter;

        switch (period) {
            case "day":
                formatter = DateTimeFormatter.ofPattern("HH:00");
                for (int i = 0; i < 24; i += 2) {
                    String intervalStart = String.format("%02d:00", i);
                    counts.put(intervalStart, 0);
                }
                for (LocalDateTime date : datesOfPurchases) {
                    if (date.toLocalDate().equals(now.toLocalDate())) {
                        int hour = date.getHour();
                        int intervalHour = (hour / 2) * 2;
                        String intervalStr = String.format("%02d:00", intervalHour);
                        counts.put(intervalStr, counts.getOrDefault(intervalStr, 0) + 1);
                    }
                }
                break;

            case "week":
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                for (int i = 6; i >= 0; i--) {
                    String dateStr = now.minusDays(i).format(formatter);
                    counts.put(dateStr, 0);
                }
                for (LocalDateTime date : datesOfPurchases) {
                    LocalDateTime startOfWeek = now.minusDays(7).withHour(0).withMinute(0).withSecond(0);
                    if (date.isAfter(startOfWeek) && date.isBefore(now.plusDays(1))) {
                        String dateStr = date.format(formatter);
                        counts.put(dateStr, counts.getOrDefault(dateStr, 0) + 1);
                    }
                }
                break;

            case "month":
                formatter = DateTimeFormatter.ofPattern("yyyy-'W'ww");
                LocalDateTime startOfMonth = now.minusDays(30).withHour(0).withMinute(0).withSecond(0);
                for (int i = 0; i < 5; i++) {
                    String weekStr = startOfMonth.plusWeeks(i).format(formatter);
                    counts.put("Week " + (i + 1), 0);
                }
                for (LocalDateTime date : datesOfPurchases) {
                    if (date.isAfter(startOfMonth) && date.isBefore(now.plusDays(1))) {
                        int weekOffset = (int) java.time.temporal.ChronoUnit.WEEKS.between(startOfMonth, date);
                        if (weekOffset >= 0 && weekOffset < 5) {
                            String weekLabel = "Week " + (weekOffset + 1);
                            counts.put(weekLabel, counts.getOrDefault(weekLabel, 0) + 1);
                        }
                    }
                }
                break;

            case "year":
                formatter = DateTimeFormatter.ofPattern("MMM");
                LocalDateTime startOfYear = now.withMonth(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
                for (int i = 0; i < 12; i++) {
                    String monthStr = startOfYear.plusMonths(i).format(formatter);
                    counts.put(monthStr, 0);
                }
                for (LocalDateTime date : datesOfPurchases) {
                    if (date.getYear() == now.getYear()) {
                        String monthStr = date.format(formatter);
                        counts.put(monthStr, counts.getOrDefault(monthStr, 0) + 1);
                    }
                }
                break;

            default:
                return;
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Tickets Sold (" + period + ")");

        int maxValue = 0;
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            maxValue = Math.max(maxValue, entry.getValue());
        }

        barChart.getData().add(series);
        barChart.setTitle("Ticket Sales - Current " + period);

        // Customize Y-axis
        NumberAxis yAxis = (NumberAxis) barChart.getYAxis();
        yAxis.setAutoRanging(false); // Disable auto-ranging
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(maxValue + 1); // Add 1 to ensure top bar is fully visible
        yAxis.setTickUnit(1); // Set tick unit to 1 for integer values
        yAxis.setMinorTickVisible(false); // Hide minor ticks
    }
}