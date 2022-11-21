package bridge.domain.object;

import java.util.List;
import java.util.Objects;

public class Bridge {
    List<String> route;

    public Bridge(List<String> bridge) {
        this.route = bridge;
    }

    public List<String> getRoute() {
        return route;
    }

    public boolean checkCorrectRoute(final List<String> route) {
        validateRoute(route);
        return this.route.equals(route);
    }

    public boolean checkDifferentRoute(final List<String> route) {
        validateRoute(route);
        for (int i =0; i < route.size(); i++) {
            if (!Objects.equals(route.get(i), this.route.get(i))) {
                return true;
            }
        }
        return false;
    }

    private void validateRoute(final List<String> route) {
        if (route.size() > this.route.size()) {
            throw new IllegalArgumentException("");
        }
    }
}
