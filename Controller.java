package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import sun.java2d.loops.GraphicsPrimitive;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Controller {



    protected SerialPort serialPort;
    public boolean serialPortStatus;
    protected int numberInList=0;
    private int iteratorOfTableSeries;
    protected List<Integer> DataList=new ArrayList<>();

    @FXML
    protected TableView <Series>tabela;

    @FXML
    protected TableColumn<Series, Integer> temperature;
    @FXML
    protected TableColumn<Series,Integer> temperature2;
    @FXML
    protected TableColumn<Series,Integer> humidity;
    @FXML
    protected TableColumn<Series,Integer> pressure;
    @FXML
    TableColumn<Series,Integer> windSpeed;




    public boolean openPort() throws SerialPortException {

        String[] names = SerialPortList.getPortNames();


        //for(String name:names){

            serialPort= new SerialPort("/dev/tty.usbmodem1423");
            serialPort.openPort();


            if(serialPort.isOpened()==true) {
                serialPortStatus = true;
                System.out.println(serialPortStatus);
               // break;
           // }

        }
        serialPort.setParams(11500,8,1,0);

        return serialPortStatus;
    }

    public List getData(boolean s) throws SerialPortException {
        s = serialPortStatus;
        int i = 0;


        if (s == true) {
            String dataRead = serialPort.readString();

            StringTokenizer sT = new StringTokenizer(dataRead.trim(), ";");


            int catchInt;

            while (sT.hasMoreTokens()) {

                if (i == 5) {
                    break;
                }

                catchInt = Integer.parseInt(sT.nextToken().trim());
                DataList.add(numberInList, catchInt);

                numberInList++;
                System.out.println("xD" + DataList);

            }
            System.out.println("xD" + DataList);
            List<Series> seriesList = new ArrayList<>();


            new Series(DataList.get(iteratorOfTableSeries), DataList.get(iteratorOfTableSeries + 1), DataList.get(iteratorOfTableSeries + 2), DataList.get(iteratorOfTableSeries + 3), DataList.get(iteratorOfTableSeries + 4));
            iteratorOfTableSeries += 5;

            tabela.itemsProperty().setValue((ObservableList<Series>) seriesList);



        }
        return DataList;
        }

        public void tableGeneratr () {


            temperature.setCellValueFactory(
                    new PropertyValueFactory<Series, Integer>("temperature")
            );
            humidity.setCellValueFactory(
                    new PropertyValueFactory<Series, Integer>("humidity")
            );
            temperature2.setCellValueFactory(
                    new PropertyValueFactory<Series, Integer>("temperature2"));
            pressure.setCellValueFactory(
                    new PropertyValueFactory<Series, Integer>("pressure")
            );
            windSpeed.setCellValueFactory(
                    new PropertyValueFactory<Series, Integer>("windSpeed"));


        }

        public void runTable () {
            Runnable runnable = new Threed();
            Thread th = new Thread(runnable);
            th.start();


        }
    }





