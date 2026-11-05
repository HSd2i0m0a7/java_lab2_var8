import billing.VanLogistics;
import model.CorporateVan;
import model.LoaderService;
import model.UserVan;
import model.Van;
import service.NotificationService;
import service.impl.EmailNotification;
import service.impl.SmsNotification;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Инициализация сервисов системы грузового каршеринга ===\n");

        NotificationService smsService = new SmsNotification();
        NotificationService emailService = new EmailNotification();

        VanLogistics smsLogistics = new VanLogistics(smsService);

        Van userVan = new UserVan("ГАЗель Бизнес", 2500.0, "MEDIUM");
        smsLogistics.processOrder(userVan, "+7-999-123-45-67");
        System.out.println();

        VanLogistics emailLogistics = new VanLogistics(emailService);

        Van corpVan = new CorporateVan("ООО Грузовоз", 3000.0, "LARGE", 5);
        emailLogistics.processOrder(corpVan, "finance@gruzovoz.ru");

        System.out.println("\n Демонстрация опции (услуга грузчика) \n");
        LoaderService loader = new LoaderService("Грузчик + тележка", 500.0);
        System.out.println("Итог физлица с грузчиком (1 час): " + userVan.calculateFinalPrice(loader) + " руб.");
        System.out.println("Итог юрлица с грузчиком: " + corpVan.calculateFinalPrice(loader) + " руб.");

        System.out.println("\n Демонстрация проверок \n");
        smsLogistics.processOrder(userVan, "12345");
        System.out.println();
        emailLogistics.processOrder(corpVan, "finance(at)gruzovoz.ru");
        System.out.println();
        Van brokenVan = new UserVan("Фургон-призрак", -500.0, "XL");
        smsLogistics.processOrder(brokenVan, "+7-911-000-00-00");
        System.out.println();
        Van brokenCorp = new CorporateVan("ООО Пустота", 2000.0, "SMALL", 0);
        emailLogistics.processOrder(brokenCorp, "boss@pustota.ru");

        VanLogistics.printGlobalStats();
    }
}