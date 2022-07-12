package smartFaensterBackend.services;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
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

    public void saveWindowsList(List<Window> windowsList) {
        List<Window> safedWindowsList = new ArrayList();
        if (windowsList.size() > 0) {
            for (Window window : windowsList) {
                if (window.id <= 0) {
                    Window windowToSafe = new Window();
                    windowToSafe.windowName = window.windowName;
                    windowToSafe.open = window.open;
                    safedWindowsList.add(windowToSafe);
                    windowToSafe.persist();
                } else {
                    Window windowToUpdate = Window.findById(window.id);
                    windowToUpdate.id = window.id;
                    windowToUpdate.open = window.open;
                    windowToUpdate.windowName = window.windowName;
                    EntityManager em = Window.getEntityManager();
                    em.merge(windowToUpdate).persist();
                    safedWindowsList.add(windowToUpdate);

                }

            }
        }

    }

    public Window addOneWindow() {
        Window newWindow = new Window("Standardname", false);

        newWindow.persist();
        return newWindow;
    }

    public Window setStatus(Window window) {
        Window windowToUpdate = Window.findById(window.id);
        windowToUpdate.open = window.open;
        EntityManager em = Window.getEntityManager();
        em.merge(windowToUpdate).persist();
        return windowToUpdate;
    }
}
