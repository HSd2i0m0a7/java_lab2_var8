package model;

public class UserVan extends Van {

    public UserVan(String modelName, double basePrice) {
        super(modelName, basePrice, "SMALL");
    }

    public UserVan(String modelName, double basePrice, String sizeClass) {
        super(modelName, basePrice, sizeClass);
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

        double finalPrice = getBasePrice() * sizeMultiplier;
        return Math.round(finalPrice * 100.0) / 100.0;
    }
}