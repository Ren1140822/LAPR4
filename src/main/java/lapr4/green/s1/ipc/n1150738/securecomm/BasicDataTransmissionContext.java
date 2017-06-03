package lapr4.green.s1.ipc.n1150738.securecomm;

import org.apache.commons.io.input.TeeInputStream;
import org.apache.commons.io.output.TeeOutputStream;
import org.eclipse.persistence.internal.jpa.metadata.accessors.mappings.BasicAccessor;

import java.io.*;

/**
 * Created by henri on 6/3/2017.
 */
public class BasicDataTransmissionContext implements DataTransmissionContext {

    private WiretapedStream inputTap;
    private WiretapedStream outputTap;

    public BasicDataTransmissionContext(){
        inputTap = new WiretapedStream();
        outputTap = new WiretapedStream();
    }

    /**
     * Returns the input stream from where the system shall read the
     * objects to be received through the connection.
     *
     * @param socketInStream the InputStream of the socket
     * @return the ObjectInputStream from where we will read objects received
     */
    @Override
    public ObjectInputStream inputStream(InputStream socketInStream) throws IOException {
        return new ObjectInputStream(new TeeInputStream(socketInStream, inputTap));
    }

    /**
     * Returns the output stream from where the system should write the
     * objects to be sent through the connection.
     *
     * @param socketOutStream the OutputStream of the socket
     * @return the ObjectOutputStream from where we will read objects received
     */
    @Override
    public ObjectOutputStream outputStream(OutputStream socketOutStream) throws IOException{
        return new ObjectOutputStream(new TeeOutputStream(socketOutStream, inputTap));
    }

    @Override
    public WiretapedStream wiretapInput() {
        return inputTap;
    }

    @Override
    public WiretapedStream wiretapOutput() {
        return outputTap;
    }
}