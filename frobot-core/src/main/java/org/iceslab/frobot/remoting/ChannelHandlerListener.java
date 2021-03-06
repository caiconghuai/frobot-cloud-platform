package org.iceslab.frobot.remoting;

import java.util.EventListener;

/**
 * @author allen
 */
public interface ChannelHandlerListener extends EventListener {

    void operationComplete(Future future) throws Exception;
}
