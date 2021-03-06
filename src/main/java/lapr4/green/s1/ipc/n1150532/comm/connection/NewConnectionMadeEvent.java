package lapr4.green.s1.ipc.n1150532.comm.connection;

import java.net.InetAddress;

/**
 * An event to signal that the TCP server has accepted a new client. It is
 * useful for this instance to create a client to connect back to it.
 *
 * @author Manuel Meireles (1150532@isep.ipp.pt)
 */
public class NewConnectionMadeEvent {

    /**
     * The new connection's TCP server address.
     */
    private final InetAddress serverIPAddress;

    /**
     * The new connection's TCP server port number.
     */
    private final int serverPortNumber;
    private boolean secure;

    /**
     * The full constructor of the event.
     *  @param theNewConnectionIPAddress The address of the new connection's TCP
     * server.
     * @param thePortNumber The port number of the new connection's TCP server.
     * @param secure
     */
    public NewConnectionMadeEvent(InetAddress theNewConnectionIPAddress, int thePortNumber, boolean secure) {
        serverIPAddress = theNewConnectionIPAddress;
        serverPortNumber = thePortNumber;
        this.secure = secure;
    }

    /**
     * A getter to the new connection's TCP server address.
     *
     * @return It returns the new connection's TCP server port number in which
     * to send the TCP connection request.
     */
    public InetAddress getServerIPAddress() {
        return serverIPAddress;
    }

    /**
     * A getter to the new connection's TCP server port number.
     *
     * @return It returns the new connection's TCP server port number in which
     * to send the TCP connection request.
     */
    public int getServerPortNumber() {
        return serverPortNumber;
    }

    /**
     * It provides a ConnectionID from the data.
     *
     * @return It returns a new ConnectionID with this data.
     */
    public ConnectionID getConnectionID(){
        return new ConnectionIDImpl(serverIPAddress,serverPortNumber);
    }

    public boolean isSecure() {
        return secure;
    }
}
