public class LengthException extends RuntimeException{
    @Override
    public String getMessage()
    {
        return "Dado do cadastro equivocado";
    }
}
