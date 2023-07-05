package support.context.rest.functions;

import static support.context.Context.*;

public interface IManagerRest {

    default void quit() {
        if (getScenario().isFailed()) {
            report().setText(rest().getAllLogs());
        }
    }
}
