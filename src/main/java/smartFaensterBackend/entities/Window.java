package smartFaensterBackend.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
public class Window extends PanacheEntityBase {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(name ="windowName")
    public String windowName;

    @Column(name = "open")
    public boolean open;
    // @Column(name = "timerPosition")
    // public int timerPosition; // 0 = Nothing / 1 = Now / 2 = 5min / 3 = 10 / 4 = 15 / 5 = 20 / 6 = 30

    // // @ElementCollection
    // public List<Window> windowsList;

    public Window() {
    }

    public Window( @NotBlank String windowName, boolean open) {
        this.windowName = windowName;
        this.open = open;
    }

    public Window( @NotBlank String windowName, boolean open, long id) {
        this.windowName = windowName;
        this.open = open;
         this.id = id;
    }

    // public long getId() {
    //     return id;
    // }

    // public void setId(long id) {
    //     this.id = id;
    // }

    // public String getWindowName() {
    //     return windowName;
    // }

    // public void setWindowName(String windowName) {
    //     this.windowName = windowName;
    // }

    // public boolean isOpen() {
    //     return isOpen;
    // }

    // public void setOpen(boolean isOpen) {
    //     this.isOpen = isOpen;
    // }

    // public boolean isSelected() {
    //     return isSelected;
    // }

    // public void setSelected(boolean isSelected) {
    //     this.isSelected = isSelected;
    // }

    // public int getTimerPosition() {
    //     return timerPosition;
    // }

    // public void setTimerPosition(int timerPosition) {
    //     this.timerPosition = timerPosition;
    // }

    // public List<Window> getWindowsList() {
    //     return windowsList;
    // }

    // public void setWindowsList(List<Window> windowsList) {
    //     this.windowsList = windowsList;
    // }

    



    



}
