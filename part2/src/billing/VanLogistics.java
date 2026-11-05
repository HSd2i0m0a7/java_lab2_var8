package billing;

import model.Van;
import service.NotificationService;

public class VanLogistics {
    private static int totalOrdersCount = 0;
    private static double totalRevenue = 0;

    // Ссылка на ИНТЕРФЕЙС
    private final NotificationService notificationService;

    public VanLogistics(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    public void processOrder(Van van, String contact) {
        // Полиморфный вызов
        double finalPrice = van.calculateFinalPrice();

        totalRevenue += finalPrice;
        totalOrdersCount++;

        String message = String.format("Счет оплачен. Фургон [%s], класс [%s]. Списано: %.2f руб.",
                van.getModelName(), van.getSizeModel(), finalPrice);

        notificationService.sendNotification(message, contact);
    }

    public static int getTotalOrdersCount() { return totalOrdersCount; }
    public static double getTotalRevenue() { return totalRevenue; }

    public static void printGlobalStats() {
        System.out.println("\n--- ГЛОБАЛЬНАЯ СТАТИСТИКА ГРУЗОВОГО КАРШЕРИНГА ---");
        System.out.println("Всего обработано заказов: " + totalOrdersCount);
        System.out.println("Общая выручка компании: " + Math.round(totalRevenue * 100.0) / 100.0 + " руб.");
        System.out.println("-------------------------------------");
    }
}