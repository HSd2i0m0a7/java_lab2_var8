package service.impl;

import service.NotificationService;

public class SmsNotification implements NotificationService {
    @Override
    public void sendNotification(String message, String recipient) {
        if (recipient != null
                && (recipient.startsWith("+7") || recipient.startsWith("8"))
                && recipient.length() >= 11) {
            System.out.printf("[SMS] На номер [%s] отправлено сообщение: %s%n", recipient, message);
        } else {
            System.out.printf("[SMS] Ошибка: номер [%s] некорректен (ожидается +7/8, не менее 11 символов). Сообщение не отправлено.%n", recipient);
        }
    }
}