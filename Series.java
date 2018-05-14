package sample;

public class Series {

    private int temperature;
    private int temperature2;
    private int humidity;
    private int pressure;
    private int windspeed;

    public Series(int temperature,int temperature2,int humidity,int pressure,int windspeed ){

        this.temperature=temperature;
        this.temperature2=temperature2;
        this.humidity=humidity;
        this.pressure=pressure;
        this.windspeed=windspeed;
    }

    public double getTemperature(){
        return temperature;
    }
    public double getTemperature2(){
        return temperature2;
    }
    public double getHumidity(){
        return humidity;
    }
    public double getPressure(){
        return pressure;
    }

    public double getWindspeed() {
        return windspeed;
    }
}
