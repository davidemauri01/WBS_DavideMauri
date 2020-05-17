package mainPackage;

import javax.ws.rs.core.*;
import javax.ws.rs.*;

@ApplicationPath("/")
@Path("/")
public class GetPostEx extends Application {

    public GetPostEx() {
        super();
    }

    @GET
    @Produces("text/xml")
    @Path("/impegni")
    public String getImpegno() {
        // connect to the db, create and execute a query, get the result, parse and print it
        return "<impegni>" + DbOperations.printImpegni(DbOperations.getImpegno()) + "</impegni>";
    }
    
    @POST
    @Path("/utenti/inserimento")
    @Consumes("application/xml")
    public String addEntry(String content, @PathParam(value="name") String url_name){
        return "<messaggino>Fatto</messaggino>";
    }
}