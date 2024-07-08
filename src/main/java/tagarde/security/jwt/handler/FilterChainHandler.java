package tagarde.security.jwt.handler;

import org.jetbrains.annotations.NotNull;

public abstract class FilterChainHandler {
    protected FilterChainHandler next;

    public FilterChainHandler setNextHandler(final FilterChainHandler nextFilterChainHandler) {
        this.next = nextFilterChainHandler;
        return next;
    }

    public abstract boolean handle(final String jwtToken);

    protected boolean handleNext(@NotNull String jwtToken) {
        if (next == null) {
            return true;
        }
        return next.handle(jwtToken);
    }
}
