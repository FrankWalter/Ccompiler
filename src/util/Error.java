package util;

/**
 * Created by liuzhe on 10/19/2015.
 */
public final class Error {
    private String message = null;

    public Error(String str, boolean silent) {
        message = str;
        if(!silent) {
            System.out.println(this);
            System.out.flush();
        }
    }

    public String toString() { return message; }
}
