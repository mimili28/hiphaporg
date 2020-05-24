import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Date {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private DateTimeFormatter format = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm" );

    /**
     *  Creates a date for event
     * @param x assigns 2 dates, one for startDate and one for endDate
     */
    Date(String[] x) {
        this.startDate = LocalDateTime.parse( x[3], format );
        this.endDate = LocalDateTime.parse( x[4], format );
    }

    /**
     * Sets the start date
     */
    void setStartDate(String startDate) {
        this.startDate = LocalDateTime.parse( startDate, format );
    }

    /**
     * Sets the end date
     */
    void setEndDate(String endDate) {
        this.endDate = LocalDateTime.parse( endDate, format );
    }

    /**
     * @return the start date
     */
    LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * @return the end date
     */
    LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * Provide the user with a string representation for the date
     * @return a string representation
     */
    @Override
    public String toString() {
        return "Start date of event: " + startDate.format( format ) + "\n" +
                "End date of event: " + endDate.format( format ) + "\n";
    }

    /**
     * @return a string representation to the file on a single line
     */
    String toFile() {
        return String.format( startDate.format( format ) + "," +
                endDate.format( format ) + "," );
    }
}
