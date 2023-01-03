import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Realization implements Changeable{

    private List<Data> list;
    private static final Path PATH = Paths.get("data/database.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @Override
    public List<Data> openConnection() {
        String json = "";
        try{
            json = Files.readString(PATH);
        }catch (IOException e){
            e.printStackTrace();
        }
        list = List.of(GSON.fromJson(json, Data[].class));
        return list;
    }

    @Override
    public void closeConnection() {

    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public Data getData(int index){
        Data d = null;
        try{
            d = list.get(index);
        }catch(IndexOutOfBoundsException e){
            System.out.println("Record not found.");
        }
        return d;
    }

    @Override
    public boolean isDataExist(String key) {
        boolean ans = false;
        for(Data data : list){
            if(data.getKey().equals(key)){
                ans = true;
                break;
            }
        }
        return ans;
    }

    @Override
    public Data getData(String key) throws NullPointerException {
        Data d = null;
        for(Data data : list){
            if(data.getKey().equals(key)){
                d = data;
                break;
            }
        }
        if(d == null) throw new NullPointerException("There is no such record in database");
        return d;
    }

    @Override
    public int getLength() {
        return list.size();
    }

    @Override
    public void addData(Data data) {
        list.add(data);
    }

    @Override
    public void changeValue(int index, String value) {
        try{
            list.get(index).setValue(value);
        }catch(IndexOutOfBoundsException e){
            System.out.println("There is no such data.");
        }
    }

    @Override
    public void changeValue(String key, String v) {
        for(Data d: list){
            if(isEqual(d, key)){
                System.out.println("Your update: ");
                d.setValue(getInput());
                return;
            }
        }
        System.out.println("There is no record with the key " + key);
    }

    private String getInput(){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();
        return input;
    }

    private boolean isEqual(Data data, String key){
        return data.getKey().equals(key);
    }

    @Override
    public List<Data> getRecords(int begin, int last) {
        List<Data> sub = new ArrayList<>();
        try{
            sub = list.subList(begin, last);
        }catch(IndexOutOfBoundsException e){
            System.out.println("Incorrect input.Enter from 0 to " + list.size());
        }
        return sub;
    }
}
