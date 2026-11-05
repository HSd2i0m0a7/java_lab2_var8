package service.impl;
import service.NotificationService;
public class EmailNotification implements NotificationService {
    @Override
    public void sendNotification(String message, String recipient) {
        if (recipient != null && recipient.contains("@") && recipient.contains(".")) {
            System.out.printf("[EMAIL] На адрес <%s> отправлено письмо: %s%n", recipient, message);
        } else {
            System.out.printf("[EMAIL] Ошибка: адрес <%s> некорректен (нужны '@' и '.'). Письмо не отправлено.%n", recipient);
        }
    }
}
