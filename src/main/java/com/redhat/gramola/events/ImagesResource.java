package com.redhat.gramola.events;

import java.io.File;
import java.net.URL;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.logging.Logger;

@Path("/images")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ImagesResource {
    Logger logger = Logger.getLogger(ImagesResource.class);

    // @GET
    // @Path("{fileName}")
    // @Produces(MediaType.APPLICATION_OCTET_STREAM)
    // public byte[] findImageByName(@PathParam("fileName") String fileName) {
    //     List<Image> images = Image.findImageByName(fileName);
    //     if (images.size() >= 0) {
    //         logger.info("size: " + images.get(0).photo.length);
    //         return images.get(0).photo;
    //     }
    //     return new byte[0];
    // }

    @GET
    @Path("/{fileName}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFile(@PathParam("fileName") String fileName) {
        logger.debug("Download file " + fileName);
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("images" + File.separator + fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File "+ fileName + " not found!");
        }
        // File fileDownload = new File("/tmp/images"+ File.separator + fileName);
        File fileDownload = new File(resource.getFile());
        ResponseBuilder response = Response.ok((Object) fileDownload);
        response.header("Content-Disposition", "attachment;filename=" + fileName);
        return response.build();
    }
}