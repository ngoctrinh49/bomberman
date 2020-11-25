package bomberman.entities;

import bomberman.entities.unmoving.StaticObject;
import java.util.LinkedList;

public class ObjectsStack {
    private LinkedList<StaticObject> staticObjectLinkedList = new LinkedList<>();

    public StaticObject getLast() {
        return staticObjectLinkedList.peekLast();
    }

    public void addStack(StaticObject object) {
        staticObjectLinkedList.push(object);
    }
}
