package smartFaensterBackend.services;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
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
                if (window.id <= 0) {
                    Window windowToSafe = new Window();
                    windowToSafe.selected = window.selected;
                    windowToSafe.timerPosition = window.timerPosition;
                    windowToSafe.windowName = window.windowName;
                    windowToSafe.open = window.open;
                    safedWindowsList.add(windowToSafe);
                    windowToSafe.persist();
                } else {
                    // Window windowToUpdate = new Window(window.windowName, window.isOpen, window.isSelected, window.timerPosition, window.id);
                    Window windowToUpdate = Window.findById(window.id);
                    // windowToUpdate.windowName = window.windowName;
                    windowToUpdate.id = window.id;
                    windowToUpdate.open = window.open;
                    windowToUpdate.selected = window.selected;
                    windowToUpdate.timerPosition = window.timerPosition;

                    // windowToUpdate=window.findById(window.id);
                    // windowToUpdate.setSelected(window.isSelected);
                    // windowToUpdate.setTimerPosition(window.timerPosition);
                    // windowToUpdate.setOpen(window.isOpen);
                    // windowToUpdate.setWindowName(window.windowName);
                    // windowToUpdate.setId(window.id);
                    // windowToUpdate.persist();
                    EntityManager em = Window.getEntityManager();
                    em.merge(windowToUpdate).persist();
                    // em.getEntityManager().merge(windowToUpdate).persist();;
                    safedWindowsList.add(windowToUpdate);
                }

            }
        }

        return safedWindowsList;
    }

    public Window addOneWindow() {
        Window newWindow = new Window("Standardname", false, false, 0);

        newWindow.persist();
        return newWindow;
    }

    public Window setStatus(long id, boolean isOpen) {
        Window window = Window.findById(id);
        // window.setOpen(isOpen);
        window.persist();
        return window;
    }

}
