/*
 * Util.java
 *
 * Copyright 2006 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.apache.hadoop.metrics.spi;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Static utility methods
 */
public class Util {
    
    /**
     * This class is not intended to be instantiated
     */
    private Util() {}
    
    /**
     * Parses a space and/or comma separated sequence of server specifications
     * of the form <i>hostname</i> or <i>hostname:port</i>.  If 
     * the specs string is null, defaults to localhost:defaultPort.
     * 
     * @return a list of InetSocketAddress objects.
     */
    public static List parse(String specs, int defaultPort) {
        List result = new ArrayList(1); // ArrayList<InetSocketAddress>
        if (specs == null) {
                result.add(new InetSocketAddress("localhost", defaultPort));
        }
        else {
            String[] specStrings = specs.split("[ ,]+");
            //for (String specString : specStrings) {
            for (int i = 0; i < specStrings.length; i++) {
                String specString = specStrings[i];
                int colon = specString.indexOf(':');
                if (colon < 0 || colon == specString.length() - 1) {
                    result.add(new InetSocketAddress(specString, defaultPort));
                } else {
                    String hostname = specString.substring(0, colon);
                    int port = Integer.parseInt(specString.substring(colon+1));
                    result.add(new InetSocketAddress(hostname, port));
                }
            }
        }
        return result;
    }
    
}
