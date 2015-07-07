import javax.swing.*;
import java.util.ArrayList;


/**
 * Created by Canon on 2015-07-06.
 */
public class History {
    private static State currentState = null;
    private static State head;
    private static State tail;

    private static class State {
        public State next = null;
        public State prev = null;
        public byte[] content = null;
        public State(byte[] bytes) {
            this.content = bytes;
        }
    }

    static  {
        head = new State(null);
        tail = new State(null);
        head.next = tail;
        tail.prev = head;
    }


    public static void addRecord() {
        byte[] bytes = SaverAndLoader.saveStateToMem();
        if(currentState == null ){
            currentState = new State(bytes);
            head.next = currentState;
            tail.prev = currentState;
            currentState.prev = head;
            currentState.next = tail;
        } else {
            State newState = new State(bytes);
            currentState.next = newState;
            tail.prev = newState;
            newState.prev = currentState;
            newState.next = tail;
            currentState = newState;
        }

    }

    public static void undo() {
        if(currentState == null) {
            return;
        }
        if(currentState.prev == head) {
            return;
        }
        currentState = currentState.prev;
        gotoState(currentState.content);
    }

    public static void redo() {
        if(currentState == null) {
            return;
        }
        if(currentState.next == tail) {
            return;
        }
        currentState = currentState.next;
        gotoState(currentState.content);
    }

    private static void gotoState(byte[] stateArray) {
        System.out.println("Switch state");
        SaverAndLoader.restoreStateFromMem(stateArray);
    }

    public static byte[] getCurrentState() {
        if(currentState == null) {
            return null;
        }
        return currentState.content;
    }

    public static void clear() {
        head.next = tail;
        tail.prev = head;
        currentState = null;
    }
}
