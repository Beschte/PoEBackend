package smartFaensterBackend.services;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.validation.Valid;

import smartFaensterBackend.entities.Window;

@RequestScoped
public class WindowService {

    public Window persistEntry(Window window) {
        window.persist();
        return window;
    }

    public List<Window> getWindows() {
        return Window.listAll();
    }

    public Window getWindowById(long id) {
        return Window.findById(id);
    }

    // public Window setState(Window window, boolean stateOpen) {
    // Window updatedWindow = window.findById(window.id);
    // updatedWindow.stateOpen = stateOpen;
    // updatedWindow.persist();
    // return updatedWindow;

    // }

    public List<Window> saveWindowsList(List<Window> windowsList) {
        // Window windowToSafe = new Window();
        List<Window> safedWindowsList = new ArrayList();
        if (windowsList.size() > 0) {
            for (Window window : windowsList) {
                Window windowToSafe = new Window();
                windowToSafe.isSelected = window.isSelected;
                windowToSafe.timerPosition = window.timerPosition;
                windowToSafe.windowName = window.windowName;
                windowToSafe.isOpen = window.isOpen;
                // this.persistEntry(windowToSafe);
                safedWindowsList.add(windowToSafe);
                windowToSafe.persist();
            }
        }

        return safedWindowsList;
    }

    public Window addOneWindow( ) {
        Window newWindow = new Window("Standardname", false, false, 0);
        
        newWindow.persist();
        return newWindow;
    }

    public Window setStatus(long id, boolean isOpen) {
        Window window=Window.findById(id);
        window.setOpen(isOpen);
        window.persist();
        return window;
    }

}
