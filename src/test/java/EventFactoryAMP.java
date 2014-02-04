import com.lmax.disruptor.EventFactory;


public class EventFactoryAMP implements EventFactory {

	@Override
	public Object newInstance() {

		return new ValueEventAMP(ObjectToMove.objValue);
	}

}
