package dk.easv.ticketmanager.gui.controllers.components;

import dk.easv.ticketmanager.bll.services.interfaces.TicketAnalysisService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ChartComponentController implements Initializable {
    private TicketAnalysisService ticketAnalysisService;

    @FXML
    private VBox chartComponent;

    @FXML
    private BarChart<String, Number> barChart;

    private static List<LocalDateTime> datesOfPurchases;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalDateTime start = LocalDateTime.of(2023, 1, 1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(2025, 4, 10, 23, 59, 59);
        generateRandomDates(1000, start, end); // To comment later - inserting mock data
        barChart.setAnimated(false);
        barChart.setVerticalGridLinesVisible(false); // Hide vertical grid lines
        barChart.getStyleClass().add("custom-bar-chart"); // For CSS styling
        if (datesOfPurchases != null) {
            updateChart("day");
        }
    }

    @FXML
    private void showDayView(ActionEvent event) {
        Button btn = (Button) event.getSource();
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

    public void setServices(TicketAnalysisService ticketAnalysisService) {
        this.ticketAnalysisService = ticketAnalysisService;
        if (ticketAnalysisService != null) {
            datesOfPurchases = ticketAnalysisService.getAllDatesForPurchasedTickets();
            updateChart("month");
        }
    }

    private void updateChart(String period) {
        barChart.getData().clear();
        Map<String, Integer> counts = new LinkedHashMap<>(); // Use LinkedHashMap to preserve insertion order
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
                // Explicitly add months in chronological order
                String[] monthOrder = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
                for (String month : monthOrder) {
                    counts.put(month, 0);
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
    public static List<LocalDateTime> generateRandomDates(int size, LocalDateTime startRange, LocalDateTime endRange) {
        if (size < 0) {
            throw new IllegalArgumentException("Size must be non-negative");
        }
        if (startRange.isAfter(endRange)) {
            throw new IllegalArgumentException("Start range must be before end range");
        }

        datesOfPurchases = new ArrayList<>(size);
        long startNanos = startRange.toEpochSecond(java.time.ZoneOffset.UTC) * 1_000_000_000L + startRange.getNano();
        long endNanos = endRange.toEpochSecond(java.time.ZoneOffset.UTC) * 1_000_000_000L + endRange.getNano();

        for (int i = 0; i < size; i++) {
            // Generate a random nanosecond value between start and end
            long randomNanos = ThreadLocalRandom.current().nextLong(startNanos, endNanos);
            datesOfPurchases.add(LocalDateTime.ofEpochSecond(
                    randomNanos / 1_000_000_000L,
                    (int) (randomNanos % 1_000_000_000L),
                    java.time.ZoneOffset.UTC
            ));
        }

        return datesOfPurchases;
    }
}