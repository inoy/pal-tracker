package io.pivotal.pal.tracker;

import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private long id;

    Logger logger = Logger.getLogger(InMemoryTimeEntryRepository.class.getName());

    List<TimeEntry> timeEntries = new ArrayList<>();

    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(++id);
        timeEntries.add(timeEntry);
        return timeEntry;
    }

    public TimeEntry find(long id) {
        for (TimeEntry timeEntry : timeEntries) {
            if (timeEntry.getId() == id) {
                return timeEntry;
            }
        }
        logger.warning("TimeEntry was not found. id: " + id);
        return null;
    }

    
    public List<TimeEntry> list() {
        return timeEntries;
    }

    
    public TimeEntry update(long id, TimeEntry entry) {
        TimeEntry timeEntry = find(id);
        if (timeEntry == null) {
            return null;
        }
        return timeEntry.update(entry);
    }

    
    public void delete(long id) {
        timeEntries.remove(find(id));
    }

}
