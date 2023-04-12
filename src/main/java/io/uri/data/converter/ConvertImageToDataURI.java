package io.uri.data.converter;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;


public class ConvertImageToDataURI {

    private static Logger LOGGER = LoggerFactory.getLogger(ConvertImageToDataURI.class);
    public static void main(String[] args) {

        // Ensure args are present
        if(args.length != 1) {
            LOGGER.error("File path and name is required as a single arg");
            return;
        }

        try {
            File file = new File(args[0]);
            String contentType = Files.probeContentType(file.toPath());

            byte[] data = Files.readAllBytes(file.toPath());

            // Convert byte array
            String base64str = Base64.getEncoder().encodeToString(data);

            // Convert to Data URI
            StringBuilder sb = new StringBuilder();
            sb.append("data:");
            sb.append(contentType);
            sb.append(";base64,");
            sb.append(base64str);

            LOGGER.error("Copy and paste this into the Image source field or wherever needed");
            LOGGER.error(sb.toString());

        } catch (Exception e) {
            LOGGER.error("Error "+e);
        }
    }

}
