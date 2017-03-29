package com.scg.net.server;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.TimeCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public final class InvoiceServer {
    private final List<ClientAccount> clientList;

    /** The Consultants*/
    private final List<Consultant>consultantList;

     /** This class' logger. */
    private static final Logger logger = LoggerFactory. getLogger(CommandProcessor.class);

    /** The name of the directory to be used for files output*/
    private String outputDirectoryName  = ".";

    /** The server socket soceket*/
     private ServerSocket serverSocket = null;

    /** The server socket port*/
    private final int port;

    public InvoiceServer(List<ClientAccount> clientList,List<Consultant>consultantList,
                         int port){
            this.clientList = clientList;
        this.consultantList = consultantList;
        this.port = port;
    }

    public void run(){
        try( ServerSocket serverSocket = new ServerSocket((port)) {
            this.serverSocket = serverSocket;
            logger.info("InvoiceServer started on "
                    + serverSocket.getInetAddress()).getHostName()+
                + serverSocket.getLocalPort());
            while(!serverSocket.isClosed()){
                logger.info("Invoice server waiting for connection on port " + port);
                try( Socket client = serverSocket.accept()) {
                    serviceConnection(client);
                } catch (final SocketException sx){
                    logger.info(" Server Socket closed");
                }
            }
            catch (final IOException e1){
                logger.error("Unable to bind server socket to port" + port);
            }
        }
    }

}
