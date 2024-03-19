public class StandardTimeConverter {
    
    public String getStandardTime(String militaryTime) {
        
        String[] parts = militaryTime.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);

        String period = (hours >= 12) ? "PM" : "AM";

        hours = hours % 12;
        if (hours == 0) {
            hours = 12; 
        }

        return String.format("%02d:%02d:%02d %s", hours, minutes, seconds, period);
    }
}
