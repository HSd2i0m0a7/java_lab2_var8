package model;

public class CorporateVan extends Van {
    private final int activeLicensesCount;

    public CorporateVan(String modelName, double basePrice, String sizeClass, int activeLicensesCount) {
        super(modelName, basePrice, sizeClass);
        if (activeLicensesCount < 1) {
            System.out.println("[Система] Число лицензий не может быть меньше 1. Принудительно установлена 1 лицензия.");
            this.activeLicensesCount = 1;
        } else {
            this.activeLicensesCount = activeLicensesCount;
        }
    }

    @Override
    public double calculateFinalPrice() {
        final double sizeMultiplier = switch (getSizeModel()) {
            case "SMALL" -> MULTIPLIER_SMALL;
            case "MEDIUM" -> MULTIPLIER_MEDIUM;
            case "LARGE" -> MULTIPLIER_LARGE;
            default -> {
                System.out.println("[Администратор] Неизвестный класс фургона: " + getSizeModel()
                        + ". Применен базовый коэффициент 1.0.");
                yield MULTIPLIER_SMALL;
            }
        };

        double finalPrice = (getBasePrice() * sizeMultiplier) * activeLicensesCount;
        return Math.round(finalPrice * 100.0) / 100.0;
    }

    @Override
    public double calculateFinalPrice(LoaderService loader) {
        System.out.println("[Система] Корпоративным клиентам услуга грузчика не применяется: "
                + "она включена в корпоративный тариф. Возвращена стандартная цена.");
        return calculateFinalPrice();
    }

    @Override
    public double calculateFinalPrice(LoaderService loader, int hours) {
        return calculateFinalPrice(loader);
    }

    public int getActiveLicensesCount() { return activeLicensesCount; }
}