package sample;

import jssc.SerialPortException;

import java.util.ArrayList;
import java.util.List;

public class Threed  implements Runnable {



    Controller cs = new Controller();
    boolean status=cs.serialPortStatus;


    @Override
    public void run() {

        int iteratorOfIndeksInList;

        int i=0;
        System.out.println(status);

        while (true){
            try {
                Thread.sleep(1000);
                if(i==0){
                    cs.getData(true);
                    i++;
                    continue;
                }
                else {
                    i++;
                }

                cs.getData(true);
                if(i>0) {
                    cs.tableGeneratr();
                }
                System.out.println(cs.DataList);
            } catch (SerialPortException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }
}
