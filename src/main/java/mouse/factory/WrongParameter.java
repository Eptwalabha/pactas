package mouse.factory;

/**
 * User: Eptwalabha
 * Date: 15/07/2014
 * Time: 23:57
 */
public class WrongParameter extends Exception {

    public WrongParameter(String message) {
        super("wrong parameter : " + message);
    }
}
