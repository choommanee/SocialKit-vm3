package co.aquario.socialkit;

import com.squareup.otto.Bus;

public class BusProvider {
    private static final Bus BUS = new Bus();

    public BusProvider() {
    }

    public static Bus getInstance() {
        return BUS;
    }
}
