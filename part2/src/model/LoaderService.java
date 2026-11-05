package model;

public class LoaderService {
    private final String serviceName;
    private double pricePerHour;
    public LoaderService(String serviceName, double pricePerHour){
        this.serviceName = serviceName;
        setPricePerHour(pricePerHour);
    }
    public String getServiceName() {
        return serviceName;
    }
    public double getPricePerHour() {
        return pricePerHour;
    }
    public void setPricePerHour(double pricePerHour){
        if (pricePerHour >= 0){
            this.pricePerHour = pricePerHour;
        } else{
            this.pricePerHour = 0;
        }
    }
}
