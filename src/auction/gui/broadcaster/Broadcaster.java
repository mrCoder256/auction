package auction.gui.broadcaster;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Broadcaster {

    private static final List<IBroadcastListener> listeners = 
    		new CopyOnWriteArrayList<IBroadcastListener>();

    public static void register(IBroadcastListener listener) {
        listeners.add(listener);
    }

    public static void unregister(IBroadcastListener listener) {
        listeners.remove(listener);
    }

    public static void broadcast(BroadcastType type, Object smthNew) {
        for (IBroadcastListener listener : listeners) {
            listener.receiveBroadcast(type, smthNew);
        }
    }

}
