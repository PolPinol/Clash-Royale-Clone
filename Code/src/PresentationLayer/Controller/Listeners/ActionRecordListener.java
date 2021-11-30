package PresentationLayer.Controller.Listeners;

import BusinessLayer.Entities.DataGame;

import java.util.ArrayList;
import java.util.Queue;

/**
 * Interface ActionRecordListener that abstracts the methods that manages the history information in real time.
 */
public interface ActionRecordListener {
    /**
     * Method that notifies that the history list has to be updated.
     */
    void loadHistoryList();

    /**
     * Method that adds a new replay if the name doesn't exists.
     *
     * @param name   the name of the replay
     * @param winner the winner if it's the ia or the user
     * @return the boolean
     */
    boolean addNewRecordIfDoesntExist(String name, int winner);

    /**
     * Method that adds new replay data.
     *
     * @param data       the data of the replay
     * @param recordName the replay name
     */
    void addNewRecordData(ArrayList<DataGame> data, String recordName);

    /**
     * Method that deletes all replay from the user that is using the program.
     */
    void deleteUserRecords();

    /**
     * Method that gets all the movements / datagame of a replay.
     *
     * @return the all movements
     */
    Queue<DataGame> getAllMovements();
}