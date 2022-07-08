package smartFaensterBackend.entities;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.DefaultValue;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
public class Window extends PanacheEntityBase {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public String windowName;

    public boolean isOpen;

    public boolean isSelected;

    public int timerPosition; // 0 = Now / 1 = 5min / 2 = 10 / 3 = 15 / 4 = 20 / 5 = 30

    // // @ElementCollection
    // public List<Window> windowsList;

    public Window() {
    }

    public Window( @NotBlank String windowName, boolean isOpen, boolean isSelected, int timerPosition) {
        this.windowName = windowName;
        this.isOpen = isOpen;
        this.isSelected = isSelected;
        this.timerPosition = timerPosition;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWindowName() {
        return windowName;
    }

    public void setWindowName(String windowName) {
        this.windowName = windowName;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public int getTimerPosition() {
        return timerPosition;
    }

    public void setTimerPosition(int timerPosition) {
        this.timerPosition = timerPosition;
    }

    // public List<Window> getWindowsList() {
    //     return windowsList;
    // }

    // public void setWindowsList(List<Window> windowsList) {
    //     this.windowsList = windowsList;
    // }

    



    



}
