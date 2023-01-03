import model.Data;

import java.util.List;

public interface Changeable {

    List<Data> openConnection();


    void closeConnection();

    boolean isConnected();


    Data getData(int index);

    boolean isDataExist(String key);

    Data getData(String key);

    int getLength();

    void addData(Data data);

    void changeValue(int index, String value);

    void changeValue(String key, String v);

    List<Data> getRecords(int index, int i);

}
