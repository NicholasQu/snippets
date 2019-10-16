package cc.xiaoquer.exception.jmh;

/**
 * Created by Nicholas on 2019/10/14.
 */
public class MyExceptionNoStackTrace extends Exception {
    public MyExceptionNoStackTrace(String message) {
        super( message );
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
