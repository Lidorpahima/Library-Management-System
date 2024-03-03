package Library;

import java.util.*;
/**
 * Name:Lidor Pahima
 * ID:206735961
 * DataStructure Class designed to manage collection of elements such as books library system
 */
public class DataStructure<T> {
    private ArrayList<T> List = new ArrayList<T>();

    /**
     * Constructor an empty Data Structure
     */
    public DataStructure() {}

    /**
     * Adds an element at specified index in data structure
     * Also will check if index outofbound so exception is thrown
     * @param data
     * @param index
     */
    public void add(T data, int index) {
        if (index < 0 || index > List.size()) {
            throw new IndexOutOfBoundsException("Worng index ");
        }
        List.add(index, data);
    }

    /**
     * Adds elements for the end of the data structure
     * @param data
     */
    public void addToEnd(T data){
        List.add(List.size(),data);
    }

    /**
     * Delete elements from the data structure
     * @param data
     */
    public void delete(T data) {
        if (List.contains(data)) {
            List.remove(data);
        } else {
            System.out.println("Not found!");
        }
    }

    /**
     * Check if element contains in the data structure
     * @param data
     * @return
     */
    public boolean contains(T data){
        if(List.isEmpty()){
            return false;
        }else if(List.contains(data)){
            return true;
        }return false;

    }

    /**
     * Return size of the data structure
     * @return
     */
    public int size(){
        return List.size();
    }

    /**
     * To string data structure
     * @return
     */
    public String toString(){
        return List.toString();
    }

    /**
     * Get element from index
     * @param index
     * @return
     */
    public T getData(int index) {
        return List.get(index);
    }

    /**
     * Get index from element
     * @param data
     * @return
     */
    public int getIndex(T data) {
        if (List.contains(data)) {
            return List.indexOf(data);
        }
    else return -1;
    }

    /**
     * Clear Data structure
     */
    public void clear() {
        List.clear();

    }


}

