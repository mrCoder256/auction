package auction.gui.broadcaster;

public interface IBroadcastListener {
	
    public void receiveBroadcast(final BroadcastType type, final Object smthNew);
    
}