public class VanLogistics {
    private static int totalOrdersCount = 0;
    private static double totalRevenue = 0;
    public static void processOrder(Van van, double finalPrice) {
        totalRevenue += finalPrice;
        totalOrdersCount++;
        System.out.printf("Успешный заказ! Фургон: %s | Размер: %s | К оплате: %.2f руб.%n",
                van.getModelName(), van.getSizeModel(), finalPrice);
        if (van.getAppliedLoader() != null) {
            System.out.printf("  -> Применена услуга грузчика: %s (%.2f руб./час)%n",
                    van.getAppliedLoader().getServiceName(), van.getAppliedLoader().getPricePerHour());
        }
    }
    public static int getTotalOrdersCount() {return totalOrdersCount;}
    public static double getTotalRevenue() {return totalRevenue;}
    public static void printGlobalStats() {
        System.out.println("\n==================================================");
        System.out.println("   ГЛОБАЛЬНАЯ СТАТИСТИКА ГРУЗОВОГО КАРШЕРИНГА   ");
        System.out.println("==================================================");
        System.out.println("Всего оформлено заказов: " + totalOrdersCount);
        System.out.println("Общая выручка системы: " + Math.round(totalRevenue * 100.0) / 100.0 + " руб.");
        System.out.println("==================================================");
    }
    public static void main(String[] args) {
        System.out.println("--- Запуск системы грузового каршеринга ---\n");
        LoaderService loader = new LoaderService("Грузчик + тележка", 500.0);
        Van van1 = new Van("ГАЗель", 2500.0);
        double price1 = van1.calculateFinalPrice();
        processOrder(van1, price1);
        IO.println();
        Van van2 = new Van("Камаз", 3200.0, "MEDIUM");
        double price2 = van2.calculateFinalPrice(loader);
        processOrder(van2, price2);
        System.out.println();
        Van van3 = new Van("Белаз", 4500.0, "LARGE");
        double price3 = van3.calculateFinalPrice(loader, 4);
        processOrder(van3, price3);
        printGlobalStats();
    }
}
