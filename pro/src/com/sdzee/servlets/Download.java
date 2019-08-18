package com.sdzee.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Download extends HttpServlet {
    private static final int DEFAULT_BUFFER_SIZE = 10240;

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String chemin = this.getServletConfig().getInitParameter( "chemin" );
        String fichierRequis = request.getPathInfo();

        if ( fichierRequis == null || "/".equals( fichierRequis ) ) {
            response.sendError( HttpServletResponse.SC_NOT_FOUND );
            return;
        }

        fichierRequis = URLDecoder.decode( fichierRequis, "UTF-8" );
        File fichier = new File( chemin, fichierRequis );

        if ( !fichier.exists() ) {
            response.sendError( HttpServletResponse.SC_NOT_FOUND );
            return;
        }

        String type = getServletContext().getMimeType( fichier.getName() );

        if ( type == null ) {
            type = "application/octet-stream";
        }

        response.reset();
        response.setBufferSize( DEFAULT_BUFFER_SIZE );
        response.setContentType( type );
        response.setHeader( "content-length", String.valueOf( fichier.length() ) );
        response.setHeader( "content-disposition", "attachment; filename=\"" + fichier.getName() + "\"" );

        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;

        try {
            entree = new BufferedInputStream( new FileInputStream( fichier ), DEFAULT_BUFFER_SIZE );
            sortie = new BufferedOutputStream( response.getOutputStream(), DEFAULT_BUFFER_SIZE );
            byte[] tampon = new byte[DEFAULT_BUFFER_SIZE];
            int longueur;
            while ( ( longueur = entree.read( tampon ) ) > 0 ) {
                sortie.write( tampon, 0, longueur );
            }

        } finally {
            try {
                sortie.close();
                entree.close();
            } catch ( IOException ignore ) {

            }
        }

    }

}
