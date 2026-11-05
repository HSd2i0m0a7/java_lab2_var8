public class Van {
    private static final double MULTIPLIER_SMALL = 1.00;
    private static final double MULTIPLIER_MEDIUM = 1.35;
    private static final double MULTIPLIER_LARGE = 1.80;
    private final String modelName;
    private final double basePrice;
    private final String sizeModel;
    private LoaderService appliedLoader = null;

    public Van(String modelName, double basePrice) {
        this(modelName, basePrice, "SMALL");
    }

    public Van(String modelName, double basePrice, String sizeModel) {
        this.modelName = modelName;
        this.basePrice = basePrice;
        this.sizeModel = sizeModel.toUpperCase();
    }


    public double calculateFinalPrice() {
        final double sizeMultiplier = switch (sizeModel) {
            case "SMALL" -> MULTIPLIER_SMALL;
            case "MEDIUM" -> MULTIPLIER_MEDIUM;
            case "LARGE" -> MULTIPLIER_LARGE;
            default -> {
                IO.println("[Система] Неизвестный тариф. Применен базовый коэффициент.");
                yield MULTIPLIER_SMALL;
            }
        };
        double finalPrice = basePrice * sizeMultiplier;
        return Math.round(finalPrice * 100.0) / 100.0;
    }

    public double calculateFinalPrice(LoaderService loader) {
        this.appliedLoader = loader;
        double priceBeforeLoader = calculateFinalPrice();
        double finalPrice = priceBeforeLoader + loader.getPricePerHour();
        return Math.round(finalPrice * 100.0) / 100.0;
    }

    public double calculateFinalPrice(LoaderService loader, int hours) {
        double priceBeforeLoader = calculateFinalPrice();

        if (hours > 0) {
            // Связываем услугу с фургоном и считаем стоимость по часам
            this.appliedLoader = loader;
            double loaderCost = loader.getPricePerHour() * hours;
            double finalPrice = priceBeforeLoader + loaderCost;
            return Math.round(finalPrice * 100.0) / 100.0;
        }
        System.out.println("[Система] Некорректное количество часов. Услуга грузчика не применена.");
        return priceBeforeLoader;
    }

    public String getModelName() { return modelName; }
    public double getBasePrice() { return basePrice; }
    public String getSizeModel() { return sizeModel; }
    public LoaderService getAppliedLoader() { return appliedLoader; }
}