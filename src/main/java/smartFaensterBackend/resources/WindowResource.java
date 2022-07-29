package smartFaensterBackend.resources;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.server.spi.RuntimeConfiguration.Body;

import smartFaensterBackend.services.WindowService;
import smartFaensterBackend.entities.Window;
import smartFaensterBackend.entities.WindowDto;

@Path("windows")
public class WindowResource {
    ResponseBuilder response;

    @Inject
    WindowService windowService;

    @Tag(name = "GET endpoints")
    @Operation(description = "Get all windows", summary = "Get all available windows in a list")
    @APIResponses(value = { @APIResponse(responseCode = "204", description = "Successful") })
    @GET
    @Transactional
    public List<Window> getWindowsList() {
        return this.windowService.getWindows();
    }

    @Tag(name = "POST endpoints")
    @Operation(description = "Add a window", summary = "Add a new entry record")
    @APIResponses(value = { @APIResponse(responseCode = "200", description = "Successful"),
            @APIResponse(responseCode = "400", description = "Unsuccessful") })
    @POST
    @Transactional
    public Response addWindow(@Valid List<Window> windowsList, @Context UriInfo uriInfo) {

        windowService.saveWindowsList(windowsList);

        if (this.windowService.getWindows().size() > 0) {
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            return Response.created(builder.build()).build();
        } else {
            throw new BadRequestException();
        }
    }

    @Tag(name = "POST endpoints")
    @POST
    @Transactional
    @Path("register")
    public long registerWindow(@Context UriInfo uriInfo) {
        Window safedWindow;
        safedWindow = windowService.addOneWindow();

        if (safedWindow.id <= 0) {
            throw new BadRequestException();
        }
        // UriBuilder builder =
        // uriInfo.getAbsolutePathBuilder().path(Long.toString(safedWindow.id));
        // return Response.created(builder.build()).build();
        return safedWindow.id;
    }

    @Tag(name = "POST endpoints")
    @POST
    @Transactional
    @Path("status")
    public int setStatus(WindowDto windowDto, @Context UriInfo uriInfo) {
        Window safedWindow, requestedWindow;
        int status;
        int returnStatus;
        requestedWindow = windowService.getWindowById(windowDto.id);
        status = windowDto.open;
        if (status == 1) {
            requestedWindow.open = true;
        } else if (status == 0) {
            requestedWindow.open = false;
        }
        safedWindow = windowService.setStatus(requestedWindow);

        if (safedWindow.id != requestedWindow.id) {
            throw new BadRequestException();
        }
        // UriBuilder builder =
        // uriInfo.getAbsolutePathBuilder().path(Boolean.toString(safedWindow.open));
        // return Response.accepted(builder.build()).build();
        if (safedWindow.open) {
            returnStatus = 1;
        } else {
            returnStatus = 0;
        }
        return returnStatus;

    }

    @Tag(name = "GET endpoints")
    @GET
    @Transactional
    @Path("status/{id}")
    public int getStatus(long id, @Context UriInfo uriInfo) {
        int status;

        Window window = windowService.getWindowById(id);
        boolean open = window.open;

        if (open) {
            status = 1;
        } else {
            status = 0;
        }
        // if (false){
        // throw new BadRequestException();
        // }
        // UriBuilder builder =
        // uriInfo.getAbsolutePathBuilder().path(Boolean.toString(open));
        // return Response.accepted(builder.build()).build();
        return status;
    }

    @Tag(name = "POST endpoints")
    @POST
    @Transactional
    @Path("update")
    public Response updateWindows(List<Window> windowsListToUpdate, @Context UriInfo uriInfo) {

        windowService.saveWindowsList(windowsListToUpdate);

        if (this.windowService.getWindows().size() > 0) {
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            return Response.created(builder.build()).build();
        } else {
            throw new BadRequestException();
        }

    }

    @Tag(name = "Delete endpoints")
    @APIResponse(responseCode = "200", description = "Successful")
    @APIResponse(responseCode = "500", description = "Unsuccessful")
    @DELETE
    @Transactional
    @Path("delete/{id}")
    public boolean delete(long id) {
        boolean success = true;

        success = Window.deleteById(id);

        return success;
    }

}
