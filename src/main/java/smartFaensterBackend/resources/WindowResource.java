package smartFaensterBackend.resources;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
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

import smartFaensterBackend.services.WindowService;
import smartFaensterBackend.entities.Window;


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
        List<Window> safedWindowsList;
        safedWindowsList= windowService.saveWindowsList(windowsList);
        
        if (safedWindowsList.size()>0) {
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            return Response.created(builder.build()).build();
        } else {
            throw new BadRequestException();
        }
    }

    @Tag(name = "POST endpoints")
    @POST
    @Transactional
    @Path("/register")
    public Response registerWindow(@Context UriInfo uriInfo) {
        Window safedWindow;
        safedWindow = windowService.addOneWindow();
        
        if (safedWindow.id<=0){
            throw new BadRequestException();
    } 
    UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(safedWindow.id));
    return Response.created(builder.build()).build();    
        
    }


    @Tag(name = "POST endpoints")
    @POST
    @Transactional
    @Path("/status")
    public Response setStatus(boolean isOpen, long id, @Context UriInfo uriInfo) {
        Window safedWindow;
        safedWindow = windowService.setStatus(id,isOpen);
        
        if (safedWindow.isOpen==isOpen){
            throw new BadRequestException();
    } 
    UriBuilder builder = uriInfo.getAbsolutePathBuilder();
    return Response.accepted(builder.build()).build();    
        
    }
    
    @Tag(name = "GET endpoints")
    @GET
    @Transactional
    @Path("/status")
    public Response getStatus(long id, @Context UriInfo uriInfo) {
        boolean isOpen = windowService.getWindowById(id).isOpen;
        
    //     if (false){
    //         throw new BadRequestException();
    // } 
    UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Boolean.toString(isOpen));
    return Response.accepted(builder.build()).build();    
        
    }


    // @Tag(name = "Put endpoints")
    // @Operation(description = "Set the windowstate", summary = "Set a window state Open it = true | close it = false")
    // @APIResponse(responseCode = "200", description = "Successful")
    // @APIResponse(responseCode = "500", description = "Unsuccessful")
    // @PUT
    // @Transactional
    // public Response setState(WindowDto windowDto) {
    //     Window window = new Window();
    //     Window updatedWindow;
    //     window.id = windowDto.getId();
    //     window.stateOpen = windowDto.isStateOpen();
    //     window.windowName = windowDto.getWindowName();
    //     updatedWindow = windowService.setState(window, window.stateOpen);
    //     if (updatedWindow.isPersistent()) {
    //         return Response.ok().build();
    //     } else {
    //         throw new InternalServerErrorException(Response.status(500).build());
    //     }
    // }

}
