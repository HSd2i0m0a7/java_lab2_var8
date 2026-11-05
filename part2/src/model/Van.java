package model;

public abstract class Van {
    protected static final double MULTIPLIER_SMALL = 1.00;
    protected static final double MULTIPLIER_MEDIUM = 1.35;
    protected static final double MULTIPLIER_LARGE = 1.80;
    private static final double MIN_BASE_PRICE = 100.0;

    private final String modelName;
    private final double basePrice;
    private final String sizeModel;

    private LoaderService appliedLoader = null;

    public Van(String modelName, double basePrice) {
        this(modelName, basePrice, "SMALL");
    }

    public Van(String modelName, double basePrice, String sizeModel) {
        this.modelName = modelName;
        if (basePrice <= 0) {
            System.out.println("[Система] Базовая цена не может быть <= 0. Принудительно установлена минимальная цена " + MIN_BASE_PRICE + " руб.");
            this.basePrice = MIN_BASE_PRICE;
        } else {
            this.basePrice = basePrice;
        }
        this.sizeModel = sizeModel.toUpperCase();
    }

    public abstract double calculateFinalPrice();

    public double calculateFinalPrice(LoaderService loader) {
        this.appliedLoader = loader;
        double priceBeforeLoader = calculateFinalPrice();
        double finalPrice = priceBeforeLoader + loader.getPricePerHour();
        return Math.round(finalPrice * 100.0) / 100.0;
    }

    public double calculateFinalPrice(LoaderService loader, int hours) {
        double priceBeforeLoader = calculateFinalPrice();
        if (hours > 0) {
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