package support.context.rest.exceptions;

public class CoreException extends RuntimeException{
    private static final  long serialVersionUID = 1L;

    public CoreException(String string) {
        super(string);
    }

    public CoreException(Throwable e){
        super(e);
    }
}
