package cambridge.materials.galliumnitride.app.ledlab;

//Exception called when topicInformation constructor is provided with invalid inputs because the
// page counts don't match the size of animations and texts arrays

public class InvalidTopicInputException extends Exception
{
    public InvalidTopicInputException() {
        super();
    }

    public InvalidTopicInputException(String message, Throwable cause)
    {
        super(message, cause);

        this.initCause(cause);
    }
}