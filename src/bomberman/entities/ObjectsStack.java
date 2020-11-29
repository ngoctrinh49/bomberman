package bomberman.entities;

import bomberman.entities.unmoving.StaticObject;
import java.util.LinkedList;

public class ObjectsStack {
    private LinkedList<StaticObject> staticObjectLinkedList = new LinkedList<>();

    public StaticObject getLast() {
        return staticObjectLinkedList.peekFirst();
    }

    public void addStack(StaticObject object) {
        staticObjectLinkedList.push(object);
    }

    public void removeLast() {
        staticObjectLinkedList.pop();
    }

    public void delete(StaticObject object) {
        staticObjectLinkedList.remove(object);
    }
}
